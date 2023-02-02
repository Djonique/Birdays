/*
 * Copyright 2017 Evgeny Timofeev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eblis.whenwasit.alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import android.util.Log;
import android.widget.Toast;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.activities.DetailActivity;
import com.eblis.whenwasit.database.DbHelper;
import com.eblis.whenwasit.models.Person;
import com.eblis.whenwasit.utils.BirdaysApplication;
import com.eblis.whenwasit.utils.Constants;
import com.eblis.whenwasit.utils.ContactsHelper;
import com.eblis.whenwasit.utils.PermissionManager;
import com.eblis.whenwasit.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "WhenWasIt::AlrmReceiver";
    private static final String CHANNEL_ID = "com.eblis.whenwasit";

    private void addNotification(Context context, NotificationManager manager, SharedPreferences preferences, Intent intent, Person person, int daysToBirthday) {
        final String name = person.getName();
        final String anniversaryLabel = person.getAnniversaryLabel();
        final String when = getWhen(context, daysToBirthday);
        final long recordId = person.getId();

        PendingIntent pendingIntent;
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            flags |= PendingIntent.FLAG_IMMUTABLE;
        }
        Intent mainIntent = getDetailsIntent(context, recordId, intent);
        pendingIntent = PendingIntent.getActivity(context, (int) recordId, mainIntent, flags);
        pendingIntent = TaskStackBuilder.create(context)
//                .addNextIntent(intent)
                .addNextIntent(getDetailsIntent(context, recordId, intent))
//                .addNextIntentWithParentStack(getDetailsIntent(context, recordId, intent))
                .getPendingIntent(((int) recordId), flags);

        final Bitmap picture = Utils.getContactPicture(context, person);
        NotificationCompat.Builder builder = buildNotification(context, name, anniversaryLabel, when, daysToBirthday, picture);

        setDefaultsAndRingtone(preferences, builder);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        if (manager != null) {
            manager.notify((int) recordId, notification);
        }

        Log.i(TAG, "Added notification for " + name + " at " + when);
    }

    private String getWhen(Context context, int daysToBirthday) {
        if (daysToBirthday == 0) {
            return "Today";
        }

        final String[] dates = context.getResources().getStringArray(R.array.additional_notification_delay);
        final String[] entryValues = context.getResources().getStringArray(R.array.additional_notification_entry_values);
        for (int i = 0; i < entryValues.length; i++) {
            if (daysToBirthday == (Long.parseLong(entryValues[i]))) {
                return dates[i + 1];
            }
        }
        return null;

    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        android.os.Debug.waitForDebugger();
        Log.d(TAG,String.format("Running AlarmReceiver::onReceive() for %s", intent.getAction()));
        final NotificationManager manager = ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE));
        final DbHelper dbHelper = new DbHelper(context);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        Log.d(TAG, "About to perform automatic import, if enabled");
        final boolean automaticImport = preferences.getBoolean(Constants.AUTOMATIC_CONTACT_IMPORT_KEY, true);
        if (automaticImport) {
            try {
                Log.d(CHANNEL_ID, "Performing automatic import");
                if (PermissionManager.readingContactsPermissionGranted(context)) {
                    ContactsHelper contactsHelper = new ContactsHelper(context, context.getContentResolver());
                    contactsHelper.updateContactsNow();
                    Toast.makeText(context, R.string.contacts_uploaded, Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception ex) {
                Toast.makeText(context, R.string.loading_contacts_error + "\n" + ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        Log.d(TAG, "Creating notification channel");
        createNotificationChannel(context, manager);

        final Set<Long> additionalNotificationOffsets = getAdditionalNotificationOffsets(preferences);
        final List<Person> persons = dbHelper.query().getPersons();
        final SortedMap<Integer, List<Person>> notifications = new TreeMap<>();
        Collections.sort(persons, Collections.<Person>reverseOrder());
        Log.d(TAG, "Checking all person birthdays for notifications to show");
        for (Person person : persons) {
            final Integer daysToBirthday = shouldNotify(person, additionalNotificationOffsets);
            if (daysToBirthday != null) {
                if (!notifications.containsKey(daysToBirthday)) {
                    notifications.put(daysToBirthday, new ArrayList<Person>());
                }
                notifications.get(daysToBirthday).add(person);
                addNotification(context, manager, preferences, intent, person, daysToBirthday);
            }
        }
        Log.d(TAG, "Finished adding notification for all contacts");
        //addNotifications(context, manager, preferences, intent, notifications);

        Log.d(TAG,String.format("Finished running AlarmReceiver::onReceive() for %s", intent.getAction()));
    }

    private Integer shouldNotify(Person person, Set<Long> additionalNotificationOffsets) {
        final int daysToBirthday = Utils.daysLeft(person);
        if (daysToBirthday == 0 || additionalNotificationOffsets.contains((long) daysToBirthday)) {
            return daysToBirthday;
        }

        //null means we shouldn't notify
        return null;
    }

    /**
     * Creates intent to open DetailActivity on notification click
     */
    private Intent getDetailsIntent(Context context, long recordId, Intent intent) {
        Intent detailsIntent = new Intent(context, DetailActivity.class);
        detailsIntent.putExtra(Constants.RECORD_ID, recordId);
        if (BirdaysApplication.isActivityVisible()) {
            detailsIntent = intent;
        }
        else {
            Log.i(TAG, "Birthdays application activity is not visible");
        }
        detailsIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return detailsIntent;
    }

    private Set<Long> getAdditionalNotificationOffsets(SharedPreferences preferences) {
        final Set<String> strs =  preferences.getStringSet(Constants.ADDITIONAL_NOTIFICATION_KEY, Collections.<String>emptySet());
        final Set<Long> results = new HashSet<>();
        for (String str : strs) {
            try {
                results.add(Long.parseLong(str));
            }
            catch (NumberFormatException nfex) {
                // ignore invalid numbers
            }
        }

        return results;
    }


    /**
     * Creates notification channel for Android API 26+
     */
    private void createNotificationChannel(Context context, NotificationManager manager) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    context.getString(R.string.channel_name), NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    /**
     * Builds default notification
     */
    private NotificationCompat.Builder buildNotification(Context context, String title, String anniversaryLabel, String text, int daysToBirthday, Bitmap contactPicture) {
        final int color = Utils.getNotificationColor(context, daysToBirthday);
        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(anniversaryLabel + ": " + text)
                .setContentInfo(anniversaryLabel)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(contactPicture)
                .setColor(color)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
    }

    /**
     * Avoids FileUriExposedException on Android API 24+
     */
    private void setDefaultsAndRingtone(SharedPreferences preferences, NotificationCompat.Builder builder) {
        String ringtone = preferences.getString(Constants.RINGTONE_KEY, Settings.System.DEFAULT_NOTIFICATION_URI.toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                setRingtone(builder, Uri.parse(ringtone));
            } catch (Exception e) {
                builder.setDefaults(NotificationCompat.DEFAULT_ALL);
            }
        } else {
            setRingtone(builder, Uri.parse(ringtone));
        }
    }

    /**
     * Set up notification tone, vibration and lights for notification
     */
    private void setRingtone(NotificationCompat.Builder builder, Uri ringtoneUri) {
        builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE | NotificationCompat.DEFAULT_LIGHTS);
        builder.setSound(ringtoneUri);
    }
}
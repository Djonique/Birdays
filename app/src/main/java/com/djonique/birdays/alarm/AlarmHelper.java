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

package com.djonique.birdays.alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.djonique.birdays.R;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.Constants;
import com.djonique.birdays.utils.Utils;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static android.text.format.DateUtils.DAY_IN_MILLIS;

public class AlarmHelper {
    final private long defaultNotificationTime = 645703200000L - Utils.getTimeOffset();
    final private HashMap<Person, Set<PendingIntent>> pendingIntents = new HashMap<>();
    final private Context context;
    final private AlarmManager alarmManager;
    final private SharedPreferences preferences;

    public AlarmHelper(Context context) {
        this.context = context;
        alarmManager = ((AlarmManager)
                context.getApplicationContext().getSystemService(Context.ALARM_SERVICE));
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set up both alarms main and additional
     */
    public void setAlarms(Person person) {
        try {
            setAlarm(person);
            Set<Long> additionalNotificationOffsets = getAdditionalNotificationOffsets();
            if ((additionalNotificationOffsets != null) && (!additionalNotificationOffsets.isEmpty())) {
                setAdditionalAlarms(person);
            }
        } catch (SecurityException e) {
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, R.string.security_exception, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    private void addPendingIntent(Person person, PendingIntent pendingIntent) {
        if (!pendingIntents.containsKey(person)) {
            pendingIntents.put(person, new HashSet<PendingIntent>());
        }

        pendingIntents.get(person).add(pendingIntent);
    }

    /**
     * Set up main alarm
     */
    private void setAlarm(Person person) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(Constants.NAME, person.getName());
        intent.putExtra(Constants.WHEN, context.getString(R.string.today));
        intent.putExtra(Constants.TIME_STAMP, person.getTimeStamp());

        long triggerAtMillis = setupCalendarYear(person, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(),
                (int) person.getTimeStamp(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        addPendingIntent(person, pendingIntent); //main alarm

        setAlarmDependingOnApi(alarmManager, triggerAtMillis, pendingIntent);
    }

    /**
     * Set up additional alarms
     */
    private void setAdditionalAlarms(Person person) {
        Set<Long> additionalNotificationOffsets = getAdditionalNotificationOffsets();
        for (Long additionalNotificationOffset : additionalNotificationOffsets) {
            Intent intent = new Intent(context, AlarmReceiver.class);
            intent.putExtra(Constants.NAME, person.getName());
            intent.putExtra(Constants.WHEN, setWhen(additionalNotificationOffset));
            intent.putExtra(Constants.TIME_STAMP, person.getTimeStamp());

            long triggerAtMillis = setupCalendarYear(person, additionalNotificationOffset);

            int requestCode = (int) (person.getTimeStamp() + additionalNotificationOffset);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(),
                    (int) requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            addPendingIntent(person, pendingIntent);

            setAlarmDependingOnApi(alarmManager, triggerAtMillis, pendingIntent);
        }
    }

    /**
     * Set up correct alarm for Android API 19 (without delay) and Android API 23+ with Doze
     */
    private void setAlarmDependingOnApi(AlarmManager alarmManager,
                                        long triggerAtMillis,
                                        PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
        }
    }

    /**
     * Configures text for additional notification
     */
    private String setWhen(long offset) {
        final String[] dates = context.getResources().getStringArray(R.array.additional_notification_delay);
        final String[] entryValues = context.getResources().getStringArray(R.array.additional_notification_entry_values);
        for (int i = 0; i < entryValues.length; i++) {
            if (offset == (Long.parseLong(entryValues[i]) * DAY_IN_MILLIS)) {
                return dates[i + 1];
            }
        }
        return null;
    }

    private Set<Long> getAdditionalNotificationOffsets() {
        final Set<String> strs =  preferences.getStringSet(Constants.ADDITIONAL_NOTIFICATION_KEY, Collections.<String>emptySet());
        final Set<Long> results = new HashSet<>();
        for (String str : strs) {
            results.add(Long.parseLong(str) * DAY_IN_MILLIS);
        }

        return results;
    }

    /**
     * Removes all alarms
     */
    public void removeAlarms(Person person) {
        if (pendingIntents.containsKey(person)) {
            for (PendingIntent pendingIntent : pendingIntents.get(person)) {
                alarmManager.cancel(pendingIntent);
            }

            pendingIntents.remove(person);
        }
    }

    /**
     * Set up time for triggering alarm
     */
    private long setupCalendarYear(Person person, long offset) {
        final long now = Calendar.getInstance().getTimeInMillis();
        final long notificationTime = preferences.getLong(Constants.NOTIFICATION_TIME_KEY, defaultNotificationTime);
        final Calendar notificationTimeCalendar = Calendar.getInstance();
        notificationTimeCalendar.setTimeInMillis(notificationTime);

        final int hour = notificationTimeCalendar.get(Calendar.HOUR_OF_DAY);
        final int minutes = notificationTimeCalendar.get(Calendar.MINUTE);

        final long date = person.getDate() - offset;
        final int year = Calendar.getInstance().get(Calendar.YEAR);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.MILLISECOND, 0);
        if (now > calendar.getTimeInMillis()) {
            calendar.set(Calendar.YEAR, year + 1);
        }
        return calendar.getTimeInMillis();
    }
}
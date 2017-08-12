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

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.djonique.birdays.R;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.Utils;

import java.util.Calendar;

public class AlarmHelper {

    private static final int REQUEST_CODE_OFFSET = 99;
    @SuppressLint("StaticFieldLeak")
    private static AlarmHelper instance;
    private long defaultNotificationTime = 645703200000L - Utils.getTimeOffset();
    private long additionalNotificationOffset;
    private Context context;
    private AlarmManager alarmManager;
    private SharedPreferences preferences;

    public static AlarmHelper getInstance() {
        if (instance == null) {
            instance = new AlarmHelper();
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        alarmManager = ((AlarmManager)
                context.getApplicationContext().getSystemService(Context.ALARM_SERVICE));
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private void setAlarm(Person person) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(ConstantManager.NAME, person.getName() + context.getString(R.string.notification_greetings));
        intent.putExtra(ConstantManager.TIME_STAMP, person.getTimeStamp());

        long triggerAtMillis = setupCalendarYear(person, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(),
                (int) person.getTimeStamp(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
    }

    private void setAdditionalAlarm(Person person) {
        additionalNotificationOffset = Long.parseLong(preferences.getString(ConstantManager.ADDITIONAL_NOTIFICATION_KEY, "0"));

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(ConstantManager.NAME, person.getName() + context.getString(R.string.additional_notification_greetings) + makeGreetings(additionalNotificationOffset));
        intent.putExtra(ConstantManager.TIME_STAMP, person.getTimeStamp());

        long triggerAtMillis = setupCalendarYear(person, additionalNotificationOffset);

        int requestCode = (int) person.getTimeStamp() + REQUEST_CODE_OFFSET;

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(),
                requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
    }

    public void setAlarms(Person person) {
        try {
            setAlarm(person);
            additionalNotificationOffset = Long.parseLong(preferences.getString(ConstantManager.ADDITIONAL_NOTIFICATION_KEY, "0"));
            if (additionalNotificationOffset != 0) {
                setAdditionalAlarm(person);
            }
        } catch (SecurityException e) {
            Toast.makeText(context, R.string.security_exception, Toast.LENGTH_LONG).show();
        }
    }

    private String makeGreetings(long offset) {
        String[] dates = context.getResources().getStringArray(R.array.additional_notification_greetings);
        String[] entryValues = context.getResources().getStringArray(R.array.additional_notification_entry_values);
        String greetings = null;
        for (int i = 1; i < entryValues.length; i++) {
            if (offset == Long.parseLong(entryValues[i])) {
                greetings = dates[i];
            }
        }
        return greetings;
    }

    private void removeAlarm(long timeStamp) {
        Intent intent = new Intent(context, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) timeStamp, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);
    }

    private void removeAdditionalAlarm(long timeStamp) {
        Intent intent = new Intent(context, AlarmReceiver.class);

        int requestCode = (int) timeStamp + REQUEST_CODE_OFFSET;

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);
    }

    public void removeAlarms(long timeStamp) {
        removeAlarm(timeStamp);
        additionalNotificationOffset = Long.parseLong(preferences.getString(ConstantManager.ADDITIONAL_NOTIFICATION_KEY, "0"));
        if (additionalNotificationOffset != 0) {
            removeAdditionalAlarm(timeStamp);
        }
    }

    private long setupCalendarYear(Person person, long offset) {
        long now = Calendar.getInstance().getTimeInMillis();
        long notificationTime = preferences.getLong(ConstantManager.NOTIFICATION_TIME_KEY, defaultNotificationTime);
        Calendar notificationTimeCalendar = Calendar.getInstance();
        notificationTimeCalendar.setTimeInMillis(notificationTime);

        // Notification time setup
        int hour = notificationTimeCalendar.get(Calendar.HOUR_OF_DAY);
        int minutes = notificationTimeCalendar.get(Calendar.MINUTE);

        long date = person.getDate() - offset;
        int year = Calendar.getInstance().get(Calendar.YEAR);

        Calendar calendar = Calendar.getInstance();
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
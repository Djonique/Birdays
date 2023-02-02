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

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import com.eblis.whenwasit.utils.Constants;
import com.eblis.whenwasit.utils.Utils;

import java.util.Calendar;

public class AlarmHelper {
    final private static String TAG = "WhenWasIt::AlarmHelper";

    final private static int BIRTHDAY_CHECKER_REQUEST_CODE = 9991;
    final private long defaultNotificationTime = 645703200000L - Utils.getTimeOffset();
    final private Context context;
    final private AlarmManager alarmManager;
    final private SharedPreferences preferences;

    public AlarmHelper(Context context) {
        this.context = context;
        alarmManager = ((AlarmManager)
                context.getApplicationContext().getSystemService(Context.ALARM_SERVICE));
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private PendingIntent getExistingAlarm(Intent intent) {
        int flags = PendingIntent.FLAG_NO_CREATE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags |= PendingIntent.FLAG_IMMUTABLE;
        }
        return PendingIntent.getBroadcast(
                context.getApplicationContext(),
                BIRTHDAY_CHECKER_REQUEST_CODE,
                intent, flags);
    }

    public void setRecurringAlarm() {
        Log.i(TAG, "About to set recurring alarm");
        final Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction(AlarmHelper.class.getName());

        final long triggerAtMillis = setupAlarmTime();

        final PendingIntent recurringAlarm = getExistingAlarm(intent);
        if (recurringAlarm == null) {
            int flags = PendingIntent.FLAG_UPDATE_CURRENT;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                flags |= PendingIntent.FLAG_MUTABLE;
            }
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                flags |= PendingIntent.FLAG_IMMUTABLE;
            }
            PendingIntent alarm = PendingIntent.getBroadcast(
                    context.getApplicationContext(),
                    BIRTHDAY_CHECKER_REQUEST_CODE,
                    intent,
                    flags);
            setAlarmDependingOnApi(alarmManager, triggerAtMillis, alarm);
        }
    }

    public void removeRecurringAlarm() {
        Log.i(TAG, "About to remove recurring alarm");
        final Intent intent = new Intent(context, AlarmReceiver.class);
        final PendingIntent recurringAlarm = getExistingAlarm(intent);
        if (recurringAlarm != null) {
            alarmManager.cancel(recurringAlarm);
        }
    }

    /**
     * Set up correct alarm for Android API 19 (without delay) and Android API 23+ with Doze
     */
    private void setAlarmDependingOnApi(AlarmManager alarmManager,
                                        long triggerAtMillis,
                                        PendingIntent pendingIntent) {
        Log.i(TAG, "Setting alarm depending on API");
        alarmManager.setInexactRepeating(AlarmManager.RTC, triggerAtMillis, AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    /**
     * Configure time when recurring alarm (which checks for pending notifications) gets triggered at
     */
    private long setupAlarmTime() {
        final long now = Calendar.getInstance().getTimeInMillis();
        final long notificationTime = preferences.getLong(Constants.NOTIFICATION_TIME_KEY, defaultNotificationTime);
        final Calendar notificationTimeCalendar = Calendar.getInstance();
        notificationTimeCalendar.setTimeInMillis(notificationTime);

        final int hour = notificationTimeCalendar.get(Calendar.HOUR_OF_DAY);
        final int minutes = notificationTimeCalendar.get(Calendar.MINUTE);

        final int year = Calendar.getInstance().get(Calendar.YEAR);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.MILLISECOND, 0);
        Log.i(TAG, "Set up a notification for " + String.valueOf(calendar.getTimeInMillis()));
        return calendar.getTimeInMillis();
    }
}
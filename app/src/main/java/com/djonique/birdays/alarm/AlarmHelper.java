package com.djonique.birdays.alarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.ConstantManager;

import java.util.Calendar;

public class AlarmHelper {

    @SuppressLint("StaticFieldLeak")
    private static AlarmHelper instance;
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

    public void setAlarm(Person person) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(ConstantManager.NAME, person.getName());
        intent.putExtra(ConstantManager.TIME_STAMP, person.getTimeStamp());

        long triggerAtMillis = setupCalendarYear(person);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(),
                (int) person.getTimeStamp(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
    }

    public void removeAlarm(long timeStamp) {
        Intent intent = new Intent(context, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) timeStamp, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);
    }

    private long setupCalendarYear(Person person) {
        long now = Calendar.getInstance().getTimeInMillis();

        long notificationTime = preferences.getLong(ConstantManager.NOTIFICATION_TIME, 1486375200000L);
        Calendar notificationTimeCalendar = Calendar.getInstance();
        notificationTimeCalendar.setTimeInMillis(notificationTime);

        int hour = notificationTimeCalendar.get(Calendar.HOUR_OF_DAY);
        int minutes = notificationTimeCalendar.get(Calendar.MINUTE);

        long date = person.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.MILLISECOND, 0);
        if (now > calendar.getTimeInMillis()) {
            calendar.set(Calendar.YEAR, (Calendar.getInstance().get(Calendar.YEAR) + 1));
        }
        return calendar.getTimeInMillis();
    }
}

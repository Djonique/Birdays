package com.djonique.birdays.alarm;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.Utils;

import java.util.Calendar;

public class AlarmHelper {

    @SuppressLint("StaticFieldLeak")
    private static AlarmHelper instance;
    private Context context;
    private AlarmManager alarmManager;

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
    }

    public void setAlarm(Person person) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("name", person.getName());
        intent.putExtra("time_stamp", person.getTimeStamp());

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
        long date = person.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        Log.d("ALARM", "Year " + String.valueOf(calendar.get(Calendar.YEAR)));
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        Log.d("ALARM", "Hour " + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
        calendar.set(Calendar.MINUTE, 30);
        Log.d("ALARM", "Minute " + String.valueOf(calendar.get(Calendar.MINUTE)));
        if (Utils.isRightDate(calendar)) {
            calendar.set(Calendar.YEAR, (Calendar.getInstance().get(Calendar.YEAR) + 1));
            Log.d("ALARM", "Year " + String.valueOf(calendar.get(Calendar.YEAR)));
            Log.d("ALARM", "Hour " + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
            Log.d("ALARM", "Minute " + String.valueOf(calendar.get(Calendar.MINUTE)));
        }
        return calendar.getTimeInMillis();
    }
}

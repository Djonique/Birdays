package com.djonique.birdays.alarm;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.djonique.birdays.model.Person;

public class AlarmHelper {

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

    // TODO: 26.12.2016 одноразовый аларм??
    public void setAlarm(Person person) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("name", person.getName());
        intent.putExtra("date", person.getDate());
        intent.putExtra("time_stamp", person.getTimeStamp());

        /*Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(person.getDate());
        Calendar intentCalendar = Calendar.getInstance();
        intentCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        intentCalendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));*/

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(),
                (int) person.getTimeStamp(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, person.getDate(), pendingIntent);
    }

    public void removeAlarm(long timeStamp) {
        Intent intent = new Intent(context, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) timeStamp, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);
    }
}

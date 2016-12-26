package com.djonique.birdays.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.model.Person;

import java.util.ArrayList;
import java.util.List;

public class AlarmSetter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DBHelper dbHelper = new DBHelper(context);

        AlarmHelper.getInstance().init(context);
        AlarmHelper alarmHelper = AlarmHelper.getInstance();

        List<Person> persons = new ArrayList<>();
        persons.addAll(dbHelper.query().getPersons());

        for (Person person : persons) {
            alarmHelper.setAlarm(person);
        }
    }
}

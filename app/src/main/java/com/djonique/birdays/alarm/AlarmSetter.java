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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.models.Person;

import java.util.ArrayList;
import java.util.List;

public class AlarmSetter extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        DBHelper dbHelper = new DBHelper(context);

        AlarmHelper alarmHelper = new AlarmHelper(context);

        List<Person> persons = new ArrayList<>();
        persons.addAll(dbHelper.query().getPersons());

        for (Person person : persons) {
            alarmHelper.setAlarms(person);
        }
    }
}
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

package com.djonique.birdays.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class DBQueryManager {

    private SQLiteDatabase mDatabase;
    private Person mPerson;
    private List<Person> persons;

    DBQueryManager(SQLiteDatabase database) {
        this.mDatabase = database;
    }

    public Person getPerson(long timeStamp) {

        Cursor cursor = mDatabase.query(DBHelper.DB_PERSONS, null, DBHelper.SELECTION_TIME_STAMP,
                new String[]{Long.toString(timeStamp)}, null, null, null);

        if (cursor.moveToFirst()) {
            String name = getName(cursor);
            long date = getDate(cursor);
            boolean isYearKnown = getYearUnknown(cursor);
            String phoneNumber = getPhoneNumber(cursor);
            String email = getEmail(cursor);

            mPerson = new Person(name, date, isYearKnown, phoneNumber, email, timeStamp);
        }
        cursor.close();

        return mPerson;
    }

    public List<Person> getPersons() {
        persons = new ArrayList<>();

        Cursor cursor = mDatabase.query(DBHelper.DB_PERSONS, null, null, null, null,
                null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = getName(cursor);
                long date = getDate(cursor);
                boolean isYearKnown = getYearUnknown(cursor);
                String phoneNumber = getPhoneNumber(cursor);
                String email = getEmail(cursor);
                long timeStamp = getTimeStamp(cursor);

                mPerson = new Person(name, date, isYearKnown, phoneNumber, email, timeStamp);
                persons.add(mPerson);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return persons;
    }

    public List<Person> getSearchPerson(String selection, String[] selectionArgs, String orderBy) {
        persons = new ArrayList<>();

        Cursor cursor = mDatabase.query(DBHelper.DB_PERSONS, null, selection, selectionArgs, null,
                null, orderBy);

        if (cursor.moveToFirst()) {
            do {
                String name = getName(cursor);
                long date = getDate(cursor);
                boolean isYearKnown = getYearUnknown(cursor);
                String phoneNumber = getPhoneNumber(cursor);
                String email = getEmail(cursor);
                long timeStamp = getTimeStamp(cursor);

                mPerson = new Person(name, date, isYearKnown, phoneNumber, email, timeStamp);
                persons.add(mPerson);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return persons;
    }

    public List<Person> getThisMonthPersons() {
        persons = new ArrayList<>();

        Cursor cursor = mDatabase.query(DBHelper.DB_PERSONS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = getName(cursor);
                long date = getDate(cursor);
                boolean isYearKnown = getYearUnknown(cursor);
                String phoneNumber = getPhoneNumber(cursor);
                String email = getEmail(cursor);
                long timeStamp = getTimeStamp(cursor);

                if (Utils.isCurrentMonth(date)) {
                    mPerson = new Person(name, date, isYearKnown, phoneNumber, email, timeStamp);
                    persons.add(mPerson);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return persons;
    }

    public List<Person> getSearchMonthPerson(String selection, String[] selectionArgs,
                                             String orderBy) {
        persons = new ArrayList<>();

        Cursor cursor = mDatabase.query(DBHelper.DB_PERSONS, null, selection, selectionArgs, null,
                null, orderBy);

        if (cursor.moveToFirst()) {
            do {
                String name = getName(cursor);
                long date = getDate(cursor);
                boolean isYearKnown = getYearUnknown(cursor);
                String phoneNumber = getPhoneNumber(cursor);
                String email = getEmail(cursor);
                long timeStamp = getTimeStamp(cursor);

                if (Utils.isCurrentMonth(date)) {
                    mPerson = new Person(name, date, isYearKnown, phoneNumber, email, timeStamp);
                    persons.add(mPerson);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return persons;
    }

    public List<Person> getFamousBornThisDay(long dayOfBirthday) {
        persons = new ArrayList<>();

        Cursor cursor = mDatabase.query(DBHelper.DB_FAMOUS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = getName(cursor);
                long date = getDate(cursor);

                if (Utils.getMonth(date) == Utils.getMonth(dayOfBirthday) &&
                        Utils.getDay(date) == Utils.getDay(dayOfBirthday)) {
                    mPerson = new Person(name, date);
                    persons.add(mPerson);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return persons;
    }

    private String getName(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
    }

    private long getDate(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_DATE));
    }

    private boolean getYearUnknown(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_IS_YEAR_KNOWN)) == 1;
    }

    private String getPhoneNumber(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PHONE_NUMBER));
    }

    private String getEmail(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EMAIL));
    }

    private long getTimeStamp(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_TIME_STAMP));
    }
}
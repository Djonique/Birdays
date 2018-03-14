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

import com.djonique.birdays.models.AnniversaryType;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class DbQueryManager {

    private SQLiteDatabase database;

    DbQueryManager(SQLiteDatabase database) {
        this.database = database;
    }

    public Person getPerson(long timeStamp) {

        final List<Person> candidates = getPersons(DbHelper.DB_PERSONS, null, DbHelper.SELECTION_TIME_STAMP, new String[]{Long.toString(timeStamp)}, null, null, null, null);

        if (candidates.size() > 0) {
            return candidates.get(0);
        }

        return null;
    }

    public List<Person> getPersons() {
        return getPersons(DbHelper.DB_PERSONS, null, null, null, null, null, null, null);
    }

    public interface Matches {
        public boolean match(Person person);
    }

    private List<Person> getPersons(final String db, final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final Matches matcher) {
        final List<Person> persons = new ArrayList<>();

        final Cursor cursor = database.query(db, columns, selection, selectionArgs, groupBy, having, orderBy);

        if (cursor.moveToFirst()) {
            do {
                final Person person = getPerson(cursor);
                if ((matcher == null) || (matcher.match(person))) {
                    persons.add(getPerson(cursor));
                }

            } while (cursor.moveToNext());
        }
        cursor.close();

        return persons;
    }

    private Person getPerson(final Cursor cursor) {
        final String name = getName(cursor);
        final long date = getDate(cursor);
        final boolean isYearKnown = getYearUnknown(cursor);
        final String phoneNumber = getPhoneNumber(cursor);
        final AnniversaryType anniversaryType = getAnniversaryType(cursor);
        final String label = getAnniversaryLabel(cursor);
        final String email = getEmail(cursor);
        final long timeStamp = getTimeStamp(cursor);

        return new Person(name, date, isYearKnown, phoneNumber, email, label, anniversaryType, timeStamp);
    }

    public List<Person> getSearchPerson(String selection, String[] selectionArgs, String orderBy) {
        return getPersons(DbHelper.DB_PERSONS, null, selection, selectionArgs, null, null, orderBy, null);
    }

    public List<Person> getThisMonthPersons() {
        return getSearchMonthPersons(null, null, null);
    }

    public List<Person> getSearchMonthPersons(String selection, String[] selectionArgs, String orderBy) {
        return getPersons(DbHelper.DB_PERSONS, null, selection, selectionArgs, null, null, orderBy, new Matches() {
            @Override
            public boolean match(Person person) {
                return Utils.isCurrentMonth(person.getDate());
            }
        });
    }

    public List<Person> getFamousBornThisDay(long dayOfBirthday) {
        final List<Person> persons = new ArrayList<>();

        final Cursor cursor = database.query(DbHelper.DB_FAMOUS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                final String name = getName(cursor);
                final long date = getDate(cursor);

                if (Utils.getMonth(date) == Utils.getMonth(dayOfBirthday) && Utils.getDay(date) == Utils.getDay(dayOfBirthday)) {
                    final Person person = new Person(name, date);
                    persons.add(person);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return persons;
    }

    private String getName(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_NAME));
    }

    private long getDate(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex(DbHelper.COLUMN_DATE));
    }

    private boolean getYearUnknown(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_IS_YEAR_KNOWN)) == 1;
    }

    private String getPhoneNumber(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_PHONE_NUMBER));
    }

    private String getAnniversaryLabel(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_ANNIVERSARY_LABEL));
    }

    private AnniversaryType getAnniversaryType(Cursor cursor) {
        return AnniversaryType.valueOf(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_ANNIVERSARY_TYPE)));
    }

    private String getEmail(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_EMAIL));
    }

    private long getTimeStamp(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex(DbHelper.COLUMN_TIME_STAMP));
    }
}
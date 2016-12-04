package com.djonique.birdays.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.djonique.birdays.Utils;
import com.djonique.birdays.model.Person;

import java.util.ArrayList;
import java.util.List;

public class DBQueryManager {

    private SQLiteDatabase database;

    public DBQueryManager(SQLiteDatabase database) {
        this.database = database;
    }

    public Person getPerson(long timeStamp) {
        Person person = null;

        Cursor c = database.query(DBHelper.DB_PERSONS, null, DBHelper.SELECTION_TIME_STAMP,
                new String[]{Long.toString(timeStamp)}, null, null, null);

        if (c.moveToFirst()) {
            String name = c.getString(c.getColumnIndex(DBHelper.COLUMN_NAME));
            long date = c.getLong(c.getColumnIndex(DBHelper.COLUMN_DATE));
            int age = c.getInt(c.getColumnIndex(DBHelper.COLUMN_AGE));
            long phone = c.getLong(c.getColumnIndex(DBHelper.COLUMN_PHONE));
            String email = c.getString(c.getColumnIndex(DBHelper.COLUMN_EMAIL));
            String lowerCaseName = c.getString(c.getColumnIndex(DBHelper.COLUMN_LOWER_CASE_NAME));

            person = new Person(name, date, age, phone, email, timeStamp, lowerCaseName);
        }
        c.close();

        return person;
    }

    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();

        Cursor cursor = database.query(DBHelper.DB_PERSONS, null, null, null, null,
                null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                long date = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_DATE));
                int age = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_AGE));
                long phone = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EMAIL));
                long timeStamp = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_TIME_STAMP));
                String lowerCaseName = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LOWER_CASE_NAME));

                Person person = new Person(name, date, age, phone, email, timeStamp, lowerCaseName);
                persons.add(person);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return persons;
    }

    public List<Person> getSearchPerson(String selection, String[] selectionArgs, String orderBy) {
        List<Person> persons = new ArrayList<>();

        Cursor cursor = database.query(DBHelper.DB_PERSONS, null, selection, selectionArgs, null,
                null, orderBy);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                long date = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_DATE));
                int age = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_AGE));
                long phone = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EMAIL));
                long timeStamp = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_TIME_STAMP));
                String lowerCaseName = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LOWER_CASE_NAME));

                Person person = new Person(name, date, age, phone, email, timeStamp, lowerCaseName);
                persons.add(person);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return persons;
    }

    public List<Person> getThisMonthPersons() {

        List<Person> persons = new ArrayList<>();

        Cursor cursor = database.query(DBHelper.DB_PERSONS, null, null, null, null,
                null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                long date = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_DATE));
                int age = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_AGE));
                long phone = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EMAIL));
                long timeStamp = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_TIME_STAMP));
                String lowerCaseName = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LOWER_CASE_NAME));

                if (Utils.isCurrentMonth(date)) {
                    Person person = new Person(name, date, age, phone, email, timeStamp, lowerCaseName);
                    persons.add(person);
                }

            } while (cursor.moveToNext());
        }
        cursor.close();

        return persons;
    }

    public List<Person> getSearchMonthPerson(String selection, String[] selectionArgs, String orderBy) {
        List<Person> persons = new ArrayList<>();

        Cursor cursor = database.query(DBHelper.DB_PERSONS, null, selection, selectionArgs, null,
                null, orderBy);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                long date = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_DATE));
                int age = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_AGE));
                long phone = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EMAIL));
                long timeStamp = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_TIME_STAMP));
                String lowerCaseName = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LOWER_CASE_NAME));

                if (Utils.isCurrentMonth(date)) {
                    Person person = new Person(name, date, age, phone, email, timeStamp,lowerCaseName);
                    persons.add(person);
                }

            } while (cursor.moveToNext());
        }
        cursor.close();

        return persons;
    }

    public List<Person> getFamousPerson(String selection, String[] selectionArgs, String orderBy) {
        List<Person> persons = new ArrayList<>();

        Cursor cursor = database.query(DBHelper.DB_FAMOUS, null, selection, selectionArgs, null,
                null, orderBy);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                long date = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_DATE));

                if (Utils.isCurrentMonth(date)) {
                    Person person = new Person(name, date);
                    persons.add(person);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return persons;
    }
}

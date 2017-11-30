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

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.Utils;

public class DbHelper extends SQLiteOpenHelper {

    public static final String COLUMN_NAME = "name";
    public static final String SELECTION_LIKE_NAME = COLUMN_NAME + " LIKE ?";

    static final String DB_PERSONS = "persons";
    static final String DB_FAMOUS = "famous";
    static final String COLUMN_DATE = "date";
    static final String COLUMN_IS_YEAR_KNOWN = "is_year_known";
    static final String COLUMN_PHONE_NUMBER = "phone";
    static final String COLUMN_EMAIL = "email";
    static final String COLUMN_TIME_STAMP = "time_stamp";
    static final String SELECTION_TIME_STAMP = COLUMN_TIME_STAMP + " = ?";

    private static final String DB_NAME = "my_db";
    private static final int DATABASE_VERSION = 1;
    private static final String DB_PERSONS_CREATE = "CREATE TABLE " + DB_PERSONS + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_DATE + " INTEGER, "
            + COLUMN_IS_YEAR_KNOWN + " INTEGER, "
            + COLUMN_PHONE_NUMBER + " TEXT, "
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_TIME_STAMP + " INTEGER"
            + ");";
    private static final String DB_FAMOUS_CREATE = "CREATE TABLE " + DB_FAMOUS + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_DATE + " INTEGER"
            + ");";

    private DbQueryManager dbQueryManager;
    private Context context;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbQueryManager = new DbQueryManager(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_PERSONS_CREATE);
        db.execSQL(DB_FAMOUS_CREATE);
        DbFamous.createFamousDb(context, db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            db.beginTransaction();
            try {
                db.execSQL("DROP TABLE IF EXISTS " + DB_FAMOUS);
                db.execSQL(DB_FAMOUS_CREATE);
                DbFamous.createFamousDb(context, db);
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }
    }

    public DbQueryManager query() {
        return dbQueryManager;
    }

    public void addRecord(Person person) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, person.getName());
        cv.put(COLUMN_DATE, person.getDate());
        cv.put(COLUMN_IS_YEAR_KNOWN, Utils.boolToInt(person.isYearUnknown()));
        cv.put(COLUMN_PHONE_NUMBER, person.getPhoneNumber());
        cv.put(COLUMN_EMAIL, person.getEmail());
        cv.put(COLUMN_TIME_STAMP, person.getTimeStamp());
        getWritableDatabase().insert(DB_PERSONS, null, cv);
    }

    public void updateRecord(Person person) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, person.getName());
        cv.put(COLUMN_DATE, person.getDate());
        cv.put(COLUMN_IS_YEAR_KNOWN, Utils.boolToInt(person.isYearUnknown()));
        cv.put(COLUMN_PHONE_NUMBER, person.getPhoneNumber());
        cv.put(COLUMN_EMAIL, person.getEmail());
        getWritableDatabase().update(DB_PERSONS, cv, SELECTION_TIME_STAMP,
                new String[]{String.valueOf(person.getTimeStamp())});
    }

    public void removeRecord(long timeStamp) {
        getWritableDatabase().delete(DB_PERSONS, SELECTION_TIME_STAMP, new String[]{Long.toString(timeStamp)});
    }
}
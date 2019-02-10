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

package com.eblis.whenwasit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.models.AnniversaryType;
import com.eblis.whenwasit.models.Person;
import com.eblis.whenwasit.utils.Utils;

import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static final String COLUMN_CONTACT_ID = "contactId";
    public static final String COLUMN_NAME = "name";
    static final String COLUMN_CATEGORY = "category";
    public static final String SEARCH_QUERY = COLUMN_NAME + " LIKE ? OR " + COLUMN_CATEGORY + " LIKE ?";

    static final String DB_PERSONS = "persons";
    static final String DB_FAMOUS = "famous";
    static final String COLUMN_DATE = "date";
    static final String COLUMN_IS_YEAR_KNOWN = "is_year_known";
    static final String COLUMN_PHONE_NUMBER = "phone";
    static final String COLUMN_ANNIVERSARY_TYPE = "type";
    static final String COLUMN_ANNIVERSARY_LABEL = "label";
    static final String COLUMN_EMAIL = "email";
    static final String COLUMN_TIME_STAMP = "time_stamp";
    static final String SELECTION_TIME_STAMP = COLUMN_TIME_STAMP + " = ?";

    private static final String DB_NAME = "my_db";
    private static final int DATABASE_VERSION = 5;
    private static final String DB_PERSONS_CREATE = "CREATE TABLE " + DB_PERSONS + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CONTACT_ID + " INTEGER, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_DATE + " INTEGER, "
            + COLUMN_IS_YEAR_KNOWN + " INTEGER, "
            + COLUMN_PHONE_NUMBER + " TEXT, "
            + COLUMN_ANNIVERSARY_TYPE + " TEXT, "
            + COLUMN_ANNIVERSARY_LABEL + " TEXT, "
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_CATEGORY + " TEXT, "
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
        if (oldVersion == 2 && newVersion == 3) {
            db.beginTransaction();
            try {
                db.execSQL("ALTER TABLE " + DB_PERSONS +  " ADD COLUMN " + COLUMN_ANNIVERSARY_TYPE + " TEXT DEFAULT '" + AnniversaryType.BIRTHDAY.name() + "'");
                db.execSQL("ALTER TABLE " + DB_PERSONS +  " ADD COLUMN " + COLUMN_ANNIVERSARY_LABEL + " TEXT DEFAULT '" + context.getResources().getString(R.string.birthday) + "'");
                db.setTransactionSuccessful();
            }
            finally {
                db.endTransaction();
            }
        }
        if (oldVersion == 3 && newVersion == 4) {
            db.beginTransaction();
            try {
                db.execSQL("ALTER TABLE " + DB_PERSONS +  " ADD COLUMN " + COLUMN_CONTACT_ID + " INTEGER DEFAULT 0");
                db.setTransactionSuccessful();
            }
            finally {
                db.endTransaction();
            }
        }

        if (oldVersion == 4 && newVersion == DATABASE_VERSION) {
            db.beginTransaction();
            try {
                db.execSQL("ALTER TABLE " + DB_PERSONS + " ADD COLUMN " + COLUMN_CATEGORY + " TEXT DEFAULT 'Friends'");
                db.setTransactionSuccessful();
            }
            finally {
                db.endTransaction();
            }
        }
    }

    public DbQueryManager query() {
        return dbQueryManager;
    }

    private ContentValues getValues(Person person, boolean create) {
        final ContentValues cv = new ContentValues();
        cv.put(COLUMN_CONTACT_ID, person.getContactId());
        cv.put(COLUMN_NAME, person.getName());
        cv.put(COLUMN_DATE, person.getDate().toDateTimeAtCurrentTime().getMillis());
        cv.put(COLUMN_IS_YEAR_KNOWN, Utils.boolToInt(person.isYearUnknown()));
        cv.put(COLUMN_PHONE_NUMBER, person.getPhoneNumber());
        cv.put(COLUMN_ANNIVERSARY_TYPE, person.getAnniversaryType().toString());
        cv.put(COLUMN_ANNIVERSARY_LABEL, person.getAnniversaryLabel());
        cv.put(COLUMN_CATEGORY, person.getContactCategory());
        cv.put(COLUMN_EMAIL, person.getEmail());
        if (create) {
            cv.put(COLUMN_TIME_STAMP, person.getTimeStamp());
        }
        return cv;
    }

    public List<String> getContactCategories() {
        return query().getContactCategories();
    }

    public void addRecord(Person person) {
        getWritableDatabase().insert(DB_PERSONS, null, getValues(person, true));
    }

    public void updateRecord(Person person) {
        int updated = getWritableDatabase().update(DB_PERSONS, getValues(person, false), SELECTION_TIME_STAMP,
                new String[]{String.valueOf(person.getTimeStamp())});
    }

    public void removeRecord(long timeStamp) {
        getWritableDatabase().delete(DB_PERSONS, SELECTION_TIME_STAMP, new String[]{Long.toString(timeStamp)});
    }
}
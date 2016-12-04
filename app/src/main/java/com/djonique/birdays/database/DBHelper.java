package com.djonique.birdays.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.djonique.birdays.model.Person;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = "myDB";
    public static final String DB_PERSONS = "personsDB";
    public static final String DB_FAMOUS = "famousDB";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_TIME_STAMP = "time_stamp";
    public static final String COLUMN_LOWER_CASE_NAME = "lower_case_name";

    public static final String DB_PERSONS_CREATE = "CREATE TABLE " + DB_PERSONS + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_DATE + " INTEGER, "
            + COLUMN_AGE + " INTEGER, "
            + COLUMN_PHONE + " INTEGER, "
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_TIME_STAMP + " INTEGER, "
            + COLUMN_LOWER_CASE_NAME + " TEXT"
            + ");";

    public static final String DB_FAMOUS_CREATE = "CREATE TABLE " + DB_PERSONS + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_DATE + " INTEGER, "
            + ");";

    public static final String SELECTION_TIME_STAMP = COLUMN_TIME_STAMP + " = ?";
    public static final String SELECTION_LIKE_NAME = COLUMN_LOWER_CASE_NAME + " LIKE ?";

    private DBQueryManager dbQueryManager;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        dbQueryManager = new DBQueryManager(getReadableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_PERSONS_CREATE);
        db.execSQL(DB_FAMOUS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE" + DB_PERSONS);
        db.execSQL("DROP TABLE" + DB_FAMOUS);
        onCreate(db);
    }

    public void addRec(Person person) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, person.getName());
        cv.put(COLUMN_DATE, person.getDate());
        cv.put(COLUMN_AGE, person.getAge());
        cv.put(COLUMN_PHONE, person.getPhoneNumber());
        cv.put(COLUMN_EMAIL, person.getEmail());
        cv.put(COLUMN_TIME_STAMP, person.getTimeStamp());
        cv.put(COLUMN_LOWER_CASE_NAME, person.getLowerCaseName());
        getWritableDatabase().insert(DB_PERSONS, null, cv);
    }

     public void addFamous(Person person) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, person.getName());
        cv.put(COLUMN_DATE, person.getDate());
        getWritableDatabase().insert(DB_FAMOUS, null, cv);
     }

    public DBQueryManager query() {
        return dbQueryManager;
    }

    public void removePerson(long timeStamp) {
        getWritableDatabase().delete(DB_PERSONS, SELECTION_TIME_STAMP, new String[]{Long.toString(timeStamp)});
    }
}

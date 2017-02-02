package com.djonique.birdays.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.djonique.birdays.R;
import com.djonique.birdays.models.Person;

import static com.djonique.birdays.database.DBHelper.COLUMN_DATE;
import static com.djonique.birdays.database.DBHelper.COLUMN_NAME;
import static com.djonique.birdays.database.DBHelper.DB_FAMOUS;

class DBFamous {
    static void createDB(Context context, SQLiteDatabase db) {

        // 1 january
        addFamous(db, new Person(context.getString(R.string.medici), -16440364800000L));
        addFamous(db, new Person(context.getString(R.string.giordano_bruno), -13316227200000L));
        addFamous(db, new Person(context.getString(R.string.coubertin), -3376598400000L));
        addFamous(db, new Person(context.getString(R.string.william_fox), -2871676800000L));

        // 1 february
        addFamous(db, new Person(context.getString(R.string.john_ford), -2395612800000L));
        // 16 december
        addFamous(db, new Person(context.getString(R.string.beethoven), -6281193600000L));
        // 17 december
        addFamous(db, new Person(context.getString(R.string.humphry_davy), -6028646400000L));
        addFamous(db, new Person(context.getString(R.string.franciscus), -1042675200000L));
        // 23 january
        addFamous(db, new Person(context.getString(R.string.manet), -4353004800000L));
    }

    private static void addFamous(SQLiteDatabase db, Person person) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, person.getName());
        cv.put(COLUMN_DATE, person.getDate());
        db.insert(DB_FAMOUS, null, cv);
    }
}

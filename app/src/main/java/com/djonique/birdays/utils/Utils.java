package com.djonique.birdays.utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils {

    private static Calendar today = Calendar.getInstance();
    private static Calendar dayOfBirthday = Calendar.getInstance();

    public static String getDate(long date) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
        return dateFormat.format(date);
    }

    private static int getDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    private static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH);
    }

    private static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    public static int getAge(long date) {
        dayOfBirthday.setTimeInMillis(date);

        int age = getYear(today) - getYear(dayOfBirthday);

        if (getMonth(today) < getMonth(dayOfBirthday)) {
            age--;
        } else if (getMonth(today) == getMonth(dayOfBirthday) &&
                getDay(today) < getDay(dayOfBirthday)) {
            age--;
        }
        return age;
    }

    public static boolean isCurrentMonth(long date) {
        boolean thisMonth = false;

        dayOfBirthday.setTimeInMillis(date);

        if (getMonth(dayOfBirthday) == getMonth(today)) {
            thisMonth = true;
        }
        return thisMonth;
    }

    public static boolean isCurrentDay(long date) {
        boolean thisDay = false;

        dayOfBirthday.setTimeInMillis(date);

        if (getDay(dayOfBirthday) == getDay(today) && isCurrentMonth(date)) {
            thisDay = true;
        }
        return thisDay;
    }
}

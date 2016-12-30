package com.djonique.birdays.utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils {

    public static String getDate(long date) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
        return dateFormat.format(date);
    }

    public static int getAge(long date) {
        Calendar birthdayDate = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        birthdayDate.setTimeInMillis(date);

        int age = today.get(Calendar.YEAR) - birthdayDate.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birthdayDate.get(Calendar.MONTH)) {
            age--;
        } else if ((today.get(Calendar.MONTH) == birthdayDate.get(Calendar.MONTH)) &&
                (today.get(Calendar.DAY_OF_MONTH) < birthdayDate.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }
        return age;
    }

    public static boolean isCurrentMonth(long date) {
        boolean thisMonth = false;

        Calendar today = Calendar.getInstance();
        Calendar dayOfBirthday = Calendar.getInstance();

        dayOfBirthday.setTimeInMillis(date);

        if (dayOfBirthday.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
            thisMonth = true;
        }

        return thisMonth;
    }

    public static boolean isCurrentDay(long date) {
        boolean thisDay = false;

        Calendar today = Calendar.getInstance();
        Calendar dayOfBirthday = Calendar.getInstance();

        dayOfBirthday.setTimeInMillis(date);

        if (dayOfBirthday.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH) &&
                isCurrentMonth(date)) {
            thisDay = true;
        }

        return thisDay;
    }
}

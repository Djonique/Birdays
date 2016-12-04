package com.djonique.birdays;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils {

    public static String getDate(long date) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
        return dateFormat.format(date);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//        return sdf.format(date);
    }

    // TODO не меняется возраст после даты дня рождения, возможно надо обновлять возраст в БД при каждой загрузке.
    public static int getAge(int year, int month, int day) {
        Calendar dayOfBirthday = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dayOfBirthday.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dayOfBirthday.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < dayOfBirthday.get(Calendar.MONTH)) {
            age--;
        } else if ((today.get(Calendar.MONTH) == dayOfBirthday.get(Calendar.MONTH)) &&
                (today.get(Calendar.DAY_OF_MONTH) < dayOfBirthday.get(Calendar.DAY_OF_MONTH))) {
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
}

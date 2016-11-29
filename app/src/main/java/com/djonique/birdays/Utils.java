package com.djonique.birdays;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

    // TODO разобраться с Locale
    public static String getDate(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }

    // TODO не меняется возраст после даты дня рождения, возможно надо обновлять возраст в БД при каждой загрузке.
    public static int getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
            age--;
        } else if ((today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)) &&
                (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }

    public static boolean isCurrentMonth(long date) {

        boolean thisMonth = false;

        Calendar today = Calendar.getInstance();
        Calendar personsBD = Calendar.getInstance();

        personsBD.setTimeInMillis(date);


        if (personsBD.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
            thisMonth = true;
        }

        return thisMonth;
    }
}

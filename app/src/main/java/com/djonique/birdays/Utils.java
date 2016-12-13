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

    public static int getAgeCircleColor(int age) {
        int ageCircleColorResID;
        if (age < 10) ageCircleColorResID = R.color.age1;
        else if (age >= 10 && age < 20) ageCircleColorResID = R.color.age2;
        else if (age >= 20 && age < 30) ageCircleColorResID = R.color.age3;
        else if (age >= 30 && age < 40) ageCircleColorResID = R.color.age4;
        else if (age >= 40 && age < 50) ageCircleColorResID = R.color.age5;
        else if (age >= 50 && age < 60) ageCircleColorResID = R.color.age6;
        else if (age >= 60 && age < 70) ageCircleColorResID = R.color.age7;
        else ageCircleColorResID = R.color.age8;
        return ageCircleColorResID;
    }
}

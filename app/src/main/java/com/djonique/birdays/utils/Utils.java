package com.djonique.birdays.utils;

import android.text.TextUtils;
import android.widget.EditText;

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

    public static boolean isEmptyDate(EditText editText) {
        return TextUtils.isEmpty(editText.getText().toString());
    }

    public static boolean isRightDate(Calendar calendar) {
        long today = Calendar.getInstance().getTimeInMillis();
        return today >= calendar.getTimeInMillis();
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

    public static String getZodiac(long date) {
        String zodiac = null;
        dayOfBirthday.setTimeInMillis(date);

        switch (getMonth(dayOfBirthday)) {
            case Calendar.JANUARY:
                zodiac = getDay(dayOfBirthday) <= 20 ? "Capricorn" : "Aquarius";
                break;
            case Calendar.FEBRUARY:
                zodiac = getDay(dayOfBirthday) <= 19 ? "Aquarius" : "Pisces";
                break;
            case Calendar.MARCH:
                zodiac = getDay(dayOfBirthday) <= 20 ? "Pisces" : "Aries";
                break;
            case Calendar.APRIL:
                zodiac = getDay(dayOfBirthday) <= 20 ? "Aries" : "Taurus";
                break;
            case Calendar.MAY:
                zodiac = getDay(dayOfBirthday) <= 21 ? "Taurus" : "Gemini";
                break;
            case Calendar.JUNE:
                zodiac = getDay(dayOfBirthday) <= 21 ? "Gemini" : "Cancer";
                break;
            case Calendar.JULY:
                zodiac = getDay(dayOfBirthday) <= 23 ? "Cancer" : "Leo";
                break;
            case Calendar.AUGUST:
                zodiac = getDay(dayOfBirthday) <= 23 ? "Leo" : "Virgo";
                break;
            case Calendar.SEPTEMBER:
                zodiac = getDay(dayOfBirthday) <= 23 ? "Virgo" : "Libra";
                break;
            case Calendar.OCTOBER:
                zodiac = getDay(dayOfBirthday) <= 23 ? "Libra" : "Scorpio";
                break;
            case Calendar.NOVEMBER:
                zodiac = getDay(dayOfBirthday) <= 22 ? "Scorpio" : "Sagittarius";
                break;
            case Calendar.DECEMBER:
                zodiac = getDay(dayOfBirthday) <= 21 ? "Sagittarius" : "Capricorn";
                break;

        }
        return zodiac;
    }

    public static String getZodiacImage(long date) {
        String zodiacImage = null;

        switch (getZodiac(date)) {
            case "Aries":
                zodiacImage = "\u2648";
                break;
            case "Taurus":
                zodiacImage = "\u2649";
                break;
            case "Gemini":
                zodiacImage = "\u264A";
                break;
            case "Cancer":
                zodiacImage = "\u264B";
                break;
            case "Leo":
                zodiacImage = "\u264C";
                break;
            case "Virgo":
                zodiacImage = "\u264D";
                break;
            case "Libra":
                zodiacImage = "\u264E";
                break;
            case "Scorpio":
                zodiacImage = "\u264F";
                break;
            case "Sagittarius":
                zodiacImage = "\u2650";
                break;
            case "Capricorn":
                zodiacImage = "\u2651";
                break;
            case "Aquarius":
                zodiacImage = "\u2652";
                break;
            case "Pisces":
                zodiacImage = "\u2653";
                break;
        }

        return zodiacImage;
    }
}

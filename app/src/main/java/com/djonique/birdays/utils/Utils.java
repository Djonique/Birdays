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

package com.djonique.birdays.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.widget.EditText;

import com.djonique.birdays.R;
import com.djonique.birdays.models.Person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    private static final int MILLISECONDS_IN_A_DAY = 86400000;
    private static Calendar today = Calendar.getInstance();
    private static Calendar dayOfBirthday = Calendar.getInstance();

    public static String getDate(long date) {
        return DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault()).format(date);
    }

    /**
     * Returns date without year
     */
    public static String getDateWithoutYear(long date) {
        Locale locale = Locale.getDefault();
        SimpleDateFormat sdf = ((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.DEFAULT, locale));
        String pattern = sdf.toPattern();
        String yearlessPattern = pattern.replaceAll("([^\\p{Alpha}']|('[\\p{Alpha}]+'))*y+([^\\p{Alpha}']|('[\\p{Alpha}]+'))*", "");
        SimpleDateFormat yearlessFormat = new SimpleDateFormat(yearlessPattern, locale);
        return yearlessFormat.format(date);
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

    public static int getDay(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.MONTH);
    }

    public static long getTimeOffset() {
        return TimeZone.getDefault().getOffset(today.getTimeInMillis());
    }

    /**
     * Returns age
     */
    public static int getAge(long date) {
        dayOfBirthday.setTimeInMillis(date);

        int age = getYear(today) - getYear(dayOfBirthday);

        if (getMonth(today) < getMonth(dayOfBirthday)) {
            age--;
        } else if (getMonth(today) == getMonth(dayOfBirthday) &&
                getDay(today) < getDay(dayOfBirthday)) {
            age--;
        }
        return age + 1;
    }

    public static String daysLeft(Context context, long date) {
        Calendar birthday = Calendar.getInstance();
        birthday.setTimeInMillis(date);
        if (getMonth(today) == getMonth(birthday)
                && getDay(today) == getDay(birthday)) return context.getString(R.string.today);
        birthday.set(Calendar.HOUR_OF_DAY, 10);
        today.set(Calendar.HOUR_OF_DAY, 9);
        if (getMonth(today) < getMonth(birthday)
                || (getMonth(today) == getMonth(birthday) && getDay(today) <= getDay(birthday))) {
            birthday.set(Calendar.YEAR, today.get(Calendar.YEAR));
        } else {
            birthday.set(Calendar.YEAR, today.get(Calendar.YEAR) + 1);
        }
        long diffDays = (birthday.getTimeInMillis() - today.getTimeInMillis()) / MILLISECONDS_IN_A_DAY;
        return String.valueOf(diffDays);
    }

    public static String daysSinceBirthday(long date) {
        Calendar birthday = Calendar.getInstance();
        birthday.setTimeInMillis(date);
        birthday.set(Calendar.HOUR_OF_DAY, 9);
        today.set(Calendar.HOUR_OF_DAY, 10);
        long diffDays = (today.getTimeInMillis() - birthday.getTimeInMillis()) / MILLISECONDS_IN_A_DAY;
        return String.valueOf(diffDays);
    }

    public static boolean isEmptyDate(EditText editText) {
        return TextUtils.isEmpty(editText.getText().toString());
    }

    /**
     * Checks if date of person's birthday is not in the future
     */
    public static boolean isRightDate(Calendar calendar) {
        return Calendar.getInstance().getTimeInMillis() >= calendar.getTimeInMillis();
    }

    /**
     * Checks if today is the same month with given date
     */
    public static boolean isCurrentMonth(long date) {
        boolean thisMonth = false;

        dayOfBirthday.setTimeInMillis(date);

        if (getMonth(dayOfBirthday) == getMonth(today)) {
            thisMonth = true;
        }
        return thisMonth;
    }

    /**
     * Returns zodiac name of certain date
     */
    public static int getZodiacId(long date) {
        int resId = 0;
        dayOfBirthday.setTimeInMillis(date);

        switch (getMonth(dayOfBirthday)) {
            case Calendar.JANUARY:
                resId = getDay(dayOfBirthday) < 21 ? R.string.capricorn : R.string.aquarius;
                break;
            case Calendar.FEBRUARY:
                resId = getDay(dayOfBirthday) < 20 ? R.string.aquarius : R.string.pisces;
                break;
            case Calendar.MARCH:
                resId = getDay(dayOfBirthday) < 21 ? R.string.pisces : R.string.aries;
                break;
            case Calendar.APRIL:
                resId = getDay(dayOfBirthday) < 21 ? R.string.aries : R.string.taurus;
                break;
            case Calendar.MAY:
                resId = getDay(dayOfBirthday) < 22 ? R.string.taurus : R.string.gemini;
                break;
            case Calendar.JUNE:
                resId = getDay(dayOfBirthday) < 22 ? R.string.gemini : R.string.cancer;
                break;
            case Calendar.JULY:
                resId = getDay(dayOfBirthday) < 23 ? R.string.cancer : R.string.leo;
                break;
            case Calendar.AUGUST:
                resId = getDay(dayOfBirthday) < 23 ? R.string.leo : R.string.virgo;
                break;
            case Calendar.SEPTEMBER:
                resId = getDay(dayOfBirthday) < 24 ? R.string.virgo : R.string.libra;
                break;
            case Calendar.OCTOBER:
                resId = getDay(dayOfBirthday) < 24 ? R.string.libra : R.string.scorpio;
                break;
            case Calendar.NOVEMBER:
                resId = getDay(dayOfBirthday) < 23 ? R.string.scorpio : R.string.sagittarius;
                break;
            case Calendar.DECEMBER:
                resId = getDay(dayOfBirthday) < 22 ? R.string.sagittarius : R.string.capricorn;
                break;
        }
        return resId;
    }

    /**
     * Returns image of certain zodiac
     */
    public static int getZodiacImage(int resId) {
        int zodiacImage = 0;
        switch (resId) {
            case R.string.aries:
                zodiacImage = R.drawable.ic_aries;
                break;
            case R.string.taurus:
                zodiacImage = R.drawable.ic_taurus;
                break;
            case R.string.gemini:
                zodiacImage = R.drawable.ic_gemini;
                break;
            case R.string.cancer:
                zodiacImage = R.drawable.ic_cancer;
                break;
            case R.string.leo:
                zodiacImage = R.drawable.ic_leo;
                break;
            case R.string.virgo:
                zodiacImage = R.drawable.ic_virgo;
                break;
            case R.string.libra:
                zodiacImage = R.drawable.ic_libra;
                break;
            case R.string.scorpio:
                zodiacImage = R.drawable.ic_scorpio;
                break;
            case R.string.sagittarius:
                zodiacImage = R.drawable.ic_sagittarius;
                break;
            case R.string.capricorn:
                zodiacImage = R.drawable.ic_capricorn;
                break;
            case R.string.aquarius:
                zodiacImage = R.drawable.ic_aquarius;
                break;
            case R.string.pisces:
                zodiacImage = R.drawable.ic_pisces;
                break;
        }
        return zodiacImage;
    }

    /**
     * Converts boolean to int
     */
    public static int boolToInt(boolean isYearKnown) {
        return isYearKnown ? 1 : 0;
    }

    /**
     * Formats contacts birthday date from "yyyy-MM-dd" to long
     */
    static long formatDateToLong(String dateString) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(0);
        String[] dateArray = dateString.split("-");

        if (dateArray[0].equals("")) {
            calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[2]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[3]));
        } else if (Integer.parseInt(dateArray[2]) < 32) {
            calendar.set(Calendar.YEAR, Integer.parseInt(dateArray[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[2]));
        }
        return calendar.getTimeInMillis();
    }

    static boolean isYearKnown(String dateString) {
        String[] dateArray = dateString.split("-");
        return dateArray[0].equals("");
    }

    /**
     * Checks if person with the same name already exists in database
     */
    static boolean isPersonAlreadyInDB(Person person, List<Person> list) {
        boolean found = false;
        for (Person dbPerson : list) {
            if (person.equals(dbPerson)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public static void setupDayNightTheme(SharedPreferences preferences) {
        boolean nightMode = preferences.getBoolean(ConstantManager.NIGHT_MODE_KEY, false);
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /*public static String setLetters(String fullName) {
        String[] names = fullName.split(" ");
        String letters = "";
        for (int i = 0; i < names.length; i++) {
            if (i == 2) break;
            if (names[i].length() > 0) letters += names[i].charAt(0);
        }
        return letters.toUpperCase();
    }*/
}
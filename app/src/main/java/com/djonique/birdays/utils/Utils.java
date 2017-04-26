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

import android.text.TextUtils;
import android.widget.EditText;

import com.djonique.birdays.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils {

    private static Calendar today = Calendar.getInstance();
    private static Calendar dayOfBirthday = Calendar.getInstance();

    // returns date
    public static String getDate(long date) {
        return DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault()).format(date);
    }

    // returns date without year
    public static String getUnknownDate(long date) {
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

    // returns age of person
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

    // checks if EditText is empty
    public static boolean isEmptyDate(EditText editText) {
        return TextUtils.isEmpty(editText.getText().toString());
    }

    // checks if date of persons birthday is not in the future
    public static boolean isRightDate(Calendar calendar) {
        long today = Calendar.getInstance().getTimeInMillis();
        return today >= calendar.getTimeInMillis();
    }

    // checks if today is the same month with given date
    public static boolean isCurrentMonth(long date) {
        boolean thisMonth = false;

        dayOfBirthday.setTimeInMillis(date);

        if (getMonth(dayOfBirthday) == getMonth(today)) {
            thisMonth = true;
        }
        return thisMonth;
    }

    // checks if today is the same day with given date
    public static boolean isCurrentDay(long date) {
        boolean thisDay = false;

        dayOfBirthday.setTimeInMillis(date);

        if (getDay(dayOfBirthday) == getDay(today) && isCurrentMonth(date)) {
            thisDay = true;
        }
        return thisDay;
    }

    // returns zodiac name of certain date
    public static int getZodiacId(long date) {
        int resId = 0;
        dayOfBirthday.setTimeInMillis(date);

        switch (getMonth(dayOfBirthday)) {
            case Calendar.JANUARY:
                resId = getDay(dayOfBirthday) <= 20 ? R.string.capricorn : R.string.aquarius;
                break;
            case Calendar.FEBRUARY:
                resId = getDay(dayOfBirthday) <= 19 ? R.string.aquarius : R.string.pisces;
                break;
            case Calendar.MARCH:
                resId = getDay(dayOfBirthday) <= 20 ? R.string.pisces : R.string.aries;
                break;
            case Calendar.APRIL:
                resId = getDay(dayOfBirthday) <= 20 ? R.string.aries : R.string.taurus;
                break;
            case Calendar.MAY:
                resId = getDay(dayOfBirthday) <= 21 ? R.string.taurus : R.string.gemini;
                break;
            case Calendar.JUNE:
                resId = getDay(dayOfBirthday) <= 21 ? R.string.gemini : R.string.cancer;
                break;
            case Calendar.JULY:
                resId = getDay(dayOfBirthday) <= 23 ? R.string.cancer : R.string.leo;
                break;
            case Calendar.AUGUST:
                resId = getDay(dayOfBirthday) <= 23 ? R.string.leo : R.string.virgo;
                break;
            case Calendar.SEPTEMBER:
                resId = getDay(dayOfBirthday) <= 23 ? R.string.virgo : R.string.libra;
                break;
            case Calendar.OCTOBER:
                resId = getDay(dayOfBirthday) <= 23 ? R.string.libra : R.string.scorpio;
                break;
            case Calendar.NOVEMBER:
                resId = getDay(dayOfBirthday) <= 22 ? R.string.scorpio : R.string.sagittarius;
                break;
            case Calendar.DECEMBER:
                resId = getDay(dayOfBirthday) <= 21 ? R.string.sagittarius : R.string.capricorn;
                break;
        }
        return resId;
    }

    // returns image of certain zodiac
    public static String getZodiacImage(int resId) {
        String zodiacImage = null;
        switch (resId) {
            case R.string.aries:
                zodiacImage = "\u2648";
                break;
            case R.string.taurus:
                zodiacImage = "\u2649";
                break;
            case R.string.gemini:
                zodiacImage = "\u264A";
                break;
            case R.string.cancer:
                zodiacImage = "\u264B";
                break;
            case R.string.leo:
                zodiacImage = "\u264C";
                break;
            case R.string.virgo:
                zodiacImage = "\u264D";
                break;
            case R.string.libra:
                zodiacImage = "\u264E";
                break;
            case R.string.scorpio:
                zodiacImage = "\u264F";
                break;
            case R.string.sagittarius:
                zodiacImage = "\u2650";
                break;
            case R.string.capricorn:
                zodiacImage = "\u2651";
                break;
            case R.string.aquarius:
                zodiacImage = "\u2652";
                break;
            case R.string.pisces:
                zodiacImage = "\u2653";
                break;
        }
        return zodiacImage;
    }

    // converts boolean to int
    public static int boolToInt(boolean isYearKnown) {
        return isYearKnown ? 1 : 0;
    }
}

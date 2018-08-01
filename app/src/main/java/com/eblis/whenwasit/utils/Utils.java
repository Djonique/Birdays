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

package com.eblis.whenwasit.utils;

import android.appwidget.AppWidgetManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.models.DisplayedAge;
import com.eblis.whenwasit.models.Person;
import com.eblis.whenwasit.widget.WidgetProvider;

import org.joda.time.LocalDate;
import org.joda.time.Days;
import org.joda.time.Period;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    public static int BIRTHDAY_CLOSE_COLORS_DAYS = 1;
    private static Calendar today = Calendar.getInstance();

    private static String getDateFormat() {
        Locale locale = Locale.getDefault();
        SimpleDateFormat sdf = ((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.DEFAULT, locale));
        return sdf.toPattern();
    }

    public static String getDate(LocalDate date) {
        return date != null ? date.toString(getDateFormat()) : "";
    }

    /**
     * Returns date without year
     */
    public static String getDateWithoutYear(LocalDate date) {
        String yearlessPattern = getDateFormat().replaceAll("([^\\p{Alpha}']|('[\\p{Alpha}]+'))*y+([^\\p{Alpha}']|('[\\p{Alpha}]+'))*", "");
        return date != null ? date.toString(yearlessPattern) : "";
    }

    public static long getTimeOffset() {
        return TimeZone.getDefault().getOffset(today.getTimeInMillis());
    }

    /**
     * Determines the way the age should be displayed.
     * @param candidate
     * @return
     */
    public static DisplayedAge getDisplayedAge(String candidate) {
        //need to support both indexes and strings
        DisplayedAge[] values = DisplayedAge.values();
        for (DisplayedAge value : values) {
            if (candidate.equals(value.name())) {
                return value;
            }
        }
        try {
            //0 current, 1 future
            Integer index = Integer.parseInt(candidate);
            return values[index];
        }
        catch (NumberFormatException nfex)
        {
            //pass
        }

        return DisplayedAge.CURRENT;
    }

    public static int getAge(LocalDate date, DisplayedAge displayedAge) {
        final LocalDate now = new LocalDate();
        switch (displayedAge) {
            default:
            case CURRENT:
                return new Period(date, now).getYears();
            case TURNING:
                return now.getYear() - date.getYear();
            case FUTURE:
                return new Period(date, now.plusYears(1)).getYears();
        }
    }

    public static int daysUntilNextBirthday(final LocalDate dob, final LocalDate now) {
        LocalDate today = new LocalDate(now);
        LocalDate birthday = new LocalDate(dob).withYear(today.getYear());

        int diff = Days.daysBetween(today, birthday).getDays();
        if (diff < 0) { //birthday already passed
            birthday = birthday.plusYears(1);
            diff = Days.daysBetween(today, birthday).getDays();
        }
        return diff;
    }

    public static int daysLeft(final Person person) {
        return daysUntilNextBirthday(person.getDate(), new LocalDate());
    }

    public static String daysLeftPretty(Context context, Person person) {
        int daysToBirthday = daysLeft(person);
        if (daysToBirthday == 0) {
            return context.getString(R.string.today);
        }
        return String.valueOf(daysToBirthday);
    }

    public static String daysSinceBirthday(LocalDate date) {
        final LocalDate today = new LocalDate();
        return String.valueOf(Days.daysBetween(date, today).getDays());
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
    public static boolean isCurrentMonth(LocalDate date) {
        final LocalDate now = new LocalDate();
        return now.getMonthOfYear() == date.getMonthOfYear();
    }

    public static boolean isBirthdayPassed(LocalDate date) {
        LocalDate now = new LocalDate();
        return now.compareTo(date.withYear(now.getYear())) > 0;
    }

    /**
     * Returns zodiac name of certain date
     */
    public static int getZodiacId(LocalDate date) {
        int resId = 0;
        final int dayOfBirthday = date.getDayOfMonth();
        switch (date.getMonthOfYear()) {
            case 1:
                resId = dayOfBirthday < 21 ? R.string.capricorn : R.string.aquarius;
                break;
            case 2:
                resId = dayOfBirthday < 20 ? R.string.aquarius : R.string.pisces;
                break;
            case 3:
                resId = dayOfBirthday < 21 ? R.string.pisces : R.string.aries;
                break;
            case 4:
                resId = dayOfBirthday < 21 ? R.string.aries : R.string.taurus;
                break;
            case 5:
                resId = dayOfBirthday < 22 ? R.string.taurus : R.string.gemini;
                break;
            case 6:
                resId = dayOfBirthday < 22 ? R.string.gemini : R.string.cancer;
                break;
            case 7:
                resId = dayOfBirthday < 23 ? R.string.cancer : R.string.leo;
                break;
            case 8:
                resId = dayOfBirthday < 23 ? R.string.leo : R.string.virgo;
                break;
            case 9:
                resId = dayOfBirthday < 24 ? R.string.virgo : R.string.libra;
                break;
            case 10:
                resId = dayOfBirthday < 24 ? R.string.libra : R.string.scorpio;
                break;
            case 11:
                resId = dayOfBirthday < 23 ? R.string.scorpio : R.string.sagittarius;
                break;
            case 12:
                resId = dayOfBirthday < 22 ? R.string.sagittarius : R.string.capricorn;
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

    public static int getBackgroundColor(Context context, int daysToBirthday) {
        final int resource;
        switch ((daysToBirthday)) {
            case 0:
                resource = R.color.cardview_birthday;
                break;
            case 1:
                resource = R.color.cardview_tomorrow;
                break;
            default:
                resource = R.color.cardview_background;
                break;
        }
        return context.getResources().getColor(resource);

    }

    public static int getNotificationColor(Context context, int daysToBirthday) {
        final int resource;
        switch ((daysToBirthday)) {
            case 0:
                resource = R.color.birthday_today;
                break;
            case 1:
                resource = R.color.birthday_tomorrow;
                break;
            default:
                resource = R.color.birthday_near;
                break;
        }
        return context.getResources().getColor(resource);
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

    static boolean isYearUnknown(String dateString) {
        String[] dateArray = dateString.split("-");
        return dateArray[0].equals("");
    }

    /**
     * Checks if person with the same name already exists in database
     */
    public static boolean isPersonAlreadyInDb(Person person, List<Person> list) {
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
        boolean nightMode = preferences.getBoolean(Constants.NIGHT_MODE_KEY, false);
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public static void openBrowser(Context context, String link) {
        try {
            context.startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(link)), null));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, R.string.web_search_error, Toast.LENGTH_SHORT).show();
        }
    }

    public static void notifyWidget(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetI‌​ds = appWidgetManager.getAppWidgetIds(new ComponentName(context, WidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetI‌​ds, R.id.listview_widget);
    }
}
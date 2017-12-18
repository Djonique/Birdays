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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Calendar;

public class DatePickerManager {

    private static final String DATE_PICKER_FRAGMENT_TAG = "DATE_PICKER_FRAGMENT_TAG";

    private Activity activity;
    private Calendar calendar;
    private SharedPreferences preferences;

    public DatePickerManager(Activity activity, Calendar calendar) {
        this.activity = activity;
        this.calendar = calendar;
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    /**
     * Shows material calendar on API 24 devices instead of spinner
     */
    public void showDialog(DatePickerDialog.OnDateSetListener androidCallback,
                           com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener libCallback) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            showMdtpDialog(libCallback);
        } else {
            showDatePickerDialog(androidCallback);
        }
    }

    /**
     * Configures android DatePickerDialog
     */
    private void showDatePickerDialog(DatePickerDialog.OnDateSetListener callback) {
        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(
                activity,
                setTheme(isNightMode()),
                callback,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /**
     * Configures DatePickerDialog by Wouter Dullaert
     */
    private void showMdtpDialog(com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener callback) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd =
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                        callback,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
        dpd.setVersion(com.wdullaer.materialdatetimepicker.date.DatePickerDialog.Version.VERSION_1);
        dpd.setThemeDark(preferences.getBoolean(Constants.NIGHT_MODE_KEY, false));
        dpd.show(activity.getFragmentManager(), DATE_PICKER_FRAGMENT_TAG);
    }

    /**
     * Set correct theme for day/night modes
     */
    private int setTheme(boolean nightMode) {
        if (nightMode) {
            return AlertDialog.THEME_HOLO_DARK;
        } else {
            return AlertDialog.THEME_HOLO_LIGHT;
        }
    }

    private boolean isNightMode() {
        return preferences.getBoolean(Constants.NIGHT_MODE_KEY, false);
    }
}
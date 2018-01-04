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

package com.djonique.birdays.activities;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.djonique.birdays.R;
import com.djonique.birdays.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpActivity extends AppCompatActivity {

    private static final String HUAWEI = "huawei";
    private static final String SAMSUNG = "samsung";
    private static final String XIAOMI = "xiaomi";
    private static final String ALERT_SHOWED = "ALERT_SHOWED";
    private static final String ACTIVITY_NOT_FOUND_EXCEPTION = "ActivityNotFoundException";

    @BindView(R.id.button_help_whitelist)
    AppCompatButton btnOpenWhitelist;
    @BindView(R.id.button_help_settings)
    AppCompatButton btnOpenSettings;
    @BindView(R.id.button_help_email)
    AppCompatButton btnSendEmail;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        btnOpenWhitelist.setText(R.string.help_whitelist);
        btnOpenSettings.setText(R.string.help_settings);
        btnSendEmail.setText(R.string.send_email);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (!preferences.getBoolean(ALERT_SHOWED, false)) {
            checkManufacturer(Build.MANUFACTURER.toLowerCase());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_primary_in, R.anim.activity_secondary_out);
    }

    private void checkManufacturer(String manufacturer) {
        switch (manufacturer) {
            case HUAWEI:
                showAlertSnackbar(getString(R.string.huawei_header), getString(R.string.huawei_description));
                break;
            case SAMSUNG:
                showAlertSnackbar(getString(R.string.samsung_header), getString(R.string.samsung_description));
                break;
            case XIAOMI:
                showAlertSnackbar(getString(R.string.xiaomi_header), getString(R.string.xiaomi_description));
                break;
        }
    }

    private void showAlertSnackbar(final String title, final String message) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.ll_help_activity), title, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.show_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showAlertDialog(title, message);
                        preferences.edit().putBoolean(ALERT_SHOWED, true).apply();
                    }
                });
        snackbar.setActionTextColor(Color.WHITE);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(this, R.color.red_alert));
        TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setAllCaps(true);
        snackbar.show();
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @OnClick(R.id.button_help_whitelist)
    void openBatteryOptimization() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                startActivity(Intent.createChooser(new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS), null));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, ACTIVITY_NOT_FOUND_EXCEPTION, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, R.string.help_whitelist_error, Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.button_help_settings)
    void openSettings() {
        startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
    }

    @OnClick(R.id.button_help_email)
    void sendEmail() {
        String email = getString(R.string.birdays_email);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType(Constants.TYPE_EMAIL);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, Build.MANUFACTURER + "/"
                + Build.MODEL + "/"
                + Build.VERSION.SDK_INT + "/"
                + getString(R.string.version_name));
        intent.setData(Uri.parse(Constants.MAILTO + email));
        startActivity(Intent.createChooser(intent, null));
    }
}
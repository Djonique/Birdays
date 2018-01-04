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

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.djonique.birdays.R;
import com.djonique.birdays.alarm.AlarmHelper;
import com.djonique.birdays.backup.ExportHelper;
import com.djonique.birdays.backup.RecoverHelper;
import com.djonique.birdays.database.DbHelper;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.Constants;
import com.djonique.birdays.utils.ContactsHelper;
import com.djonique.birdays.utils.PermissionManager;
import com.djonique.birdays.utils.Utils;

import java.util.List;

public class SettingsActivity extends AppCompatActivity implements ContactsHelper.LoadingContactsListener {

    private static final String DO_NOT_SHOW_OPTIMIZATION_ALERT = "DO_NOT_SHOW_OPTIMIZATION_ALERT";
    private static final int FILE_MANAGER = 5;

    private AppCompatCheckBox cbDoNotShowAgain;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BirdaysPreferenceFragment fragment = new BirdaysPreferenceFragment();
        getFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!preferences.getBoolean(DO_NOT_SHOW_OPTIMIZATION_ALERT, false)) {
                PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
                if (powerManager != null && !powerManager.isIgnoringBatteryOptimizations(getPackageName())) {
                    showWarningMessage(this);
                }
            }
        }
    }

    private void showWarningMessage(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_warning_red_24dp);
        builder.setTitle(R.string.optimization_enabled);

        @SuppressLint("InflateParams") LinearLayout view = (LinearLayout)
                getLayoutInflater().inflate(R.layout.checkbox_do_not_show_again, null);

        builder.setView(view);
        cbDoNotShowAgain = view.findViewById(R.id.checkbox_alert);

        builder.setMessage(R.string.optimization_description);
        builder.setPositiveButton(R.string.disable_button, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    startActivity(new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS));
                    if (cbDoNotShowAgain.isChecked()) {
                        preferences.edit().putBoolean(DO_NOT_SHOW_OPTIMIZATION_ALERT, true).apply();
                    }
                } catch (ActivityNotFoundException ignored) {
                }
            }
        });
        builder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                if (cbDoNotShowAgain.isChecked()) {
                    preferences.edit().putBoolean(DO_NOT_SHOW_OPTIMIZATION_ALERT, true).apply();
                }
            }
        });
        builder.show();
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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == Constants.READ_CONTACTS_PERMISSION_CODE && PermissionManager.readingContactsPermissionGranted(this)) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            if (!preferences.getBoolean(Constants.WRONG_CONTACTS_FORMAT, false)) {
                new ContactsHelper(this, getContentResolver()).loadContacts(preferences);
            }
        } else if (PermissionManager.writingSdPermissionGranted(this)) {
            if (requestCode == Constants.WRITE_EXTERNAL_STORAGE_PERMISSION_CODE) {
                new ExportHelper(this).exportRecords();
            } else if (requestCode == Constants.READ_EXTERNAL_STORAGE_PERMISSION_CODE) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT)
                            .setType("text/xml")
                            .addCategory(Intent.CATEGORY_OPENABLE)
                            .putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                    startActivityForResult(Intent.createChooser(intent, null), FILE_MANAGER);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, R.string.recover_records_error, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_MANAGER && resultCode == RESULT_OK) {
            new RecoverHelper(this).recoverRecords(this, data.getData());
        }
    }

    @Override
    public void onContactsUploaded() {
    }

    public static class BirdaysPreferenceFragment extends PreferenceFragment
            implements SharedPreferences.OnSharedPreferenceChangeListener {

        private SharedPreferences preferences;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

            /*
            * Set summary for additional notification
            */
            findPreference(Constants.ADDITIONAL_NOTIFICATION_KEY).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    ((ListPreference) preference).setValue(newValue.toString());
                    preference.setSummary(((ListPreference) preference).getEntry());
                    return true;
                }
            });

            /*
            * Set summary for ringtone
            */
            Preference ringtonePreference = findPreference(Constants.RINGTONE_KEY);
            try {
                String defaultRingtoneString = Settings.System.DEFAULT_NOTIFICATION_URI.toString();
                String ringtoneString = preferences.getString(ringtonePreference.getKey(), defaultRingtoneString);
                if (ringtoneString.equals(defaultRingtoneString)) {
                    preferences.edit().putString(Constants.RINGTONE_KEY, defaultRingtoneString).apply();
                }
                if (ringtoneString.equals("")) {
                    ringtonePreference.setSummary(getString(R.string.silent));
                } else {
                    String ringtoneName = RingtoneManager.getRingtone(getActivity(),
                            Uri.parse(ringtoneString)).getTitle(getActivity());
                    ringtonePreference.setSummary(ringtoneName);
                }
            } catch (Exception ignored) {
            }

            ringtonePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    try {
                        if (newValue.toString().equals("")) {
                            preference.setSummary(getString(R.string.silent));
                        } else {
                            String newRingtoneName = RingtoneManager.getRingtone(getActivity(),
                                    Uri.parse(newValue.toString())).getTitle(getActivity());
                            preference.setSummary(newRingtoneName);
                        }
                    } catch (Exception ignored) {
                    }
                    return true;
                }
            });

            /*
            * Opens HelpActivity
            */
            findPreference(getString(R.string.help_key)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    SettingsActivity activity = (SettingsActivity) getActivity();
                    if (activity != null) {
                        startActivity(new Intent(activity, HelpActivity.class));
                        activity.overridePendingTransition(R.anim.activity_secondary_in, R.anim.activity_primary_out);
                    }
                    return true;
                }
            });

            /*
            * Imports contacts
            */
            findPreference(getString(R.string.import_contacts_key)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!preferences.getBoolean(Constants.WRONG_CONTACTS_FORMAT, false)) {
                        ContactsHelper contactsHelper = new ContactsHelper(getActivity(), getActivity().getContentResolver());
                        contactsHelper.loadContacts(preferences);
                    }
                    return true;
                }
            });

            /*
            * Exports records to XML file
            */
            findPreference(getString(R.string.export_key)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (PermissionManager.writingSdPermissionGranted(getActivity())) {
                        new ExportHelper(getActivity()).exportRecords();
                    } else {
                        PermissionManager.requestWritingSdPermission(getActivity());
                    }
                    return true;
                }
            });

            /*
            * Recovers records from XML file
            */
            findPreference(getString(R.string.recover_key)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (PermissionManager.writingSdPermissionGranted(getActivity())) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT)
                                    .setType("text/xml")
                                    .addCategory(Intent.CATEGORY_OPENABLE)
                                    .putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                            startActivityForResult(intent, FILE_MANAGER);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(getActivity(), R.string.recover_records_error,
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        PermissionManager.requestReadingSdPermission(getActivity());
                    }
                    return true;
                }
            });

            /*
            * Start page
            */
            findPreference(getString(R.string.start_page_key)).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    ((ListPreference) preference).setValue(newValue.toString());
                    preference.setSummary(((ListPreference) preference).getEntry());
                    return true;
                }
            });

            /*
            * Displayed age
            */
            findPreference(getString(R.string.displayed_age_key)).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    ((ListPreference) preference).setValue(newValue.toString());
                    preference.setSummary(((ListPreference) preference).getEntry());
                    showRestartAppDialog();
                    return true;
                }
            });

            /*
             * Rate app
             */
            findPreference(getString(R.string.rate_app_key)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Utils.openBrowser(getActivity(), getString(R.string.play_market_app_link));
                    return true;
                }
            });

            /*
            * Share app
            */
            findPreference(getString(R.string.share_key)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text) + getString(R.string.play_market_app_link));
                    startActivity(Intent.createChooser(intent, null));
                    return true;
                }
            });

            /*
            * Ad banner
            */
            findPreference(getString(R.string.ad_banner_key)).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    showRestartAppDialog();
                    return true;
                }
            });

            /*
            * Source code
            */
            findPreference(getString(R.string.source_code_key)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Utils.openBrowser(getActivity(), getString(R.string.github_url));
                    return true;
                }
            });

            /*
            * Privacy policy
            */
            findPreference(getString(R.string.privacy_policy_key)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Utils.openBrowser(getActivity(), getString(R.string.privacy_policy_link));
                    return true;
                }
            });

            /*
            * Opens LicensesActivity
            */
            findPreference(getString(R.string.licenses_key)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    SettingsActivity activity = (SettingsActivity) getActivity();
                    if (activity != null) {
                        startActivity(new Intent(activity, LicensesActivity.class));
                        activity.overridePendingTransition(R.anim.activity_secondary_in, R.anim.activity_primary_out);
                    }
                    return true;
                }
            });
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == FILE_MANAGER && resultCode == RESULT_OK) {
                new RecoverHelper(getActivity()).recoverRecords(getActivity(), data.getData());
            }
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            final AlarmHelper alarmHelper = new AlarmHelper(getActivity());
            DbHelper dbHelper = new DbHelper(getActivity());
            final List<Person> persons = dbHelper.query().getPersons();
            switch (key) {
                case Constants.NOTIFICATIONS_KEY:
                    final boolean isChecked = sharedPreferences.getBoolean(Constants.NOTIFICATIONS_KEY, false);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (isChecked) {
                                for (Person person : persons) {
                                    alarmHelper.setAlarms(person);
                                }
                            } else {
                                for (Person person : persons) {
                                    alarmHelper.removeAlarms(person.getTimeStamp());
                                }
                            }
                        }
                    }).start();
                    break;
                case Constants.NOTIFICATION_TIME_KEY:
                    restartAlarms(alarmHelper, persons);
                    break;
                case Constants.ADDITIONAL_NOTIFICATION_KEY:
                    restartAlarms(alarmHelper, persons);
                    break;
                case Constants.NIGHT_MODE_KEY:
                    Utils.setupDayNightTheme(sharedPreferences);
                    restartApp();
                    break;
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }

        private void restartAlarms(final AlarmHelper alarmHelper, final List<Person> persons) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (Person person : persons) {
                        alarmHelper.removeAlarms(person.getTimeStamp());
                        alarmHelper.setAlarms(person);
                    }
                }
            }).start();
        }

        private void restartApp() {
            Intent intent = getActivity().getPackageManager()
                    .getLaunchIntentForPackage(getActivity().getPackageName());
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }

        private void showRestartAppDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(getString(R.string.action_settings));
            builder.setMessage(R.string.displayed_age_changes);
            builder.setPositiveButton(R.string.restart_now, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    restartApp();
                }
            });
            builder.setNegativeButton(R.string.later, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
    }
}
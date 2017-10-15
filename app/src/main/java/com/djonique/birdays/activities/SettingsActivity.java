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
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.djonique.birdays.R;
import com.djonique.birdays.alarm.AlarmHelper;
import com.djonique.birdays.backup.ExportHelper;
import com.djonique.birdays.backup.RestoreHelper;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.Constants;
import com.djonique.birdays.utils.ContactsHelper;
import com.djonique.birdays.utils.PermissionManager;
import com.djonique.birdays.utils.Utils;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

public class SettingsActivity extends AppCompatActivity implements ContactsHelper.LoadingContactsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BirdaysPreferenceFragment fragment = new BirdaysPreferenceFragment();
        getFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
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
        if (requestCode == Constants.CONTACTS_REQUEST_PERMISSION_CODE && PermissionManager.readingContactsPermissionGranted(this)) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            if (!preferences.getBoolean(Constants.WRONG_CONTACTS_FORMAT, false)) {
                ContactsHelper contactsHelper = new ContactsHelper(this, getContentResolver());
                contactsHelper.loadContacts(preferences);
            }
        } else if (PermissionManager.writingSdPermissionGranted(this)) {
            if (requestCode == Constants.WRITE_EXTERNAL_STORAGE_PERMISSION_CODE) {
                new ExportHelper(this).export();
            } else if (requestCode == Constants.READ_EXTERNAL_STORAGE_PERMISSION_CODE) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT)
                            .setType("text/xml")
                            .addCategory(Intent.CATEGORY_OPENABLE)
                            .putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                    startActivityForResult(intent, Constants.FILE_MANAGER);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this,
                            "You don't have an app to perform action, download any File Manager from market",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.FILE_MANAGER && resultCode == RESULT_OK) {
            new RestoreHelper(this).restoreRecords(data.getData().getPath());
        }
    }

    @Override
    public void onContactsUploaded() {
    }

    public static class BirdaysPreferenceFragment extends PreferenceFragment
            implements SharedPreferences.OnSharedPreferenceChangeListener {

        private FirebaseAnalytics mFirebaseAnalytics;
        private SharedPreferences mPreferences;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
            logEvent();

            mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

            /*
            * Sets summary for additional notification
            */
            Preference additionalNotification = findPreference(Constants.ADDITIONAL_NOTIFICATION_KEY);
            additionalNotification.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    ((ListPreference) preference).setValue(newValue.toString());
                    preference.setSummary(((ListPreference) preference).getEntry());
                    return true;
                }
            });

            /*
            * Sets summary for ringtone
            */
            Preference ringtonePreference = findPreference(Constants.RINGTONE_KEY);
            try {
                String ringtoneString = mPreferences.getString(ringtonePreference.getKey(),
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION).toString());
                String ringtoneName = RingtoneManager.getRingtone(getActivity(),
                        Uri.parse(ringtoneString)).getTitle(getActivity());
                ringtonePreference.setSummary(ringtoneName);
            } catch (Exception ignored) {
            }

            ringtonePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    try {
                        String newRingtoneName = RingtoneManager.getRingtone(getActivity(),
                                Uri.parse(newValue.toString())).getTitle(getActivity());
                        preference.setSummary(newRingtoneName);
                    } catch (Exception ignored) {
                    }
                    return true;
                }
            });

            /*
            * Opens HelpActivity
            */
            Preference help = findPreference(getString(R.string.help_key));
            help.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
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
            Preference contactsSync = findPreference(getString(R.string.contacts_sync_key));
            contactsSync.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!mPreferences.getBoolean(Constants.WRONG_CONTACTS_FORMAT, false)) {
                        ContactsHelper contactsHelper = new ContactsHelper(getActivity(), getActivity().getContentResolver());
                        contactsHelper.loadContacts(mPreferences);
                    }
                    return true;
                }
            });

            /*
            * Exports records to XML file
            */
            Preference exportPreference = findPreference(getString(R.string.export_key));
            exportPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (PermissionManager.writingSdPermissionGranted(getActivity())) {
                        new ExportHelper(getActivity()).export();
                    } else {
                        PermissionManager.requestWritingSdPermission(getActivity());
                    }
                    return true;
                }
            });

            /*
            * Restores records from XML file
            */
            Preference restorePreference = findPreference(getString(R.string.recover_key));
            restorePreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (PermissionManager.writingSdPermissionGranted(getActivity())) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT)
                                    .setType("text/xml")
                                    .addCategory(Intent.CATEGORY_OPENABLE)
                                    .putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                            startActivityForResult(intent, Constants.FILE_MANAGER);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(getActivity(),
                                    "You don't have an app to perform action, download any File Manager from market",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        PermissionManager.requestReadingSdPermission(getActivity());
                    }
                    return true;
                }
            });

            /*
            * Displayed age
            */
            Preference displayedAge = findPreference(getString(R.string.displayed_age_key));
            displayedAge.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    ((ListPreference) preference).setValue(newValue.toString());
                    preference.setSummary(((ListPreference) preference).getEntry());
                    showRestartAppDialog();
                    return true;
                }
            });

            /*
            * Ad banner
            */
            Preference adBannerPreference = findPreference(getString(R.string.ad_banner_key));
            adBannerPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (!Boolean.parseBoolean(newValue.toString())) {
                        mFirebaseAnalytics.logEvent(Constants.AD_BANNER_DISABLED, new Bundle());
                    }
                    showRestartAppDialog();
                    return true;
                }
            });

            /*
            * Share app
            */
            Preference share = findPreference(getString(R.string.share_key));
            share.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    mFirebaseAnalytics.logEvent(Constants.SHARE_APP, new Bundle());
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType(Constants.TEXT_PLAIN);
                    intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text) + getString(R.string.play_market_app_link));
                    startActivity(Intent.createChooser(intent, getString(R.string.app_name)));
                    return true;
                }
            });

            /*
            * Opens LicensesActivity
            */
            Preference licenses = findPreference(getString(R.string.licenses_key));
            licenses.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
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
            if (requestCode == Constants.FILE_MANAGER && resultCode == RESULT_OK) {
                new RestoreHelper(getActivity()).restoreRecords(data.getData().getPath());
            }
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            final AlarmHelper alarmHelper = new AlarmHelper(getActivity());
            DBHelper dbHelper = new DBHelper(getActivity());
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

        private void logEvent() {
            Bundle params = new Bundle();
            params.putString(FirebaseAnalytics.Param.CONTENT_TYPE, Constants.SETTINGS_ACTIVITY_TAG);
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);
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
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
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
package com.djonique.birdays.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.djonique.birdays.R;
import com.djonique.birdays.alarm.AlarmHelper;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BirdaysPreferenceFragment fragment = new BirdaysPreferenceFragment();
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                fragment).commit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.settings_main_in, R.anim.settings_main_out);
    }

    public static class BirdaysPreferenceFragment extends PreferenceFragment
            implements SharedPreferences.OnSharedPreferenceChangeListener {

        private FirebaseAnalytics mFirebaseAnalytics;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
            logEvent();

            Preference share = findPreference(getString(R.string.share_key));
            share.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    mFirebaseAnalytics.logEvent(ConstantManager.SHARE_APP, new Bundle());
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType(ConstantManager.TEXT_PLAIN);
                    intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.play_market_app_link));
                    startActivity(Intent.createChooser(intent, getString(R.string.app_name)));
                    return true;
                }
            });

            Preference licenses = findPreference(getString(R.string.licenses_key));
            licenses.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    SettingsActivity activity = (SettingsActivity) getActivity();
                    if (activity != null) {
                        startActivity(new Intent(activity, LicensesActivity.class));
                        activity.overridePendingTransition(R.anim.main_settings_in, R.anim.main_settings_out);
                    }
                    return true;
                }
            });
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            DBHelper dbHelper = new DBHelper(getActivity());
            List<Person> persons = dbHelper.query().getPersons();
            AlarmHelper alarmHelper = AlarmHelper.getInstance();
            switch (key) {
                case ConstantManager.NOTIFICATIONS:
                    boolean isChecked = sharedPreferences.getBoolean(getString(R.string.notifications_key), false);
                    if (isChecked) {
                        for (Person person : persons) {
                            alarmHelper.setAlarm(person);
                        }
                    } else {
                        for (Person person : persons) {
                            alarmHelper.removeAlarm(person.getTimeStamp());
                        }
                    }
                    break;
                case ConstantManager.NOTIFICATION_TIME:
                    for (Person person : persons) {
                        alarmHelper.removeAlarm(person.getTimeStamp());
                        alarmHelper.setAlarm(person);
                    }
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
            params.putString(FirebaseAnalytics.Param.CONTENT_TYPE, ConstantManager.SETTINGS_ACTIVITY_TAG);
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);
        }
    }
}

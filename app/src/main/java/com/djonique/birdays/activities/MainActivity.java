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

import android.Manifest;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.djonique.birdays.R;
import com.djonique.birdays.adapters.PagerAdapter;
import com.djonique.birdays.ads.Ad;
import com.djonique.birdays.alarm.AlarmHelper;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.dialogs.NewPersonDialogFragment;
import com.djonique.birdays.fragments.AllFragment;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.BirdaysApplication;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.ContactsHelper;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kobakei.ratethisapp.RateThisApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;

public class MainActivity extends AppCompatActivity implements
        NewPersonDialogFragment.AddingPersonListener, AllFragment.DeletingRecordListener {

    public static final int INSTALL_DAYS = 7;
    public static final int LAUNCH_TIMES = 7;
    public DBHelper dbHelper;

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.container)
    CoordinatorLayout container;

    private PagerAdapter pagerAdapter;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FirebaseAnalytics.getInstance(this);

        AlarmHelper.getInstance().init(getApplicationContext());

        rateThisAppInit(this);

        Ad.showMainBanner(findViewById(R.id.container), (AdView) findViewById(R.id.banner), fab);

        dbHelper = new DBHelper(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean contactsLoaded = preferences.getBoolean(ConstantManager.CONTACTS_LOADED, false);
        boolean wrongContactsFormat = preferences.getBoolean(ConstantManager.WRONG_CONTACTS_FORMAT, false);

        if (!wrongContactsFormat && !contactsLoaded) {
            loadContacts();
        }

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);

        setSupportActionBar(toolbar);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pagerAdapter.search(newText);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        BirdaysApplication.activityResumed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pagerAdapter.addRecordsFromDB();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BirdaysApplication.activityPaused();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            overridePendingTransition(R.anim.activity_secondary_in, R.anim.activity_primary_out);
        } /*else if (item.getItemId() == R.id.action_sync) {
            ModalBottomSheet modalBottomSheet = new ModalBottomSheet();
            modalBottomSheet.show(getSupportFragmentManager(), ConstantManager.BOTTOM_SHEET_DIALOG_TAG);
        } else if (item.getItemId() == R.id.action_backup) {
            try {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("text/xml");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivity(intent);
            } catch (ActivityNotFoundException e){
                Toast.makeText(this, "You don't have an app to perform action, download any File Manager from market", Toast.LENGTH_LONG).show();
            }
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
        if (resultCode == RESULT_OK) {
            int position = data.getIntExtra(ConstantManager.POSITION, 0);
            pagerAdapter.startRemovePersonDialog(position);
        }
    }

    @Override
    public void onPersonAdded(Person person) {
        pagerAdapter.addPerson(person);
        Snackbar.make(findViewById(R.id.container), R.string.record_added, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPersonAddedCancel() {
    }

    @Override
    public void onRecordDeleted(long timeStamp) {
        pagerAdapter.deleteRecord(timeStamp);
    }

    @OnPageChange(R.id.viewPager)
    void onPageSelected(int position) {
        appBarLayout.setExpanded(true, true);
        if (position == PagerAdapter.FAMOUS_FRAGMENT_POSITION) {
            fab.hide();
            searchView.setVisibility(View.GONE);
        } else {
            fab.show();
            searchView.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.fab)
    void showDialog() {
        DialogFragment newPersonDialogFragment = new NewPersonDialogFragment();
        newPersonDialogFragment.show(getFragmentManager(), ConstantManager.NEW_PERSON_DIALOG_TAG);
    }

    /**
     * «Rate this app» dialog initialization
     */
    private void rateThisAppInit(Context context) {
        RateThisApp.onCreate(context);
        RateThisApp.Config config = new RateThisApp.Config(INSTALL_DAYS, LAUNCH_TIMES);
        RateThisApp.init(config);
        RateThisApp.showRateDialogIfNeeded(context);
    }

    /**
     * Loads all persons with Birthdays from Contacts, compares them with persons from Database and
     * saves them into DB, sets alarm for added persons
     */
    private void loadContacts() {
        ContentResolver contentResolver = getContentResolver();
        List<Person> dbPersons = dbHelper.query().getPersons();
        AlarmHelper alarmHelper = AlarmHelper.getInstance();

        if (permissionGranted()) {
            try {
                List<Person> contacts = ContactsHelper.getAllContactsWithBirthdays(contentResolver);

                for (Person person : contacts) {
                    if (!isPersonAlreadyInDB(person, dbPersons)) {
                        dbHelper.addRec(person);
                        alarmHelper.setAlarms(person);
                    }
                }
                viewPager.getAdapter().notifyDataSetChanged();
                preferences.edit().putBoolean(ConstantManager.CONTACTS_LOADED, true).apply();
                Toast.makeText(MainActivity.this, "Контакты загружены", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                preferences.edit().putBoolean(ConstantManager.WRONG_CONTACTS_FORMAT, true).apply();
                Toast.makeText(MainActivity.this, R.string.loading_contacts_error, Toast.LENGTH_LONG).show();
            }
        } else {
            requestPermission();
        }
    }

    /**
     * Checks if permission for reading contacts is granted
     */
    private boolean permissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Requests reading contacts permission if it is not granted
     */
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},
                ConstantManager.CONTACTS_REQUEST_PERMISSION_CODE);

        Snackbar.make(container, R.string.permission_required,
                Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_allow_text, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openApplicationSettings();
                    }
                })
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == ConstantManager.CONTACTS_REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts();
            }
        }
    }

    /**
     * Checks if person with the same name already exists in database
     */
    private boolean isPersonAlreadyInDB(Person person, List<Person> list) {
        boolean found = false;
        for (Person dbPerson : list) {
            if (person.equals(dbPerson)) {
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * Opens application settings
     */
    public void openApplicationSettings() {
        startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse(ConstantManager.PACKAGE + getPackageName())));
    }
}
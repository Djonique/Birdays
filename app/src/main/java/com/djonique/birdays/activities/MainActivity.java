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

import android.app.DialogFragment;
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
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.djonique.birdays.R;
import com.djonique.birdays.ad.Ad;
import com.djonique.birdays.adapters.PagerAdapter;
import com.djonique.birdays.database.DbHelper;
import com.djonique.birdays.dialogs.NewPersonDialogFragment;
import com.djonique.birdays.fragments.AllFragment;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.BirdaysApplication;
import com.djonique.birdays.utils.Constants;
import com.djonique.birdays.utils.ContactsHelper;
import com.djonique.birdays.utils.Utils;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements
        NewPersonDialogFragment.AddingPersonListener,
        AllFragment.DeletingRecordListener,
        ContactsHelper.LoadingContactsListener {

    private static final String NEW_PERSON_DIALOG_TAG = "NEW_PERSON_DIALOG_TAG";
    // private static final String BOTTOM_SHEET_DIALOG_TAG = "BOTTOM_SHEET_DIALOG_TAG";

    public DbHelper dbHelper;

    @BindView(R.id.container_main)
    CoordinatorLayout container;
    @BindView(R.id.appbar_main)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar_main)
    Toolbar toolbar;
    @BindView(R.id.searchview_main)
    SearchView searchView;
    @BindView(R.id.tablayout_main)
    TabLayout tabLayout;
    @BindView(R.id.viewpager_main)
    ViewPager viewPager;
    @BindView(R.id.fab_main)
    FloatingActionButton fab;
    @BindView(R.id.banner_main)
    AdView adView;

    private SharedPreferences preferences;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FirebaseAnalytics.getInstance(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        Utils.setupDayNightTheme(preferences);

        dbHelper = new DbHelper(this);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);

        setSupportActionBar(toolbar);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);

        if (!preferences.getBoolean(Constants.CONTACTS_UPLOADED, false)) {
            new ContactsHelper(this, getContentResolver()).loadContacts(preferences);
        }

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

        if (preferences.getBoolean(getString(R.string.ad_banner_key), true)) {
            Ad.showBannerAd(container, adView, fab);
        }

        // App starts from AllFragment
        viewPager.setCurrentItem(1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BirdaysApplication.activityResumed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pagerAdapter.addRecordsFromDb();
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
        /*} else if (item.getItemId() == R.id.action_sync) {
            ModalBottomSheet modalBottomSheet = new ModalBottomSheet();
            modalBottomSheet.show(getSupportFragmentManager(), Constants.BOTTOM_SHEET_DIALOG_TAG);*/
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPersonAdded(Person person) {
        pagerAdapter.addPerson(person);
        Snackbar.make(findViewById(R.id.container_main), R.string.record_added, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPersonAddedCancel() {
    }

    @Override
    public void onRecordDeleted(long timeStamp) {
        pagerAdapter.deleteRecord(timeStamp);
    }

    @OnPageChange(R.id.viewpager_main)
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

    @OnClick(R.id.fab_main)
    void showDialog() {
        DialogFragment newPersonDialogFragment = new NewPersonDialogFragment();
        newPersonDialogFragment.show(getFragmentManager(), NEW_PERSON_DIALOG_TAG);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == Constants.READ_CONTACTS_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (!preferences.getBoolean(Constants.WRONG_CONTACTS_FORMAT, false)) {
                    new ContactsHelper(this, getContentResolver()).loadContacts(preferences);
                }
            } else {
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
        }
    }

    /**
     * Opens application settings
     */
    private void openApplicationSettings() {
        startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + getPackageName())));
    }

    @Override
    public void onContactsUploaded() {
        if (viewPager != null) viewPager.getAdapter().notifyDataSetChanged();
    }
}
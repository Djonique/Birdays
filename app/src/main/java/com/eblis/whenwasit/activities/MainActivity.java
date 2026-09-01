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

package com.eblis.whenwasit.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.ad.Ad;
import com.eblis.whenwasit.adapters.PagerAdapter;
import com.eblis.whenwasit.alarm.AlarmHelper;
import com.eblis.whenwasit.database.DbHelper;
import com.eblis.whenwasit.databinding.ActivityMainBinding;
import com.eblis.whenwasit.dialogs.NewPersonDialogFragment;
import com.eblis.whenwasit.fragments.AllFragment;
import com.eblis.whenwasit.models.Person;
import com.eblis.whenwasit.utils.BirdaysApplication;
import com.eblis.whenwasit.utils.Constants;
import com.eblis.whenwasit.utils.ContactsHelper;
import com.eblis.whenwasit.utils.PermissionManager;
import com.eblis.whenwasit.utils.Utils;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity implements
        NewPersonDialogFragment.AddingPersonListener,
        AllFragment.DeletingPersonListener,
        ContactsHelper.LoadingContactsListener {

    private static final String NEW_PERSON_DIALOG_TAG = "NEW_PERSON_DIALOG_TAG";
    // private static final String BOTTOM_SHEET_DIALOG_TAG = "BOTTOM_SHEET_DIALOG_TAG";

    public DbHelper dbHelper;

    private ActivityMainBinding binding;

    private SharedPreferences preferences;
    private PagerAdapter pagerAdapter;

    private static final Intent[] POWERMANAGER_INTENTS = {
            new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
            new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
            new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
            new Intent().setComponent(new ComponentName("com.samsung.android.lool", "com.samsung.android.sm.battery.ui.BatteryActivity")),
            new Intent().setComponent(new ComponentName("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity")),
            new Intent().setComponent(new ComponentName("com.htc.pitroad", "com.htc.pitroad.landingpage.activity.LandingPageActivity")),
            new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.MainActivity")),
            new Intent().setComponent(new ComponentName("com.transsion.phonemanager", "com.itel.autobootmanager.activity.AutoBootMgrActivity"))
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.appbarMain, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(v.getPaddingLeft(), insets.top, v.getPaddingRight(), v.getPaddingBottom());
            return windowInsets;
        });

        ViewCompat.setOnApplyWindowInsetsListener(binding.bannerMain, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), insets.bottom);
            return windowInsets;
        });

        ViewCompat.setOnApplyWindowInsetsListener(binding.fabMain, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            mlp.bottomMargin = (int) (getResources().getDimension(R.dimen.fab_margin_default) + insets.bottom);
            v.setLayoutParams(mlp);
            return windowInsets;
        });

        FirebaseAnalytics.getInstance(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        Utils.setupDayNightTheme(preferences);

        dbHelper = new DbHelper(this);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);

        setSupportActionBar(binding.toolbarMain);

        binding.viewpagerMain.setAdapter(pagerAdapter);
        binding.viewpagerMain.setOffscreenPageLimit(2);
        binding.tablayoutMain.setupWithViewPager(binding.viewpagerMain);

        if (!preferences.getBoolean(Constants.CONTACTS_UPLOADED, false)) {
            new ContactsHelper(this, getContentResolver()).loadContactsWithProgress(preferences);
        }

        binding.searchviewMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        MobileAds.initialize(this, initializationStatus -> {});

        if (preferences.getBoolean(getString(R.string.ad_banner_key), true)) {
            Ad.showBannerAd(this, binding.containerMain, binding.bannerMain, binding.fabMain);
        }

        startNotificationsChecker(this, preferences);
        if (!PermissionManager.notificationsPermissionGranted(this)) {
            PermissionManager.requestNotificationsPermission(this);
        }

        // Start page
        binding.viewpagerMain.setCurrentItem(Integer.parseInt(preferences.getString(Constants.START_PAGE, "1")));

        binding.viewpagerMain.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                MainActivity.this.onPageSelected(position);
            }
        });

        binding.fabMain.setOnClickListener(v -> showDialog());

//        enableBattery();
    }

    private void enableBattery() {
        for (Intent intent : POWERMANAGER_INTENTS)
            if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                startActivity(intent);
                break;
            }
    }

    private void startNotificationsChecker(Context context, SharedPreferences preferences) {
        final AlarmHelper alarmHelper = new AlarmHelper(this);
        final boolean notificationsEnabled = preferences.getBoolean(Constants.NOTIFICATIONS_KEY, false);
        if (notificationsEnabled) {
            alarmHelper.setRecurringAlarm();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        BirdaysApplication.activityResumed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pagerAdapter.addPersonsFromDb();
        startNotificationsChecker(this, preferences);
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
        Utils.notifyWidget(this);
        Snackbar.make(findViewById(R.id.container_main), R.string.record_added, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPersonAddedCancel() {
    }

    @Override
    public void onPersonDeleted(long recordId) {
        pagerAdapter.deletePerson(recordId);
        Utils.notifyWidget(this);
    }

    void onPageSelected(int position) {
        binding.appbarMain.setExpanded(true, true);
        if (position == PagerAdapter.FAMOUS_FRAGMENT_POSITION) {
            binding.fabMain.hide();
            binding.searchviewMain.setVisibility(View.GONE);
        } else {
            binding.fabMain.show();
            binding.searchviewMain.setVisibility(View.VISIBLE);
        }
    }

    void showDialog() {
        DialogFragment newPersonDialogFragment = new NewPersonDialogFragment();
        newPersonDialogFragment.show(getSupportFragmentManager(), NEW_PERSON_DIALOG_TAG);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == Constants.READ_CONTACTS_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (!preferences.getBoolean(Constants.WRONG_CONTACTS_FORMAT, false)) {
                    new ContactsHelper(this, getContentResolver()).loadContactsWithProgress(preferences);
                }
            } else {
                Snackbar.make(binding.containerMain, R.string.permission_required,
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
        if (binding.viewpagerMain.getAdapter() != null) binding.viewpagerMain.getAdapter().notifyDataSetChanged();
    }
}
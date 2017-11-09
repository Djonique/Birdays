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

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.djonique.birdays.BuildConfig;
import com.djonique.birdays.R;
import com.djonique.birdays.adapters.FamousFragmentAdapter;
import com.djonique.birdays.alarm.AlarmHelper;
import com.djonique.birdays.database.DbHelper;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.Constants;
import com.djonique.birdays.utils.Utils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kobakei.ratethisapp.RateThisApp;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    private static final int INSTALL_DAYS = 7;
    private static final int LAUNCH_TIMES = 5;
    private static final int EDIT_ACTIVITY = 4;

    @BindView(R.id.container_detail)
    CoordinatorLayout container;
    @BindView(R.id.toolbar_detail)
    Toolbar toolbar;
    @BindView(R.id.imageview_detail_picture)
    ImageView ivSeasonPicture;
    @BindView(R.id.textview_detail_age)
    TextView tvAge;
    @BindView(R.id.textview_detail_date)
    TextView tvDate;
    @BindView(R.id.textview_detail_left)
    TextView tvDaysLeft;
    @BindView(R.id.relativelayout_detail_since)
    RelativeLayout rlDaysSinceBirthday;
    @BindView(R.id.textview_detail_since)
    TextView tvDaysSinceBirthday;
    @BindView(R.id.imageview_detail_zodiac)
    ImageView ivZodiacSign;
    @BindView(R.id.textview_detail_zodiac)
    TextView tvZodiacSign;
    @BindView(R.id.cardview_detail_info)
    CardView cardViewInfo;
    @BindView(R.id.relativelayout_detail_phone)
    RelativeLayout rlPhoneNumber;
    @BindView(R.id.textview_detail_phone)
    TextView tvPhoneNumber;
    @BindView(R.id.relativelayout_detail_email)
    RelativeLayout rlEmail;
    @BindView(R.id.textview_detail_email)
    TextView tvEmail;
    @BindView(R.id.recyclerview_detail)
    RecyclerView recyclerView;

    private FirebaseAnalytics mFirebaseAnalytics;
    private InterstitialAd mInterstitialAd;
    private DbHelper dbHelper;
    private Person person;
    private long timeStamp, date;
    private String phoneNumber, email, displayedAge;
    private boolean yearUnknown;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean adEnabled = preferences.getBoolean(getString(R.string.ad_interstitial_key), true);
        displayedAge = preferences.getString(Constants.DISPLAYED_AGE_KEY, "0");

        /*
        * Interstitial doesn't work on Android API 26+
        * java.lang.IllegalStateException: Only fullscreen activities can request orientation
        */
        mInterstitialAd = new InterstitialAd(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O && adEnabled) {
            mInterstitialAd.setAdUnitId(BuildConfig.INTERSTITIAL_AD_ID);
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }

        dbHelper = new DbHelper(this);

        Utils.setupDayNightTheme(preferences);

        Intent intent = getIntent();
        timeStamp = intent.getLongExtra(Constants.TIME_STAMP, 0);
        person = dbHelper.query().getPerson(timeStamp);
        date = person.getDate();
        yearUnknown = person.isYearUnknown();
        phoneNumber = person.getPhoneNumber();
        email = person.getEmail();

        toolbar.setTitle(person.getName());
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        setupUI();

        loadBornThisDay();

        recyclerView.setFocusable(false);

        rateThisAppInit(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_detail_delete:
                deletePersonDialog(person);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        finish();
        overridePendingTransition(R.anim.activity_primary_in, R.anim.activity_secondary_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (requestCode == EDIT_ACTIVITY && resultCode == RESULT_OK) {
            // Refreshes activity after editing
            Toast.makeText(this, R.string.record_edited, Toast.LENGTH_SHORT).show();
            Intent refresh = new Intent(this, DetailActivity.class);
            refresh.putExtra(Constants.TIME_STAMP, timeStamp);
            startActivity(refresh);
            this.finish();
        }
    }

    /**
     * Set up UI depending on person's data
     */
    private void setupUI() {
        setSeasonImage();

        tvDaysLeft.setText(Utils.daysLeft(this, date));

        if (yearUnknown) {
            tvDate.setText(Utils.getDateWithoutYear(date));
            tvAge.setVisibility(View.GONE);
            rlDaysSinceBirthday.setVisibility(View.GONE);
        } else {
            tvDate.setText(Utils.getDate(date));
            tvAge.setText(String.valueOf(displayedAge.equals("0") ? Utils.getCurrentAge(date) : Utils.getFutureAge(date)));
            tvDaysSinceBirthday.setText(Utils.daysSinceBirthday(date));
        }

        int zodiacId = Utils.getZodiacId(date);
        tvZodiacSign.setText(getString(zodiacId));
        ivZodiacSign.setImageResource(Utils.getZodiacImage(zodiacId));

        if (isEmpty(phoneNumber) && isEmpty(email))
            cardViewInfo.setVisibility(View.GONE);

        if (isEmpty(phoneNumber)) {
            rlPhoneNumber.setVisibility(View.GONE);
        } else {
            tvPhoneNumber.setText(String.valueOf(person.getPhoneNumber()));
        }

        if (isEmpty(email)) {
            rlEmail.setVisibility(View.GONE);
        } else {
            tvEmail.setText(person.getEmail());
        }
    }

    private boolean isEmpty(String text) {
        return text == null || text.equals("");
    }

    /**
     * Loads list of famous persons born certain day
     */
    private void loadBornThisDay() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        FamousFragmentAdapter adapter = new FamousFragmentAdapter();
        recyclerView.setAdapter(adapter);

        List<Person> famousPersons = dbHelper.query().getFamousBornThisDay(date);
        for (int i = 0; i < famousPersons.size(); i++) {
            adapter.addItem(famousPersons.get(i));
        }
    }

    @OnClick(R.id.fab_detail)
    void starEditActivity() {
        logEvent();
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(Constants.TIME_STAMP, timeStamp);
        startActivityForResult(intent, EDIT_ACTIVITY);
        overridePendingTransition(R.anim.activity_secondary_in, R.anim.activity_primary_out);
    }

    /**
     * Set up image depending on month
     */
    private void setSeasonImage() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int month = calendar.get(Calendar.MONTH);
        if (month >= 0 && month < 2 || month == 11) {
            ivSeasonPicture.setImageResource(R.drawable.img_winter);
        } else if (month >= 2 && month < 5) {
            ivSeasonPicture.setImageResource(R.drawable.img_spring);
        } else if (month >= 5 && month < 8) {
            ivSeasonPicture.setImageResource(R.drawable.img_summer);
        } else ivSeasonPicture.setImageResource(R.drawable.img_autumn);
    }

    private void deletePersonDialog(final Person person) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.delete_record_text) + person.getName() + "?");
        builder.setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                new AlarmHelper(getApplicationContext()).removeAlarms(timeStamp);
                dbHelper.removePerson(timeStamp);
                dialog.dismiss();
                finish();
                overridePendingTransition(R.anim.activity_primary_in, R.anim.activity_secondary_out);
            }
        });
        builder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
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

    private void logEvent() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.CONTENT_TYPE, Constants.EDIT_ACTIVITY_TAG);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);
    }

    @OnClick(R.id.imagebutton_detail_phone)
    void makeCall() {
        mFirebaseAnalytics.logEvent(Constants.MAKE_CALL, new Bundle());
        startActivity(Intent.createChooser(new Intent(Intent.ACTION_DIAL,
                Uri.parse(Constants.TEL + phoneNumber)), null));
    }

    @OnClick(R.id.imagebutton_detail_chat)
    void sendMessage() {
        mFirebaseAnalytics.logEvent(Constants.SEND_MESSAGE, new Bundle());
        Intent intent = new Intent(Intent.ACTION_VIEW)
                .setType(Constants.TYPE_SMS)
                .putExtra(Constants.ADDRESS, phoneNumber)
                .setData(Uri.parse(Constants.SMSTO + phoneNumber));
        startActivity(Intent.createChooser(intent, null));
    }

    @OnClick(R.id.imagebutton_detail_email)
    void sendEmail() {
        mFirebaseAnalytics.logEvent(Constants.SEND_EMAIL, new Bundle());
        Intent intent = new Intent(Intent.ACTION_SENDTO)
                .setType(Constants.TYPE_EMAIL)
                .putExtra(Intent.EXTRA_EMAIL, new String[]{email})
                .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.happy_birthday))
                .setData(Uri.parse(Constants.MAILTO + email));
        startActivity(Intent.createChooser(intent, null));
    }
}
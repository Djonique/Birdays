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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
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

import com.djonique.birdays.R;
import com.djonique.birdays.adapters.FamousFragmentAdapter;
import com.djonique.birdays.ads.Ad;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.Utils;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    public static final int POSSIBLE_PICTURES = 3;
    @BindView(R.id.toolbar_detail)
    Toolbar toolbar;
    @BindView(R.id.ivPicture)
    ImageView imageView;
    @BindView(R.id.tvDetailDate)
    TextView tvDate;
    @BindView(R.id.tvZodiacImage)
    TextView tvZodiacImage;
    @BindView(R.id.tvZodiac)
    TextView tvZodiac;
    @BindView(R.id.rlAge)
    RelativeLayout rlAge;
    @BindView(R.id.tvDetailAge)
    TextView tvAge;
    @BindView(R.id.cardViewDetail)
    CardView cardView;
    @BindView(R.id.rlPhone)
    RelativeLayout rlPhone;
    @BindView(R.id.tvDetailPhone)
    TextView tvPhone;
    @BindView(R.id.tvDetailEmail)
    TextView tvEmail;
    @BindView(R.id.rlEmail)
    RelativeLayout rlEmail;
    @BindView(R.id.detailRecyclerView)
    RecyclerView recyclerView;

    private DBHelper dbHelper;
    private Person person;
    private FirebaseAnalytics mFirebaseAnalytics;

    private long date, timeStamp;
    private int position;
    private boolean unknownYear;
    private String name, phoneNumber, email, shortDate;

    private int winterImages[] = {R.drawable.img_winter_1,
            R.drawable.img_winter_2,
            R.drawable.img_winter_3,};
    private int springImages[] = {R.drawable.img_spring_1,
            R.drawable.img_spring_2,
            R.drawable.img_spring_3};
    private int summerImages[] = {R.drawable.img_summer_1,
            R.drawable.img_summer_2,
            R.drawable.img_summer_3};
    private int autumnImages[] = {R.drawable.img_autumn_1,
            R.drawable.img_autumn_2,
            R.drawable.img_autumn_3};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        ButterKnife.bind(this);

        Ad.showDetailBanner(findViewById(R.id.container_detail), (AdView) findViewById(R.id.banner_detail));

        dbHelper = new DBHelper(getApplicationContext());

        Intent intent = getIntent();

        timeStamp = intent.getLongExtra(ConstantManager.TIME_STAMP, 0);
        position = intent.getIntExtra(ConstantManager.SELECTED_ITEM, 0);
        person = dbHelper.query().getPerson(timeStamp);
        name = person.getName();
        date = person.getDate();
        shortDate = Utils.getUnknownDate(date);
        unknownYear = person.isYearUnknown();
        phoneNumber = person.getPhoneNumber();
        email = person.getEmail();

        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }

        updateUI();

        loadBornThisDay();

        recyclerView.setFocusable(false);
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
                Intent mainIntent = new Intent();
                mainIntent.putExtra(ConstantManager.POSITION, position);
                setResult(RESULT_OK, mainIntent);
                finish();
                overridePendingTransition(R.anim.activity_primary_in, R.anim.activity_secondary_out);
                break;
            case R.id.menu_detail_share:
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType(ConstantManager.TEXT_PLAIN);
                intentShare.putExtra(Intent.EXTRA_TEXT, name
                        + getString(R.string.is_celebrating_bd)
                        + shortDate
                        + "\n\n"
                        + getString(R.string.play_market_app_link));
                startActivity(Intent.createChooser(intentShare, getString(R.string.app_name)));
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.activity_primary_in, R.anim.activity_secondary_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (resultCode == RESULT_OK) {
            // Refreshes activity after editing
            Intent refresh = new Intent(this, DetailActivity.class);
            refresh.putExtra(ConstantManager.TIME_STAMP, timeStamp);
            startActivity(refresh);
            this.finish();
        }
    }

    /**
     * Updates UI depending on person's info
     */
    private void updateUI() {
        setSeasonImage();

        int zodiacId = Utils.getZodiacId(date);
        tvZodiac.setText(getString(zodiacId));
        tvZodiacImage.setText(Utils.getZodiacImage(zodiacId));

        if (unknownYear) {
            tvDate.setText(Utils.getUnknownDate(date));
            rlAge.setVisibility(View.GONE);
        } else {
            tvDate.setText(Utils.getDate(date));
            tvAge.setText(String.valueOf(Utils.getAge(date)));
        }

        if (phoneNumber == null && email == null) cardView.setVisibility(View.GONE);

        if (phoneNumber == null) {
            rlPhone.setVisibility(View.GONE);
        } else {
            tvPhone.setText(String.valueOf(person.getPhoneNumber()));
        }

        if (email == null) {
            rlEmail.setVisibility(View.GONE);
        } else {
            tvEmail.setText(person.getEmail());
        }
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
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
        intent.putExtra(ConstantManager.TIME_STAMP, timeStamp);
        startActivityForResult(intent, ConstantManager.EDIT_ACTIVITY);
        overridePendingTransition(R.anim.activity_secondary_in, R.anim.activity_primary_out);
    }

    /**
     * Sets random picture into ImageView
     */
    private void setPicture(ImageView imageView, int[] pictures) {
        imageView.setImageResource(pictures[(int) (Math.random() * POSSIBLE_PICTURES)]);
    }

    /**
     * Chooses certain image depending on month
     */
    private void setSeasonImage() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int month = calendar.get(Calendar.MONTH);
        if (month >= 0 && month < 2 || month == 11) {
            setPicture(imageView, winterImages);
        } else if (month >= 2 && month < 5) {
            setPicture(imageView, springImages);
        } else if (month >= 5 && month < 8) {
            setPicture(imageView, summerImages);
        } else setPicture(imageView, autumnImages);
    }

    private void logEvent() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.CONTENT_TYPE, ConstantManager.EDIT_ACTIVITY_TAG);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);
    }

    @OnClick(R.id.ibPhoneIcon)
    void call() {
        mFirebaseAnalytics.logEvent(ConstantManager.MAKE_CALL, new Bundle());
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(ConstantManager.TEL + phoneNumber)));
    }

    @OnClick(R.id.ibChatIcon)
    void sendSMS() {
        mFirebaseAnalytics.logEvent(ConstantManager.SEND_SMS, new Bundle());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType(ConstantManager.TYPE_SMS);
        intent.putExtra(ConstantManager.ADDRESS, phoneNumber);
        intent.setData(Uri.parse(ConstantManager.SMSTO + phoneNumber));
        startActivity(intent);
    }

    @OnClick(R.id.ibEmailIcon)
    void sendEmail() {
        mFirebaseAnalytics.logEvent(ConstantManager.SEND_EMAIL, new Bundle());
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType(ConstantManager.TYPE_EMAIL);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.happy_birthday));
        intent.setData(Uri.parse(ConstantManager.MAILTO + email));
        startActivity(Intent.createChooser(intent, getString(R.string.send_email)));
    }
}

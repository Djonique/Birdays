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

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import com.djonique.birdays.R;
import com.djonique.birdays.adapters.FamousFragmentAdapter;
import com.djonique.birdays.ads.Ad;
import com.djonique.birdays.alarm.AlarmHelper;
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
    @BindView(R.id.tvDaysLeft)
    TextView tvDaysLeft;
    @BindView(R.id.cardViewDetail)
    CardView cardView;
    @BindView(R.id.rlPhone)
    RelativeLayout rlPhone;
    @BindView(R.id.tvDetailPhone)
    TextView tvPhone;
    @BindView(R.id.rlEmail)
    RelativeLayout rlEmail;
    @BindView(R.id.tvDetailEmail)
    TextView tvEmail;
    @BindView(R.id.detailRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.container_detail)
    CoordinatorLayout container;
    @BindView(R.id.banner_detail)
    AdView adView;

    private DBHelper dbHelper;
    private Person person;
    private FirebaseAnalytics mFirebaseAnalytics;
    private long date, timeStamp;
    private boolean unknownYear;
    private String name, phoneNumber, email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Intent intent = getIntent();
        timeStamp = intent.getLongExtra(ConstantManager.TIME_STAMP, 0);

        dbHelper = new DBHelper(this);
        person = dbHelper.query().getPerson(timeStamp);
        name = person.getName();
        date = person.getDate();
        unknownYear = person.isYearUnknown();
        phoneNumber = person.getPhoneNumber();
        email = person.getEmail();

        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        updateUI();

        loadBornThisDay();

        recyclerView.setFocusable(false);

        Ad.showDetailBanner(container, adView);
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
                deleteDialog(person);
                break;
            case R.id.menu_detail_share:
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType(ConstantManager.TEXT_PLAIN);
                intentShare.putExtra(Intent.EXTRA_TEXT, name
                        + getString(R.string.is_celebrating_bd)
                        + Utils.getDateWithoutYear(date)
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
            Toast.makeText(this, R.string.record_edited, Toast.LENGTH_SHORT).show();
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

        tvDaysLeft.setText(Utils.daysLeft(this, date));

        int zodiacId = Utils.getZodiacId(date);
        tvZodiac.setText(getString(zodiacId));
        tvZodiacImage.setText(Utils.getZodiacImage(zodiacId));

        if (unknownYear) {
            tvDate.setText(Utils.getDateWithoutYear(date));
            tvAge.setVisibility(View.GONE);
        } else {
            tvDate.setText(Utils.getDate(date));
            tvAge.setText(String.valueOf(Utils.getAge(date)));
        }

        if (isStringEmpty(phoneNumber) && isStringEmpty(email)) cardView.setVisibility(View.GONE);

        if (isStringEmpty(phoneNumber)) {
            rlPhone.setVisibility(View.GONE);
        } else {
            tvPhone.setText(String.valueOf(person.getPhoneNumber()));
        }

        if (isStringEmpty(email)) {
            rlEmail.setVisibility(View.GONE);
        } else {
            tvEmail.setText(person.getEmail());
        }
    }

    private boolean isStringEmpty(String text) {
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
        intent.putExtra(ConstantManager.TIME_STAMP, timeStamp);
        startActivityForResult(intent, ConstantManager.EDIT_ACTIVITY);
        overridePendingTransition(R.anim.activity_secondary_in, R.anim.activity_primary_out);
    }

    /**
     * Sets up image depending on month
     */
    private void setSeasonImage() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int month = calendar.get(Calendar.MONTH);
        if (month >= 0 && month < 2 || month == 11) {
            imageView.setImageResource(R.drawable.img_winter);
        } else if (month >= 2 && month < 5) {
            imageView.setImageResource(R.drawable.img_spring);
        } else if (month >= 5 && month < 8) {
            imageView.setImageResource(R.drawable.img_summer);
        } else imageView.setImageResource(R.drawable.img_autumn);
    }

    private void deleteDialog(final Person person) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.delete_record_text) + " " + person.getName() + "?");
        builder.setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlarmHelper.getInstance().removeAlarms(timeStamp);
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


    private void logEvent() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.CONTENT_TYPE, ConstantManager.EDIT_ACTIVITY_TAG);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);
    }

    @OnClick(R.id.ibPhoneIcon)
    void makeCall() {
        mFirebaseAnalytics.logEvent(ConstantManager.MAKE_CALL, new Bundle());
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(ConstantManager.TEL + phoneNumber)));
    }

    @OnClick(R.id.ibChatIcon)
    void sendMessage() {
        mFirebaseAnalytics.logEvent(ConstantManager.SEND_MESSAGE, new Bundle());
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
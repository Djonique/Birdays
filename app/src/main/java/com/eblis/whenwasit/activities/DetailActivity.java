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

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eblis.whenwasit.BuildConfig;
import com.eblis.whenwasit.R;
import com.eblis.whenwasit.adapters.FamousFragmentAdapter;
import com.eblis.whenwasit.database.DbHelper;
import com.eblis.whenwasit.models.AnniversaryType;
import com.eblis.whenwasit.models.DisplayedAge;
import com.eblis.whenwasit.models.Person;
import com.eblis.whenwasit.utils.CommunicationHelper;
import com.eblis.whenwasit.utils.Constants;
import com.eblis.whenwasit.utils.Utils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.kobakei.ratethisapp.RateThisApp;

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
    @BindView(R.id.textview_detail_label)
    TextView tvAnniversaryLabel;
    @BindView(R.id.imageview_detail_zodiac)
    ImageView ivZodiacSign;
    @BindView(R.id.textview_detail_zodiac)
    TextView tvZodiacSign;
    @BindView(R.id.textview_detail_zodiac_label)
    TextView tvZodiacSignLabel;
    @BindView(R.id.textview_detail_contact_category)
    TextView tvContactCategory;
    @BindView(R.id.textview_detail_contact_category_label)
    TextView getTvContactCategoryLabel;
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
    @BindView(R.id.imagebutton_detail_whatsapp)
    ImageButton ibWhatsapp;

    private InterstitialAd mInterstitialAd;
    private DbHelper dbHelper;
    private Person person;
    private long recordId;
    private DisplayedAge displayedAge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean adEnabled = preferences.getBoolean(getString(R.string.ad_interstitial_key), true);
        displayedAge = Utils.getDisplayedAge(preferences.getString(Constants.DISPLAYED_AGE_KEY, DisplayedAge.CURRENT.name()));

        /*
        * Interstitial doesn't work on Android API 26+
        * java.lang.IllegalStateException: Only fullscreen activities can request orientation
        */
        mInterstitialAd = new InterstitialAd(this);
        if (adEnabled) {
            final DetailActivity activity = this;
            mInterstitialAd.setAdUnitId(BuildConfig.INTERSTITIAL_AD_ID);
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            mInterstitialAd.setAdListener(new AdListener()
            {
                @Override
                public void onAdFailedToLoad(int i) {
                        Toast.makeText(activity, "Interstitial AD failed to load: " + i, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdLoaded() {
                    mInterstitialAd.show();
                }
            });
        }

        dbHelper = new DbHelper(this);

        Utils.setupDayNightTheme(preferences);

        Intent intent = getIntent();
        recordId = intent.getLongExtra(Constants.RECORD_ID, 0);
        person = dbHelper.query().getPerson(recordId);

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
            refresh.putExtra(Constants.RECORD_ID, recordId);
            startActivity(refresh);
            this.finish();
        }
    }

    /**
     * Set up UI depending on person's data
     */
    private void setupUI() {
        final Activity activity = this;
        if (!setContactImage()) {
            setSeasonImage();
        }

        ivSeasonPicture.setClickable(true);

        View.OnClickListener openDetails = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI,
                        String.valueOf(person.getContactId()));
                intent.setData(uri);
                activity.startActivity(intent);
            }
        };
        ivSeasonPicture.setOnClickListener(openDetails);

        tvDaysLeft.setText(Utils.daysLeftPretty(this, person));
        tvAnniversaryLabel.setText(person.getAnniversaryLabel());
        tvContactCategory.setText(person.getContactCategory());

        if (person.isYearUnknown()) {
            tvDate.setText(Utils.getDateWithoutYear(person.getDate()));
            tvAge.setVisibility(View.GONE);
            rlDaysSinceBirthday.setVisibility(View.GONE);
        } else {
            tvDate.setText(Utils.getDate(person.getDate()));
            tvDate.setOnClickListener(openDetails);
            tvAge.setText(String.valueOf(Utils.getAge(person.getDate(), displayedAge)));
            tvDaysSinceBirthday.setText(Utils.daysSinceBirthday(person.getDate()));
        }

        if (person.getAnniversaryType() == AnniversaryType.BIRTHDAY) {
            int zodiacId = Utils.getZodiacId(person.getDate());
            tvZodiacSign.setText(getString(zodiacId));
            ivZodiacSign.setImageResource(Utils.getZodiacImage(zodiacId));
        }
        else {
            tvZodiacSign.setVisibility(View.GONE);
            tvZodiacSignLabel.setVisibility(View.GONE);
            ivZodiacSign.setVisibility(View.GONE);
        }

        final String phoneNumber = person.getPhoneNumber();
        final String email = person.getEmail();
        if (isEmpty(phoneNumber) && isEmpty(email))
            cardViewInfo.setVisibility(View.GONE);

        if (isEmpty(phoneNumber)) {
            rlPhoneNumber.setVisibility(View.GONE);
        } else {
            tvPhoneNumber.setText(String.valueOf(person.getPhoneNumber()));
            if (!CommunicationHelper.hasWhatsapp(this, person)) {
                ibWhatsapp.setEnabled(false);
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ibWhatsapp.setColorFilter(new ColorMatrixColorFilter(matrix));
            }
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

        List<Person> famousPersons = dbHelper.query().getFamousBornThisDay(person.getDate());
        for (int i = 0; i < famousPersons.size(); i++) {
            adapter.addPerson(famousPersons.get(i));
        }
    }

    @OnClick(R.id.fab_detail)
    void startEditActivity() {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(Constants.RECORD_ID, recordId);
        startActivityForResult(intent, EDIT_ACTIVITY);
        overridePendingTransition(R.anim.activity_secondary_in, R.anim.activity_primary_out);
    }

    private boolean setContactImage() {
        final Bitmap picture = Utils.getContactPicture(this, person);
        if (picture != null) {
            ivSeasonPicture.setImageBitmap(picture);
        }

        return picture != null;
    }

    /**
     * Set up image depending on month
     */
    private void setSeasonImage() {
        final int month = person.getMonth();
        //probably better with if's and ranges, but i find this easier to see what's going on
        switch (month) {
            case 12:
            case 1:
            case 2:
                ivSeasonPicture.setImageResource(R.drawable.img_winter);
                break;
            case 3:
            case 4:
            case 5:
                ivSeasonPicture.setImageResource(R.drawable.img_spring);
                break;
            case 6:
            case 7:
            case 8:
                ivSeasonPicture.setImageResource(R.drawable.img_summer);
                break;
            case 9:
            case 10:
            case 11:
                ivSeasonPicture.setImageResource(R.drawable.img_autumn);
                break;
        }
    }

    private void deletePersonDialog(final Person person) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.delete_record_text) + person.getName() + "?");
        builder.setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.removeRecord(recordId);
                Utils.notifyWidget(getApplicationContext());
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

    @OnClick(R.id.imagebutton_detail_phone)
    void makeCall() {
        CommunicationHelper.call(this, person.getPhoneNumber());
    }

    @OnClick(R.id.imagebutton_detail_chat)
    void sendMessage() {
        CommunicationHelper.sms(this, person.getPhoneNumber());
    }

    @OnClick(R.id.imagebutton_detail_whatsapp)
    void sendWhatsapp() {
        CommunicationHelper.whatsapp(this, person);
    }

    @OnClick(R.id.imagebutton_detail_generic_message)
    void sendMessageWithSystemPicker() {
        CommunicationHelper.genericContact(this, person);
    }

    @OnClick(R.id.textview_detail_generic_message)
    void sendMessageWithSystemPicker2() {
        sendMessageWithSystemPicker();
    }

    @OnClick(R.id.imagebutton_detail_email)
    void sendEmail() {
        CommunicationHelper.sendEmail(this, person.getEmail());
    }

    @OnClick(R.id.textview_detail_email)
    void sendEmail2() {
        sendEmail();
    }
}
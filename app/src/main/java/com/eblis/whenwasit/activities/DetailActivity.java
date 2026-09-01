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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.adapters.FamousFragmentAdapter;
import com.eblis.whenwasit.database.DbHelper;
import com.eblis.whenwasit.databinding.ActivityDetailBinding;
import com.eblis.whenwasit.models.AnniversaryType;
import com.eblis.whenwasit.models.DisplayedAge;
import com.eblis.whenwasit.models.Person;
import com.eblis.whenwasit.utils.CommunicationHelper;
import com.eblis.whenwasit.utils.Constants;
import com.eblis.whenwasit.utils.Utils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.kobakei.ratethisapp.RateThisApp;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private static final int INSTALL_DAYS = 7;
    private static final int LAUNCH_TIMES = 5;
    private static final int EDIT_ACTIVITY = 4;

    private ActivityDetailBinding binding;
    private DbHelper dbHelper;
    private Person person;
    private long recordId;
    private DisplayedAge displayedAge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.appbarDetail, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(v.getPaddingLeft(), insets.top, v.getPaddingRight(), v.getPaddingBottom());
            return windowInsets;
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean adEnabled = preferences.getBoolean(getString(R.string.ad_interstitial_key), true);
        displayedAge = Utils.getDisplayedAge(preferences.getString(Constants.DISPLAYED_AGE_KEY, DisplayedAge.CURRENT.name()));

        /*
        * Interstitial doesn't work on Android API 26+
        * java.lang.IllegalStateException: Only fullscreen activities can request orientation
        */
        if (adEnabled) {
            final DetailActivity activity = this;
            InterstitialAd.load(this, getString(R.string.interstitial_ad_id), new AdRequest.Builder().build(), new InterstitialAdLoadCallback()
            {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    Toast.makeText(activity, "Interstitial AD failed to load: " + loadAdError.toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                }
            });
        }

        dbHelper = new DbHelper(this);

        Utils.setupDayNightTheme(preferences);

        Intent intent = getIntent();
        recordId = intent.getLongExtra(Constants.RECORD_ID, 0);
        person = dbHelper.query().getPerson(recordId);

        if (person != null) {
            binding.toolbarDetail.setTitle(person.getName());
        }
        setSupportActionBar(binding.toolbarDetail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        setupUI();

        loadBornThisDay();

        binding.recyclerviewDetail.setFocusable(false);

        binding.fabDetail.setOnClickListener(v -> startEditActivity());
        binding.imagebuttonDetailPhone.setOnClickListener(v -> makeCall());
        binding.imagebuttonDetailChat.setOnClickListener(v -> sendMessage());
        binding.imagebuttonDetailWhatsapp.setOnClickListener(v -> sendWhatsapp());
        binding.imagebuttonDetailGenericMessage.setOnClickListener(v -> sendMessageWithSystemPicker());
        binding.textviewDetailGenericMessage.setOnClickListener(v -> sendMessageWithSystemPicker2());
        binding.imagebuttonDetailEmail.setOnClickListener(v -> sendEmail());
        binding.textviewDetailEmail.setOnClickListener(v -> sendEmail2());

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

        binding.imageviewDetailPicture.getRoot().setClickable(true);

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
        binding.imageviewDetailPicture.getRoot().setOnClickListener(openDetails);

        binding.textviewDetailLeft.setText(Utils.daysLeftPretty(this, person));
        binding.textviewDetailLabel.setText(person.getAnniversaryLabel());
        binding.textviewDetailContactCategory.setText(person.getContactCategory());

        if (person.isYearUnknown()) {
            binding.textviewDetailDate.setText(Utils.getDateWithoutYear(person.getDate()));
            binding.textviewDetailAge.setVisibility(View.GONE);
            binding.relativelayoutDetailSince.setVisibility(View.GONE);
        } else {
            binding.textviewDetailDate.setText(Utils.getDate(person.getDate()));
            binding.textviewDetailDate.setOnClickListener(openDetails);
            binding.textviewDetailAge.setText(String.valueOf(Utils.getAge(person.getDate(), displayedAge)));
            binding.textviewDetailSince.setText(Utils.daysSinceBirthday(person.getDate()));
        }

        if (person.getAnniversaryType() == AnniversaryType.BIRTHDAY) {
            int zodiacId = Utils.getZodiacId(person.getDate());
            binding.textviewDetailZodiac.setText(getString(zodiacId));
            binding.imageviewDetailZodiac.setImageResource(Utils.getZodiacImage(zodiacId));
        }
        else {
            binding.textviewDetailZodiac.setVisibility(View.GONE);
            binding.textviewDetailZodiacLabel.setVisibility(View.GONE);
            binding.imageviewDetailZodiac.setVisibility(View.GONE);
        }

        final String phoneNumber = person.getPhoneNumber();
        final String email = person.getEmail();
        if (isEmpty(phoneNumber) && isEmpty(email))
            binding.cardviewDetailInfo.setVisibility(View.GONE);

        if (isEmpty(phoneNumber)) {
            binding.relativelayoutDetailPhone.setVisibility(View.GONE);
        } else {
            binding.textviewDetailPhone.setText(String.valueOf(person.getPhoneNumber()));
            if (!CommunicationHelper.hasWhatsapp(this, person)) {
                binding.imagebuttonDetailWhatsapp.setEnabled(false);
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                binding.imagebuttonDetailWhatsapp.setColorFilter(new ColorMatrixColorFilter(matrix));
            }
        }

        if (isEmpty(email)) {
            binding.relativelayoutDetailEmail.setVisibility(View.GONE);
        } else {
            binding.textviewDetailEmail.setText(person.getEmail());
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
        binding.recyclerviewDetail.setLayoutManager(manager);

        FamousFragmentAdapter adapter = new FamousFragmentAdapter();
        binding.recyclerviewDetail.setAdapter(adapter);

        List<Person> famousPersons = dbHelper.query().getFamousBornThisDay(person.getDate());
        for (int i = 0; i < famousPersons.size(); i++) {
            adapter.addPerson(famousPersons.get(i));
        }
    }

    void startEditActivity() {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(Constants.RECORD_ID, recordId);
        startActivityForResult(intent, EDIT_ACTIVITY);
        overridePendingTransition(R.anim.activity_secondary_in, R.anim.activity_primary_out);
    }

    private boolean setContactImage() {
        final Bitmap picture = Utils.getContactPicture(this, person);
        if (picture != null) {
            ((ImageView) binding.imageviewDetailPicture.getRoot()).setImageBitmap(picture);
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
                ((ImageView) binding.imageviewDetailPicture.getRoot()).setImageResource(R.drawable.img_winter);
                break;
            case 3:
            case 4:
            case 5:
                ((ImageView) binding.imageviewDetailPicture.getRoot()).setImageResource(R.drawable.img_spring);
                break;
            case 6:
            case 7:
            case 8:
                ((ImageView) binding.imageviewDetailPicture.getRoot()).setImageResource(R.drawable.img_summer);
                break;
            case 9:
            case 10:
            case 11:
                ((ImageView) binding.imageviewDetailPicture.getRoot()).setImageResource(R.drawable.img_autumn);
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

    void makeCall() {
        CommunicationHelper.call(this, person.getPhoneNumber());
    }

    void sendMessage() {
        CommunicationHelper.sms(this, person.getPhoneNumber());
    }

    void sendWhatsapp() {
        CommunicationHelper.whatsapp(this, person);
    }

    void sendMessageWithSystemPicker() {
        CommunicationHelper.genericContact(this, person);
    }

    void sendMessageWithSystemPicker2() {
        sendMessageWithSystemPicker();
    }

    void sendEmail() {
        CommunicationHelper.sendEmail(this, person.getEmail());
    }

    void sendEmail2() {
        sendEmail();
    }
}
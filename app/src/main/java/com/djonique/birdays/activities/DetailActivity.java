package com.djonique.birdays.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.djonique.birdays.ads.Ad;
import com.djonique.birdays.R;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.IntentHelper;
import com.djonique.birdays.utils.Utils;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.ivPicture)
    ImageView imageView;
    @BindView(R.id.tvDetailDate)
    TextView tvDate;
    @BindView(R.id.tvZodiac)
    TextView tvZodiac;
    @BindView(R.id.tvZodiacImage)
    TextView tvZodiacImage;
    @BindView(R.id.tvDetailAge)
    TextView tvAge;
    @BindView(R.id.tvDetailPhone)
    TextView tvPhone;
    @BindView(R.id.tvDetailEmail)
    TextView tvEmail;
    @BindView(R.id.rlAge)
    RelativeLayout rlAge;
    @BindView(R.id.rlPhone)
    RelativeLayout rlPhone;
    @BindView(R.id.rlEmail)
    RelativeLayout rlEmail;
    @BindView(R.id.viewDivider)
    View view;
    @BindView(R.id.toolbar_detail)
    Toolbar toolbar;

    private Intent mainIntent;
    private Person person;

    private String phoneNumber, email;
    private long date, timeStamp;
    private int position;
    private boolean unknownYear;

    private int winterImages[] = {R.drawable.img_winter_0,
            R.drawable.img_winter_1,
            R.drawable.img_winter_2,
            R.drawable.img_winter_3,
            R.drawable.img_winter_4};
    private int springImages[] = {R.drawable.img_spring_0,
            R.drawable.img_spring_1,
            R.drawable.img_spring_2,
            R.drawable.img_spring_3,
            R.drawable.img_spring_4};
    private int summerImages[] = {R.drawable.img_summer_0,
            R.drawable.img_summer_1,
            R.drawable.img_summer_2,
            R.drawable.img_summer_3,
            R.drawable.img_summer_4};
    private int autumnImages[] = {R.drawable.img_autumn_0,
            R.drawable.img_autumn_1,
            R.drawable.img_autumn_2,
            R.drawable.img_autumn_3,
            R.drawable.img_autumn_4};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Ad.showBanner(findViewById(R.id.container_detail), (AdView) findViewById(R.id.banner_detail));

        DBHelper dbHelper = new DBHelper(getApplicationContext());

        mainIntent = new Intent();
        Intent intent = getIntent();

        timeStamp = intent.getLongExtra(ConstantManager.TIME_STAMP, 0);
        position = intent.getIntExtra(ConstantManager.SELECTED_ITEM, 0);
        person = dbHelper.query().getPerson(timeStamp);
        date = person.getDate();
        unknownYear = person.isYearUnknown();
        phoneNumber = person.getPhoneNumber();
        email = person.getEmail();

        toolbar.setTitle(person.getName());
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }

        updateUI();
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
                mainIntent.putExtra(ConstantManager.POSITION, position);
                setResult(RESULT_OK, mainIntent);
                finish();
                overridePendingTransition(R.anim.detail_main_in, R.anim.detail_main_out);
                break;
            case R.id.menu_detail_share:
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType(ConstantManager.TEXT_PLAIN);
                intentShare.putExtra(Intent.EXTRA_TEXT, getString(R.string.menu_share));
                startActivity(Intent.createChooser(intentShare, getString(R.string.app_name)));
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED, mainIntent);
        finish();
        overridePendingTransition(R.anim.detail_main_in, R.anim.detail_main_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (resultCode == RESULT_OK) {
            Intent refresh = new Intent(this, DetailActivity.class);
            refresh.putExtra(ConstantManager.TIME_STAMP, timeStamp);
            startActivity(refresh);
            this.finish();
        }
    }

    private void updateUI() {
        setSeasonImage();

        tvZodiac.setText(Utils.getZodiac(date));
        tvZodiacImage.setText(Utils.getZodiacImage(date));

        if (unknownYear) {
            tvDate.setText(Utils.getUnknownDate(date));
            rlAge.setVisibility(View.GONE);
        } else {
            tvDate.setText(Utils.getDate(date));
            tvAge.setText(String.valueOf(Utils.getAge(date)));
        }

        if (phoneNumber == null && email == null) {
            view.setVisibility(View.INVISIBLE);
            rlPhone.setVisibility(View.INVISIBLE);
            rlEmail.setVisibility(View.INVISIBLE);
        }
        if (phoneNumber == null) {
            rlPhone.setVisibility(View.GONE);
        } else {
            tvPhone.setText(String.valueOf(person.getPhoneNumber()));
        }

        if (email == null) {
            rlEmail.setVisibility(View.INVISIBLE);
        } else {
            tvEmail.setText(person.getEmail());
        }
    }

    @OnClick(R.id.fab_detail)
    void starEditActivity() {
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
        intent.putExtra(ConstantManager.TIME_STAMP, timeStamp);
        startActivityForResult(intent, ConstantManager.EDIT_ACTIVITY);
        overridePendingTransition(R.anim.detail_edit_in, R.anim.detail_edit_out);
    }

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

    private void setPicture(ImageView imageView, int[] pictures) {
        imageView.setImageResource(pictures[(int) (Math.random() * 5)]);
    }

    @OnClick(R.id.ibPhoneIcon)
    void call() {
        IntentHelper.call(getApplicationContext(), phoneNumber);
    }

    @OnClick(R.id.ibChatIcon)
    void sendSMS() {
        IntentHelper.sendSms(getApplicationContext(), phoneNumber);
    }

    @OnClick(R.id.ibEmailIcon)
    void sendEmail() {
        IntentHelper.sendEmail(getApplicationContext(), email);
    }
}

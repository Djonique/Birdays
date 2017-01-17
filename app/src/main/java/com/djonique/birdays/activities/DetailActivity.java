package com.djonique.birdays.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.djonique.birdays.R;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.model.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.Utils;

import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView tvDate, tvZodiac, tvZodiacImage, tvAge, tvPhone, tvEmail;
    private RelativeLayout rlEmail, rlPhone, rlAge;
    private View view;
    private Toolbar toolbar;
    private Intent mainIntent;

    private long date, timeStamp;
    private int position;

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

        mainIntent = new Intent();

        initUI();

        Intent intent = getIntent();
        timeStamp = intent.getLongExtra(ConstantManager.TIME_STAMP, 0);
        position = intent.getIntExtra(ConstantManager.SELECTED_ITEM, 0);
        DBHelper dbHelper = new DBHelper(getApplicationContext());

        Person person = dbHelper.query().getPerson(timeStamp);
        date = person.getDate();
        boolean unknownYear = person.isYearUnknown();
        String phoneNumber = person.getPhoneNumber();
        String email = person.getEmail();
        toolbar.setTitle(person.getName());
        setSupportActionBar(toolbar);

        setSeasonImage();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }

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

    private void initUI() {
        imageView = ((ImageView) findViewById(R.id.ivPicture));
        tvDate = ((TextView) findViewById(R.id.tvDetailDate));
        tvZodiac = ((TextView) findViewById(R.id.tvZodiac));
        tvZodiacImage = ((TextView) findViewById(R.id.tvZodiacImage));
        tvAge = ((TextView) findViewById(R.id.tvDetailAge));
        tvPhone = ((TextView) findViewById(R.id.tvDetailPhone));
        tvEmail = ((TextView) findViewById(R.id.tvDetailEmail));
        rlEmail = ((RelativeLayout) findViewById(R.id.rlEmail));
        rlPhone = ((RelativeLayout) findViewById(R.id.rlPhone));
        rlAge = ((RelativeLayout) findViewById(R.id.rlAge));
        view = findViewById(R.id.viewDivider);
        toolbar = ((Toolbar) findViewById(R.id.toolbar_detail));
        FloatingActionButton fab = ((FloatingActionButton) findViewById(R.id.fab_detail));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra(ConstantManager.TIME_STAMP, timeStamp);
                startActivityForResult(intent, ConstantManager.EDIT_ACTIVITY);
                overridePendingTransition(R.anim.detail_edit_in, R.anim.detail_edit_out);
            }
        });
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

    private void setPicture(ImageView imageView, int[] pictures) {
        imageView.setImageResource(pictures[(int) (Math.random() * 5)]);
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
}

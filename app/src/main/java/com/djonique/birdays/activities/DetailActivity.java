package com.djonique.birdays.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.djonique.birdays.R;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.model.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.Utils;

import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {

    private TextView tvPhone, tvEmail, tvDate, tvAge;
    private ImageView imageView;

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

        initUI();

        Intent intent = getIntent();
        long timeStamp = intent.getLongExtra(ConstantManager.TIME_STAMP, 0);
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        Person person = dbHelper.query().getPerson(timeStamp);
        long date = person.getDate();

        Toolbar toolbar = ((Toolbar) findViewById(R.id.toolbar_detail));
        toolbar.setTitle(person.getName());
        setSupportActionBar(toolbar);

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

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }

        tvPhone.setText(String.valueOf(person.getPhoneNumber()));
        tvEmail.setText(person.getEmail());
        tvDate.setText(Utils.getDate(date));
        tvAge.setText(String.valueOf(Utils.getAge(date)));
    }

    private void initUI() {
        imageView = ((ImageView) findViewById(R.id.image_detail));
        tvPhone = ((TextView) findViewById(R.id.tvDetailPhone));
        tvEmail = ((TextView) findViewById(R.id.tvDetailEmail));
        tvDate = ((TextView) findViewById(R.id.tvDetailDate));
        tvAge = ((TextView) findViewById(R.id.tvDetailAge));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_back_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                break;
        }
        return true;
    }

    private void setPicture(ImageView imageView, int[] pictures) {
        imageView.setImageResource(pictures[(int) (Math.random() * 5)]);
    }
}

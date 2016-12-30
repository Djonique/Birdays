package com.djonique.birdays.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.djonique.birdays.R;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.model.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.Utils;

public class DetailActivity extends AppCompatActivity {

    TextView tvName, tvPhone, tvEmail, tvDate, tvAge;

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

        tvName.setText(person.getName());
        tvPhone.setText(String.valueOf(person.getPhoneNumber()));
        tvEmail.setText(person.getEmail());
        tvDate.setText(Utils.getDate(date));
        tvAge.setText(String.valueOf(Utils.getAge(date)));
    }

    private void initUI() {
        Toolbar toolbar = ((Toolbar) findViewById(R.id.toolbarDetail));
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }

        tvName = ((TextView) findViewById(R.id.tvDetailName));
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
}

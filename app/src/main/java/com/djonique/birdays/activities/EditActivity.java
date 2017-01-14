package com.djonique.birdays.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.djonique.birdays.R;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.model.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Calendar calendar;
    private EditText etName, etDate, etPhone, etEmail;
    private DBHelper dbHelper;
    private Person person;
    private TextInputLayout tilEditDate, tilEditName;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dbHelper = new DBHelper(this);
        calendar = Calendar.getInstance();

        Intent intent = getIntent();
        long timeStamp = intent.getLongExtra(ConstantManager.TIME_STAMP, 0);
        person = dbHelper.query().getPerson(timeStamp);

        etName = ((EditText) findViewById(R.id.etEditName));
        etDate = ((EditText) findViewById(R.id.etEditDate));
        etPhone = ((EditText) findViewById(R.id.etEditPhone));
        etEmail = ((EditText) findViewById(R.id.etEditEmail));
        tilEditDate = ((TextInputLayout) findViewById(R.id.tilEditDate));
        tilEditName = ((TextInputLayout) findViewById(R.id.tilEditName));

        etName.setText(person.getName());
        etName.setSelection(etName.getText().length());
        etDate.setText(Utils.getDate(person.getDate()));
        etPhone.setText(person.getPhoneNumber());
        etEmail.setText(person.getEmail());

        calendar.setTimeInMillis(person.getDate());

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd =
                        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                                EditActivity.this,
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                        );
                dpd.show(getFragmentManager(), ConstantManager.DATE_PICKER_FRAGMENT_TAG);
            }
        });

        fab = ((FloatingActionButton) findViewById(R.id.fab_edit));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePerson();
                Intent update = new Intent();
                setResult(RESULT_OK, update);
                overridePendingTransition(R.anim.edit_detail_in, R.anim.edit_detail_out);
                finish();
            }
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    fab.hide();
                    tilEditName.setError(getString(R.string.error_hint));
                } else {
                    if (!Utils.isEmptyDate(etDate) && Utils.isRightDate(calendar)) {
                        fab.show();
                    }
                    tilEditName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        etDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!Utils.isEmptyDate(etDate) && Utils.isRightDate(calendar)) {
                    fab.show();
                    tilEditDate.setErrorEnabled(false);
                } else if (Utils.isEmptyDate(etDate)) {
                    fab.hide();
                    tilEditDate.setError(getString(R.string.wrong_date));
                } else if (!Utils.isRightDate(calendar)) {
                    fab.hide();
                    tilEditDate.setError(getString(R.string.not_vanga));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.edit_detail_in, R.anim.edit_detail_out);
    }

    private void updatePerson() {
        person.setName(updateText(etName));
        person.setDate(calendar.getTimeInMillis());
        person.setPhoneNumber(updateText(etPhone));
        person.setEmail(updateText(etEmail));
        dbHelper.updateRec(person);
    }

    private String updateText(EditText editText) {
        String result = null;
        if (editText != null && editText.length() != 0) {
            result = editText.getText().toString();
        }
        return result;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        etDate.setText(Utils.getDate(calendar.getTimeInMillis()));
    }
}

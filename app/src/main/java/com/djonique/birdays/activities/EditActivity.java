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
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

import com.djonique.birdays.R;
import com.djonique.birdays.alarm.AlarmHelper;
import com.djonique.birdays.database.DbHelper;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.Constants;
import com.djonique.birdays.utils.DatePickerManager;
import com.djonique.birdays.utils.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class EditActivity extends AppCompatActivity implements
        android.app.DatePickerDialog.OnDateSetListener,
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {

    @BindView(R.id.til_edit_name)
    TextInputLayout tilEditName;
    @BindView(R.id.edittext_edit_name)
    EditText etName;
    @BindView(R.id.til_edit_date)
    TextInputLayout tilEditDate;
    @BindView(R.id.edittext_edit_date)
    EditText etDate;
    @BindView(R.id.checkbox_edit)
    AppCompatCheckBox checkBox;
    @BindView(R.id.edittext_edit_phone)
    EditText etPhoneNumber;
    @BindView(R.id.edittext_edit_email)
    EditText etEmail;

    private DbHelper dbHelper;
    private Person person;
    private Calendar calendar;
    private long date;
    private boolean yearUnknown;
    private boolean hideOkButton = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        long timeStamp = intent.getLongExtra(Constants.TIME_STAMP, 0);

        dbHelper = new DbHelper(this);
        person = dbHelper.query().getPerson(timeStamp);
        yearUnknown = person.isYearUnknown();

        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(person.getDate());

        setupUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        if (hideOkButton) menu.findItem(R.id.menu_edit_ok).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_edit_ok:
                updatePerson();
                setAlarms(person);
                Utils.notifyWidget(this);
                setResult(RESULT_OK, new Intent());
                finish();
                this.overridePendingTransition(R.anim.activity_primary_in, R.anim.activity_secondary_out);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_primary_in, R.anim.activity_secondary_out);
    }

    /**
     * Set up UI depending on person's data
     */
    private void setupUI() {
        etName.setText(person.getName());
        etName.setSelection(etName.getText().length());

        date = person.getDate();
        if (yearUnknown) {
            etDate.setText(Utils.getDateWithoutYear(date));
        } else {
            etDate.setText(Utils.getDate(date));
        }

        checkBox.setChecked(yearUnknown);
        etPhoneNumber.setText(person.getPhoneNumber());
        etEmail.setText(person.getEmail());
    }

    /**
     * Updates person's data after editing
     */
    private void updatePerson() {
        person.setName(updateText(etName));
        person.setDate(calendar.getTimeInMillis());
        person.setYearUnknown(checkBox.isChecked());
        person.setPhoneNumber(updateText(etPhoneNumber));
        person.setEmail(updateText(etEmail));
        dbHelper.updateRecord(person);
    }

    /**
     * Used to update text in updatePerson() method
     */
    private String updateText(EditText editText) {
        String result = "";
        if (editText != null && editText.length() != 0) {
            result = editText.getText().toString();
        }
        return result;
    }

    private void setAlarms(Person person) {
        AlarmHelper alarmHelper = new AlarmHelper(this);
        alarmHelper.removeAlarms(person);
        alarmHelper.setAlarms(person);
    }

    @OnClick(R.id.edittext_edit_date)
    void pickDate() {
        new DatePickerManager(this, calendar).showDialog(this, this);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        setDate(year, month, dayOfMonth);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        setDate(year, monthOfYear, dayOfMonth);
    }

    private void setDate(int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTimeInMillis();
        // Checks state of CheckBox whenever date is picked
        if (!checkBox.isChecked()) {
            etDate.setText(Utils.getDate(date));
        } else {
            etDate.setText(Utils.getDateWithoutYear(date));
        }
    }

    @OnTextChanged(value = R.id.edittext_edit_name, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void validateName() {
        if (etName.length() == 0) {
            tilEditName.setError(getString(R.string.error_hint));
            hideOkButton = true;
        } else {
            if (fieldsValid()) {
                hideOkButton = false;
            }
            tilEditName.setErrorEnabled(false);
        }
        invalidateOptionsMenu();
    }

    @OnTextChanged(value = R.id.edittext_edit_date, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void validateDate() {
        if (fieldsValid()) {
            tilEditDate.setErrorEnabled(false);
            if (etName.length() != 0) {
                hideOkButton = false;
            }
        } else {
            tilEditDate.setError(getString(R.string.wrong_date));
            hideOkButton = true;
        }
        invalidateOptionsMenu();
    }

    @OnCheckedChanged(R.id.checkbox_edit)
    void checkBoxListener() {
        if (checkBox.isChecked()) {
            etDate.setText(Utils.getDateWithoutYear(date));
        } else {
            etDate.setText(Utils.getDate(date));
        }
        if (fieldsValid()) {
            tilEditDate.setErrorEnabled(false);
            if (etName.length() != 0) {
                hideOkButton = false;
            }
        } else {
            tilEditDate.setError(getString(R.string.wrong_date));
            hideOkButton = true;
        }
        invalidateOptionsMenu();
    }

    private boolean fieldsValid() {
        return (!Utils.isEmptyDate(etDate) && Utils.isRightDate(calendar))
                || (!Utils.isEmptyDate(etDate) && checkBox.isChecked());
    }
}
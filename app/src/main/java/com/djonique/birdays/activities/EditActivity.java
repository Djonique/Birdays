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
import android.widget.EditText;

import com.djonique.birdays.R;
import com.djonique.birdays.alarm.AlarmHelper;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class EditActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.tilEditName)
    TextInputLayout tilEditName;
    @BindView(R.id.etEditName)
    EditText etName;
    @BindView(R.id.tilEditDate)
    TextInputLayout tilEditDate;
    @BindView(R.id.etEditDate)
    EditText etDate;
    @BindView(R.id.cbEdit)
    AppCompatCheckBox checkBox;
    @BindView(R.id.etEditPhone)
    EditText etPhone;
    @BindView(R.id.etEditEmail)
    EditText etEmail;

    private DBHelper dbHelper;
    private Person person;
    private Calendar calendar;
    private long date;
    private boolean unknownYear;
    private boolean hide = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        long timeStamp = intent.getLongExtra(ConstantManager.TIME_STAMP, 0);

        dbHelper = new DBHelper(this);
        person = dbHelper.query().getPerson(timeStamp);
        unknownYear = person.isYearUnknown();

        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(person.getDate());

        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        if (hide) menu.findItem(R.id.menu_edit_ok).setVisible(false);
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
     * Updates UI depending on person's info
     */
    private void updateUI() {
        etName.setText(person.getName());
        etName.setSelection(etName.getText().length());

        date = person.getDate();
        if (unknownYear) {
            etDate.setText(Utils.getDateWithoutYear(date));
        } else {
            etDate.setText(Utils.getDate(date));
        }

        checkBox.setChecked(unknownYear);
        etPhone.setText(person.getPhoneNumber());
        etEmail.setText(person.getEmail());
    }

    /**
     * Updates person's info after editing
     */
    private void updatePerson() {
        String name = updateText(etName);
        person.setName(name);
        person.setLowerCaseName(name.toLowerCase());
        person.setDate(calendar.getTimeInMillis());
        person.setYearUnknown(checkBox.isChecked());
        person.setPhoneNumber(updateText(etPhone));
        person.setEmail(updateText(etEmail));
        dbHelper.updateRec(person);
    }

    /**
     * Uses to update text in updatePerson() method
     */
    private String updateText(EditText editText) {
        String result = "";
        if (editText != null && editText.length() != 0) {
            result = editText.getText().toString();
        }
        return result;
    }

    private void setAlarms(Person person) {
        AlarmHelper alarmHelper = AlarmHelper.getInstance();
        alarmHelper.removeAlarms(person.getTimeStamp());
        alarmHelper.setAlarms(person);
    }

    @OnClick(R.id.etEditDate)
    void pickDate() {
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd =
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                        EditActivity.this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
        dpd.show(getFragmentManager(), ConstantManager.DATE_PICKER_FRAGMENT_TAG);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTimeInMillis();
        // Checks state of CheckBox whenever date is picked
        if (!checkBox.isChecked()) {
            etDate.setText(Utils.getDate(date));
        } else {
            etDate.setText(Utils.getDateWithoutYear(date));
        }
    }

    @OnTextChanged(value = R.id.etEditName, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void validateName() {
        if (etName.length() == 0) {
            tilEditName.setError(getString(R.string.error_hint));
            hide = true;
        } else {
            if (areFieldsValid()) {
                hide = false;
            }
            tilEditName.setErrorEnabled(false);
        }
        invalidateOptionsMenu();
    }

    @OnTextChanged(value = R.id.etEditDate, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void validateDate() {
        if (areFieldsValid()) {
            tilEditDate.setErrorEnabled(false);
            if (etName.length() != 0) {
                hide = false;
            }
        } else {
            tilEditDate.setError(getString(R.string.wrong_date));
            hide = true;
        }
        invalidateOptionsMenu();
    }

    @OnCheckedChanged(R.id.cbEdit)
    void checkBoxListener() {
        if (checkBox.isChecked()) {
            etDate.setText(Utils.getDateWithoutYear(date));
        } else {
            etDate.setText(Utils.getDate(date));
        }
        if (areFieldsValid()) {
            tilEditDate.setErrorEnabled(false);
            if (etName.length() != 0) {
                hide = false;
            }
        } else {
            tilEditDate.setError(getString(R.string.wrong_date));
            hide = true;
        }
        invalidateOptionsMenu();
    }

    private boolean areFieldsValid() {
        return (!Utils.isEmptyDate(etDate) && Utils.isRightDate(calendar))
                || (!Utils.isEmptyDate(etDate) && checkBox.isChecked());
    }
}
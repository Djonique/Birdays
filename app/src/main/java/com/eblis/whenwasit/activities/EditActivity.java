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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.database.DbHelper;
import com.eblis.whenwasit.models.Person;
import com.eblis.whenwasit.utils.Constants;
import com.eblis.whenwasit.utils.DatePickerManager;
import com.eblis.whenwasit.utils.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.joda.time.LocalDate;

import java.util.Calendar;
import java.util.Locale;

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
    @BindView(R.id.edittext_edit_anniversaryType)
    EditText etAnniversaryLabel;
    @BindView(R.id.edittext_edit_contactCategory)
    AutoCompleteTextView actvContactCategoryLabel;

    private DbHelper dbHelper;
    private Person person;
    private Calendar calendar;
    private boolean hideOkButton = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        final Intent intent = getIntent();
        final long recordId = intent.getLongExtra(Constants.RECORD_ID, 0);

        dbHelper = new DbHelper(this);
        person = dbHelper.query().getPerson(recordId);
        calendar = person.getDate().toDateTimeAtCurrentTime().toCalendar(Locale.getDefault());
        final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbHelper.getContactCategories());
        actvContactCategoryLabel.setAdapter(adapter);

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

        if (person.isYearUnknown()) {
            etDate.setText(Utils.getDateWithoutYear(person.getDate()));
        } else {
            etDate.setText(Utils.getDate(person.getDate()));
        }

        checkBox.setChecked(person.isYearUnknown());
        etPhoneNumber.setText(person.getPhoneNumber());
        etEmail.setText(person.getEmail());
        etAnniversaryLabel.setText(person.getAnniversaryLabel());
        actvContactCategoryLabel.setText(person.getContactCategory());
    }

    /**
     * Updates person's data after editing
     */
    private void updatePerson() {
        person.setName(updateText(etName));
        person.setDate(new LocalDate(calendar));
        person.setYearUnknown(checkBox.isChecked());
        person.setPhoneNumber(updateText(etPhoneNumber));
        person.setEmail(updateText(etEmail));
        person.setAnniversaryLabel(updateText(etAnniversaryLabel));
        person.setContactCategory(updateText(actvContactCategoryLabel));
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
        LocalDate date = new LocalDate(calendar);
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
            etDate.setText(Utils.getDateWithoutYear(person.getDate()));
        } else {
            etDate.setText(Utils.getDate(person.getDate()));
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
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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.database.DbHelper;
import com.eblis.whenwasit.databinding.ActivityEditBinding;
import com.eblis.whenwasit.models.Person;
import com.eblis.whenwasit.utils.Constants;
import com.eblis.whenwasit.utils.DatePickerManager;
import com.eblis.whenwasit.utils.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.joda.time.LocalDate;

import java.util.Calendar;
import java.util.Locale;

public class EditActivity extends AppCompatActivity implements
        android.app.DatePickerDialog.OnDateSetListener,
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {

    private ActivityEditBinding binding;
    private DbHelper dbHelper;
    private Person person;
    private Calendar calendar;
    private boolean hideOkButton = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.containerEdit, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return windowInsets;
        });

        final Intent intent = getIntent();
        final long recordId = intent.getLongExtra(Constants.RECORD_ID, 0);

        dbHelper = new DbHelper(this);
        person = dbHelper.query().getPerson(recordId);
        if (person != null && person.getDate() != null) {
            calendar = person.getDate().toDateTimeAtCurrentTime().toCalendar(Locale.getDefault());
        }
        final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbHelper.getContactCategories());
        binding.edittextEditContactCategory.setAdapter(adapter);

        setupUI();

        binding.edittextEditDate.setOnClickListener(v -> pickDate());
        binding.edittextEditName.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateName();
            }
        });
        binding.edittextEditDate.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateDate();
            }
        });
        binding.checkboxEdit.setOnCheckedChangeListener((buttonView, isChecked) -> checkBoxListener());
    }

    private abstract static class SimpleTextWatcher implements android.text.TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void afterTextChanged(android.text.Editable s) {}
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
        binding.edittextEditName.setText(person.getName());
        binding.edittextEditName.setSelection(binding.edittextEditName.getText().length());

        if (person.isYearUnknown()) {
            binding.edittextEditDate.setText(Utils.getDateWithoutYear(person.getDate()));
        } else {
            binding.edittextEditDate.setText(Utils.getDate(person.getDate()));
        }

        binding.checkboxEdit.setChecked(person.isYearUnknown());
        binding.edittextEditPhone.setText(person.getPhoneNumber());
        binding.edittextEditEmail.setText(person.getEmail());
        binding.edittextEditAnniversaryType.setText(person.getAnniversaryLabel());
        binding.edittextEditContactCategory.setText(person.getContactCategory());
    }

    /**
     * Updates person's data after editing
     */
    private void updatePerson() {
        person.setName(updateText(binding.edittextEditName));
        person.setDate(new LocalDate(calendar));
        person.setYearUnknown(binding.checkboxEdit.isChecked());
        person.setPhoneNumber(updateText(binding.edittextEditPhone));
        person.setEmail(updateText(binding.edittextEditEmail));
        person.setAnniversaryLabel(updateText(binding.edittextEditAnniversaryType));
        person.setContactCategory(updateText(binding.edittextEditContactCategory));
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
        if (!binding.checkboxEdit.isChecked()) {
            binding.edittextEditDate.setText(Utils.getDate(date));
        } else {
            binding.edittextEditDate.setText(Utils.getDateWithoutYear(date));
        }
    }

    void validateName() {
        if (binding.edittextEditName.length() == 0) {
            binding.tilEditName.setError(getString(R.string.error_hint));
            hideOkButton = true;
        } else {
            if (fieldsValid()) {
                hideOkButton = false;
            }
            binding.tilEditName.setErrorEnabled(false);
        }
        invalidateOptionsMenu();
    }

    void validateDate() {
        if (fieldsValid()) {
            binding.tilEditDate.setErrorEnabled(false);
            if (binding.edittextEditName.length() != 0) {
                hideOkButton = false;
            }
        } else {
            binding.tilEditDate.setError(getString(R.string.wrong_date));
            hideOkButton = true;
        }
        invalidateOptionsMenu();
    }

    void checkBoxListener() {
        if (binding.checkboxEdit.isChecked()) {
            binding.edittextEditDate.setText(Utils.getDateWithoutYear(person.getDate()));
        } else {
            binding.edittextEditDate.setText(Utils.getDate(person.getDate()));
        }
        if (fieldsValid()) {
            binding.tilEditDate.setErrorEnabled(false);
            if (binding.edittextEditName.length() != 0) {
                hideOkButton = false;
            }
        } else {
            binding.tilEditDate.setError(getString(R.string.wrong_date));
            hideOkButton = true;
        }
        invalidateOptionsMenu();
    }

    private boolean fieldsValid() {
        return (!Utils.isEmptyDate(binding.edittextEditDate) && Utils.isRightDate(calendar))
                || (!Utils.isEmptyDate(binding.edittextEditDate) && binding.checkboxEdit.isChecked());
    }
}
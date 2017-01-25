package com.djonique.birdays.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.djonique.birdays.ads.Ad;
import com.djonique.birdays.R;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.Utils;
import com.google.android.gms.ads.AdView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    CheckBox checkBox;
    @BindView(R.id.etEditPhone)
    EditText etPhone;
    @BindView(R.id.etEditEmail)
    EditText etEmail;
    @BindView(R.id.fab_edit)
    FloatingActionButton fab;

    private Calendar calendar;
    private DBHelper dbHelper;
    private Person person;
    private boolean unknownYear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ButterKnife.bind(this);

        Ad.showBanner(findViewById(R.id.container_edit), (AdView) findViewById(R.id.banner_edit));

        dbHelper = new DBHelper(this);
        calendar = Calendar.getInstance();

        Intent intent = getIntent();
        long timeStamp = intent.getLongExtra(ConstantManager.TIME_STAMP, 0);
        person = dbHelper.query().getPerson(timeStamp);
        unknownYear = person.isYearUnknown();

        updateUI();

        calendar.setTimeInMillis(person.getDate());
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

    private void updateUI() {
        etName.setText(person.getName());
        etName.setSelection(etName.getText().length());

        if (unknownYear) {
            etDate.setText(Utils.getUnknownDate(person.getDate()));
        } else {
            etDate.setText(Utils.getDate(person.getDate()));
        }

        checkBox.setChecked(unknownYear);
        etPhone.setText(person.getPhoneNumber());
        etEmail.setText(person.getEmail());
    }

    @OnClick(R.id.fab_edit)
    void submit() {
        updatePerson();
        Intent update = new Intent();
        setResult(RESULT_OK, update);
        finish();
        overridePendingTransition(R.anim.edit_detail_in, R.anim.edit_detail_out);
    }

    private void updatePerson() {
        person.setName(updateText(etName));
        person.setDate(calendar.getTimeInMillis());
        person.setYearUnknown(checkBox.isChecked());
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
        etDate.setText(Utils.getDate(calendar.getTimeInMillis()));
    }

    @OnTextChanged(value = R.id.etEditName, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void validate() {
        if (etName.length() == 0) {
            fab.hide();
            tilEditName.setError(getString(R.string.error_hint));
        } else {
            if (!Utils.isEmptyDate(etDate) && Utils.isRightDate(calendar)) {
                fab.show();
            }
            tilEditName.setErrorEnabled(false);
        }
    }

    @OnTextChanged(value = R.id.etEditDate, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void updateDate() {
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
}

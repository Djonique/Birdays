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

package com.djonique.birdays.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.djonique.birdays.R;
import com.djonique.birdays.alarm.AlarmHelper;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.ContactsHelper;
import com.djonique.birdays.utils.PermissionHelper;
import com.djonique.birdays.utils.Utils;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Calendar;

public class NewPersonDialogFragment extends DialogFragment implements
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {

    private AddingPersonListener addingPersonListener;
    private SharedPreferences preferences;
    private EditText etName, etPhone, etEmail, etDate;
    private AppCompatCheckBox cbKnownYear;
    private Calendar calendar;
    private String name;
    private long date;
    private FirebaseAnalytics mFirebaseAnalytics;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            addingPersonListener = (AddingPersonListener) activity;
            preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.add_new_record);

        calendar = Calendar.getInstance();
        final Person person = new Person();

        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams")
        View container = inflater.inflate(R.layout.fragment_dialog, null);

        AppCompatButton addFromContactsButton =
                ((AppCompatButton) container.findViewById(R.id.button_dialog_afc));
        addFromContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PermissionHelper.permissionGranted(getActivity())) {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, ConstantManager.REQUEST_READ_CONTACTS);
                } else {
                    PermissionHelper.requestPermission(getActivity());
                }
            }
        });

        final TextInputLayout tilName = (TextInputLayout) container.findViewById(R.id.til_dialog_name);
        etName = tilName.getEditText();

        final TextInputLayout tilPhone = (TextInputLayout) container.findViewById(R.id.til_dialog_phone);
        etPhone = tilPhone.getEditText();

        final TextInputLayout tilEmail = (TextInputLayout) container.findViewById(R.id.til_dialog_email);
        etEmail = tilEmail.getEditText();

        final TextInputLayout tilDate = ((TextInputLayout) container.findViewById(R.id.til_dialog_date));
        etDate = (EditText) container.findViewById(R.id.edittext_dialog_date);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etDate.length() == 0) {
                    etDate.setText("");
                }
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd =
                        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                                NewPersonDialogFragment.this,
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                        );
                dpd.setThemeDark(preferences.getBoolean(ConstantManager.NIGHT_MODE_KEY, false));
                dpd.show(getFragmentManager(), ConstantManager.DATE_PICKER_FRAGMENT_TAG);
            }
        });

        cbKnownYear = ((AppCompatCheckBox) container.findViewById(R.id.checkbox_dialog));

        builder.setView(container);
        builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mFirebaseAnalytics.logEvent(ConstantManager.NEW_PERSON_ADDED, new Bundle());
                if (etName != null) {
                    name = etName.getText().toString();
                    person.setName(name);
                    person.setLowerCaseName(name.toLowerCase());
                }

                if (!Utils.isEmptyDate(etDate)) {
                    person.setDate(calendar.getTimeInMillis());
                    new AlarmHelper(getActivity()).setAlarms(person);
                }

                if (cbKnownYear != null) person.setYearUnknown(cbKnownYear.isChecked());

                if (etPhone != null && etPhone.length() != 0) {
                    person.setPhoneNumber(etPhone.getText().toString());
                } else {
                    person.setPhoneNumber("");
                }

                if (etEmail != null && etEmail.length() != 0) {
                    person.setEmail(etEmail.getText().toString());
                } else {
                    person.setEmail("");
                }

                addingPersonListener.onPersonAdded(person);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addingPersonListener.onPersonAddedCancel();
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                final Button positiveButton =
                        ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);

                if (etDate != null && etDate.length() == 0) positiveButton.setEnabled(false);

                if (etName != null && etName.length() == 0) {
                    positiveButton.setEnabled(false);
                    tilName.setError(getString(R.string.error_hint));
                }

                etName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() == 0) {
                            tilName.setError(getString(R.string.error_hint));
                            positiveButton.setEnabled(false);
                        } else {
                            tilName.setErrorEnabled(false);
                            if (!Utils.isEmptyDate(etDate) && Utils.isRightDate(calendar) || !Utils.isEmptyDate(etDate) && cbKnownYear.isChecked()) {
                                positiveButton.setEnabled(true);
                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                etDate.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (!Utils.isEmptyDate(etDate) && Utils.isRightDate(calendar)
                                || !Utils.isEmptyDate(etDate) && cbKnownYear.isChecked()) {
                            tilDate.setErrorEnabled(false);
                            if (etName.length() != 0) {
                                positiveButton.setEnabled(true);
                            }
                        } else {
                            tilDate.setError(getString(R.string.wrong_date));
                            positiveButton.setEnabled(false);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });

                cbKnownYear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        // If date isn't picked does nothing, if picked checks state of CheckBox
                        if (cbKnownYear.isChecked() && !Utils.isEmptyDate(etDate)) {
                            etDate.setText(Utils.getDateWithoutYear(date));
                        } else if (!cbKnownYear.isChecked() && !Utils.isEmptyDate(etDate)) {
                            etDate.setText(Utils.getDate(date));
                        }

                        // Doesn't allow to add Person if conditions are not met and shows error
                        if (!Utils.isEmptyDate(etDate) && Utils.isRightDate(calendar) || !Utils.isEmptyDate(etDate) && cbKnownYear.isChecked()) {
                            tilDate.setErrorEnabled(false);
                            if (etName.length() != 0) {
                                positiveButton.setEnabled(true);
                            }
                        } else {
                            tilDate.setError(getString(R.string.wrong_date));
                            positiveButton.setEnabled(false);
                        }
                    }
                });
            }
        });
        return alertDialog;
    }

    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view,
                          int yearPicked, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.YEAR, yearPicked);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTimeInMillis();
        // Checks state of CheckBox whenever date is picked
        if (!cbKnownYear.isChecked()) {
            etDate.setText(Utils.getDate(date));
        } else {
            etDate.setText(Utils.getDateWithoutYear(date));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri contactData = data.getData();
            ContentResolver contentResolver = getActivity().getContentResolver();
            ContactsHelper contactsHelper = new ContactsHelper(getActivity(), contentResolver);
            Cursor cursor = contentResolver.query(contactData, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    String id = cursor.getString(
                            cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    etName.setText(contactsHelper.getContactName(contentResolver, id));
                    etPhone.setText(contactsHelper.getContactPhoneNumber(contentResolver, id));
                    etEmail.setText(contactsHelper.getContactEmail(contentResolver, id));
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public interface AddingPersonListener {

        void onPersonAdded(Person person);

        void onPersonAddedCancel();
    }
}
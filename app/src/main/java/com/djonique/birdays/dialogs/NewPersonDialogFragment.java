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

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.djonique.birdays.utils.ContactsInfo;
import com.djonique.birdays.utils.Utils;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Calendar;

public class NewPersonDialogFragment extends DialogFragment implements
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {

    private AddingPersonListener addingPersonListener;
    private EditText etName, etPhone, etEmail, etDate;
    private AppCompatCheckBox cbKnownYear;
    private Calendar calendar;
    private String name, phone, email;
    private FirebaseAnalytics mFirebaseAnalytics;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            addingPersonListener = (AddingPersonListener) activity;
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

        final TextInputLayout tilName = (TextInputLayout) container.findViewById(R.id.tilName);
        etName = tilName.getEditText();

        final TextInputLayout tilPhone = (TextInputLayout) container.findViewById(R.id.tilPhone);
        etPhone = tilPhone.getEditText();

        Button addFromContactsButton =
                ((Button) container.findViewById(R.id.addFromContactsDialogButton));
        addFromContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_CONTACTS) ==
                        PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, ConstantManager.REQUEST_READ_CONTACTS);
                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_CONTACTS},
                            ConstantManager.CONTACTS_REQUEST_PERMISSION_CODE);
                }
            }
        });

        final TextInputLayout tilEmail = (TextInputLayout) container.findViewById(R.id.tilEmail);
        etEmail = tilEmail.getEditText();

        final TextInputLayout tilDate = ((TextInputLayout) container.findViewById(R.id.tilDate));
        etDate = (EditText) container.findViewById(R.id.etDate);
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
                dpd.show(getFragmentManager(), ConstantManager.DATE_PICKER_FRAGMENT_TAG);
            }
        });

        cbKnownYear = ((AppCompatCheckBox) container.findViewById(R.id.cbKnownYear));

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

                    AlarmHelper alarmHelper = AlarmHelper.getInstance();
                    alarmHelper.setAlarm(person);
                }

                if (cbKnownYear != null) person.setYearUnknown(cbKnownYear.isChecked());

                if (etPhone != null && etPhone.length() != 0) {
                    phone = etPhone.getText().toString();
                    person.setPhoneNumber(phone);
                } else person.setPhoneNumber(null);

                if (etEmail != null && etEmail.length() != 0) {
                    email = etEmail.getText().toString();
                    person.setEmail(email);
                } else person.setEmail(null);
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

                if (etName != null && etName.length() == 0) {
                    positiveButton.setEnabled(false);
                    tilName.setError(getString(R.string.error_hint));

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
                }

                etDate.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
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

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                cbKnownYear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
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
        etDate.setText(Utils.getDate(calendar.getTimeInMillis()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri contactData = data.getData();
            ContentResolver contentResolver = getActivity().getContentResolver();
            Cursor cursor = contentResolver.query(contactData, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    String id = cursor.getString(
                            cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    etName.setText(ContactsInfo.retrieveName(contentResolver, id));
                    etPhone.setText(ContactsInfo.retrievePhoneNumber(contentResolver, cursor, id));
                    etEmail.setText(ContactsInfo.retrieveEmail(contentResolver, id));
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

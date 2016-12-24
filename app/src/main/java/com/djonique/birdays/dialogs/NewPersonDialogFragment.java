package com.djonique.birdays.dialogs;

import android.Manifest;
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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.djonique.birdays.AlarmHelper;
import com.djonique.birdays.R;
import com.djonique.birdays.model.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.Utils;

import java.util.Calendar;

public class NewPersonDialogFragment extends DialogFragment implements
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {

    private AddingPersonListener addingPersonListener;
    private EditText etName, etPhone, etEmail, etDate;
    private Calendar calendar;
    private String name, email;
    private long date, phone;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            addingPersonListener = (AddingPersonListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /*if (savedInstanceState != null) {
            name = savedInstanceState.getString(ConstantManager.NAME);
            phone = savedInstanceState.getLong(ConstantManager.PHONE);
            email = savedInstanceState.getString(ConstantManager.EMAIL);
            date = savedInstanceState.getLong(ConstantManager.DATE);
        }*/

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.add_new_record);

        calendar = Calendar.getInstance();
        final Person person = new Person();

        LayoutInflater inflater = getActivity().getLayoutInflater();
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

        builder.setView(container);
        builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (etName != null) {
                    name = etName.getText().toString();
                    person.setName(name);
                    person.setLowerCaseName(name.toLowerCase());
                }

                if (!isEmptyDate()) {
                    date = calendar.getTimeInMillis();
                    person.setDate(date);

                    AlarmHelper alarmHelper = AlarmHelper.getInstance();
                    alarmHelper.setAlarm(person);
                }

                if (etPhone != null && etPhone.length() != 0) {
                    // TODO: 21.12.2016 БД должна хранить телефон в стринге
                    phone = Long.parseLong(etPhone.getText().toString());
                    person.setPhoneNumber(phone);
                } else person.setPhoneNumber(0);

                if (etEmail != null && etEmail.length() != 0) {
                    email = etEmail.getText().toString();
                    person.setEmail(email);
                } else person.setEmail(" ");
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
                                positiveButton.setEnabled(false);
                                tilName.setError(getString(R.string.error_hint));
                            } else {
                                if (!isEmptyDate() && isRightDate()) {
                                    positiveButton.setEnabled(true);
                                }
                                tilName.setErrorEnabled(false);
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
                        if (!isEmptyDate() && isRightDate()) {
                            positiveButton.setEnabled(true);
                            tilDate.setErrorEnabled(false);
                        } else if (isEmptyDate()) {
                            positiveButton.setEnabled(false);
                            tilDate.setError("Wrong date");
                        } else if (!isRightDate()) {
                            positiveButton.setEnabled(false);
                            tilDate.setError("You aren't Vanga");
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

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

    private boolean isEmptyDate() {
        return TextUtils.isEmpty(etDate.getText().toString());
    }

    private boolean isRightDate() {
        long today = Calendar.getInstance().getTimeInMillis();
        return today >= calendar.getTimeInMillis();
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
                    etPhone.setText(getPhoneFromContacts(contentResolver, cursor, id));
                    etEmail.setText(getEmailFromContacts(contentResolver, id));
                }
            }
            cursor.close();
        }
    }

    // Retrieves phone number from picked contact
    private String getPhoneFromContacts(ContentResolver contentResolver, Cursor cursor, String id) {
        String phoneNumber = "";
        if (Integer.parseInt(cursor.getString(
                cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
            Cursor phoneCursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[]{id}, null);
            if (phoneCursor.moveToFirst()) {
                phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex
                        (ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phoneCursor.close();
        }
        return phoneNumber;
    }

    // Retrieves email from picked contact
    private String getEmailFromContacts(ContentResolver contentResolver, String id) {
        String email = "";
        Cursor emailCursor = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                new String[]{id}, null);
        if (emailCursor.moveToFirst()) {
            email = emailCursor.getString(
                    emailCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DATA));
        }
        emailCursor.close();
        return email;
    }

  /*  @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ConstantManager.NAME, name);
        outState.putLong(ConstantManager.PHONE, phone);
        outState.putString(ConstantManager.EMAIL, email);
        outState.putLong(ConstantManager.DATE, date);
    }*/

    public interface AddingPersonListener {

        void onPersonAdded(Person person);

        void onPersonAddedCancel();
    }
}

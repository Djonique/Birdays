package com.djonique.birdays.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.djonique.birdays.R;
import com.djonique.birdays.Utils;
import com.djonique.birdays.model.Person;

import java.util.Calendar;

public class NewPersonDialogFragment extends DialogFragment implements
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {

    public static final String DATE_PICKER_FRAGMENT_TAG = "DatePickerFragment";
    private EditText etDate;
    private Calendar calendar;
    private int year, month, day;
    private AddingPersonListener addingPersonListener;

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

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.add_new_record);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View container = inflater.inflate(R.layout.dialog_add_new_person, null);

        etDate = (EditText) container.findViewById(R.id.etDate);

        final TextInputLayout tilName = (TextInputLayout) container.findViewById(R.id.tilName);
        final EditText etName = tilName.getEditText();

        final TextInputLayout tilPhone = (TextInputLayout) container.findViewById(R.id.tilPhone);
        final EditText etPhone = tilPhone.getEditText();

        final TextInputLayout tilEmail = (TextInputLayout) container.findViewById(R.id.tilEmail);
        final EditText etEmail = tilEmail.getEditText();

        calendar = Calendar.getInstance();

        final Person person = new Person();

        builder.setView(container);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etDate.length() == 0) {
                    etDate.setText(" ");
                }
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd =
                        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                                NewPersonDialogFragment.this,
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                        );

                dpd.show(getFragmentManager(), DATE_PICKER_FRAGMENT_TAG);
            }
        });

        builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assert etName != null;
                String name = etName.getText().toString();
                person.setName(name);
                person.setLowerCaseName(name.toLowerCase());
                if (etDate.length() != 0) {
                    long time = calendar.getTimeInMillis();
                    person.setDate(time);
                    int age = Utils.getAge(year, month, day);
                    person.setAge(age);
                }
                if (etPhone != null && etPhone.length() != 0) {
                    person.setPhoneNumber(Long.parseLong(etPhone.getText().toString()));
                } else person.setPhoneNumber(0);
                if (etEmail != null && etEmail.length() != 0) {
                    person.setEmail(etEmail.getText().toString());
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
                final Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
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
                                positiveButton.setEnabled(true);
                                tilName.setErrorEnabled(false);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }

            }
        });

        return alertDialog;
    }

    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view,
                          int yearPicked, int monthOfYear, int dayOfMonth) {
        year = yearPicked;
        month = monthOfYear;
        day = dayOfMonth;
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        etDate.setText(Utils.getDate(calendar.getTimeInMillis()));
    }

    public interface AddingPersonListener {

        void onPersonAdded(Person person);

        void onPersonAddedCancel();
    }
}

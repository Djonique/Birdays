package com.djonique.birdays.utils;


public interface ConstantManager {
    // Dialog
    String NEW_PERSON_DIALOG_TAG = "NEW_PERSON_DIALOG_TAG";
    String DATE_PICKER_FRAGMENT_TAG = "DATE_PICKER_FRAGMENT_TAG";
    String NAME = "NAME";
    String PHONE = "PHONE";
    String EMAIL = "EMAIL";
    String DATE = "DATE";

    int REQUEST_READ_CONTACTS = 1;
    int CONTACTS_REQUEST_PERMISSION_CODE = 2;

    // MonthFragment
    String TYPE_EMAIL = "message/rfc822";
    String MAILTO = "mailto:";
    String TYPE_SMS = "vnd.android-dir/mms-sms";
    String ADDRESS = "address";
    String SMSTO = "smsto:";
    String TEL = "tel: ";
}

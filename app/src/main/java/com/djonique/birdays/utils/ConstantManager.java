package com.djonique.birdays.utils;


public interface ConstantManager {
    String NEW_PERSON_DIALOG_TAG = "NEW_PERSON_DIALOG_TAG";
    String DATE_PICKER_FRAGMENT_TAG = "DATE_PICKER_FRAGMENT_TAG";

    String TYPE_EMAIL = "message/rfc822";
    String MAILTO = "mailto:";
    String TYPE_SMS = "vnd.android-dir/mms-sms";
    String ADDRESS = "address";
    String SMSTO = "smsto:";
    String TEL = "tel: ";

    int REQUEST_READ_CONTACTS = 1;
    int CONTACTS_REQUEST_PERMISSION_CODE = 2;
}

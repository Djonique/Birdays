package com.djonique.birdays.utils;

public interface ConstantManager {

    // Share
    String TEXT_PLAIN = "text/plain";

    // Dialog
    String NEW_PERSON_DIALOG_TAG = "NEW_PERSON_DIALOG_TAG";
    String DATE_PICKER_FRAGMENT_TAG = "DATE_PICKER_FRAGMENT_TAG";

    // Permission requests
    int REQUEST_READ_CONTACTS = 1;
    int CONTACTS_REQUEST_PERMISSION_CODE = 2;

    // AllFragment
    String TIME_STAMP = "time_stamp";
    String SELECTED_ITEM = "selected_item";
    int DETAIL_ACTIVITY = 101;

    // DetailActivity intent
    int EDIT_ACTIVITY = 102;
    String POSITION = "position";

    // Alarm
    String NAME = "name";
    String NOTIFICATIONS = "notifications";
    String NOTIFICATION_TIME = "notification_time";

    // Intents
    String TYPE_EMAIL = "message/rfc822";
    String MAILTO = "mailto:";
    String TYPE_SMS = "vnd.android-dir/mms-sms";
    String ADDRESS = "address";
    String SMSTO = "smsto:";
    String TEL = "tel: ";

    // Firebase Analytics
    String NEW_PERSON_ADDED = "new_person_added";
    String MAKE_CALL = "make_call";
    String SEND_SMS = "send_sms";
    String SEND_EMAIL = "send_email";
    String FAMOUS_PERSON_CLICKED = "famous_person_clicked";
    String SHARE_APP = "share_app";
    String DETAIL_ACTIVITY_TAG = "DetailActivity";
    String EDIT_ACTIVITY_TAG = "EditActivity";
    String SETTINGS_ACTIVITY_TAG = "SettingsActivity";
}

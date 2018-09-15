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

package com.eblis.whenwasit.utils;

public interface Constants {

    // Preferences
    String CONTACTS_UPLOADED = "CONTACTS_UPLOADED";
    String WRONG_CONTACTS_FORMAT = "WRONG_CONTACTS_FORMAT";

    // Permission requests
    int READ_CONTACTS_PERMISSION_CODE = 1;
    int WRITE_EXTERNAL_STORAGE_PERMISSION_CODE = 2;
    int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 3;

    // Alarm
    String NAME = "NAME";
    String WHEN = "WHEN";
    String ANNIVERSARY_LABEL = "ANNIVERSARY_LABEL";
    String TIME_STAMP = "TIME_STAMP";

    // Settings keys
    String NOTIFICATIONS_KEY = "notifications_key";
    String NOTIFICATION_TIME_KEY = "notification_time_key";
    String ADDITIONAL_NOTIFICATION_KEY = "additional_notification_key_multi";
    String RINGTONE_KEY = "ringtone_key";
    String NIGHT_MODE_KEY = "night_mode_key";
    String START_PAGE = "start_page_key";
    String DISPLAYED_AGE_KEY = "displayed_age_key";
    String AUTOMATIC_CONTACT_IMPORT_KEY = "import_contacts_automatically_key";

    // Intents
    String TYPE_EMAIL = "message/rfc822";
    String MAILTO = "mailto:";
    String TYPE_SMS = "vnd.android-dir/mms-sms";
    String ADDRESS = "address";
    String SMSTO = "smsto:";
    String TEL = "tel: ";
}
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

package com.djonique.birdays.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class ContactsInfo {

    /**
     * Returns name from certain contact
     */
    public static String getContactName(ContentResolver contentResolver, String id) {
        String name = null;
        Cursor nameCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ?",
                new String[]{id}, null);
        if (nameCursor != null && nameCursor.moveToFirst()) {
            name =
                    nameCursor.getString(nameCursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
        }
        if (nameCursor != null) {
            nameCursor.close();
        }
        return name;
    }

    /**
     * Returns phone number from certain contact
     */
    public static String getContactPhoneNumber(ContentResolver contentResolver, String id) {
        String phoneNumber = null;
        Cursor phoneCursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[]{id}, null);
        if (phoneCursor != null && phoneCursor.moveToFirst()) {
            phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex
                    (ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
        if (phoneCursor != null) {
            phoneCursor.close();
        }
        return phoneNumber;
    }

    /**
     * Returns email from certain contact
     */
    public static String getContactEmail(ContentResolver contentResolver, String id) {
        String email = null;
        Cursor emailCursor = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                new String[]{id}, null);
        if (emailCursor != null && emailCursor.moveToFirst()) {
            email = emailCursor.getString(
                    emailCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DATA));
        }
        if (emailCursor != null) {
            emailCursor.close();
        }
        return email;
    }

    /**
     * Returns all contacts with specified Birthdays
     */
    public static Cursor getContacts(ContentResolver contentResolver) {
        Uri uri = ContactsContract.Data.CONTENT_URI;

        String[] projection = new String[]{
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Event.CONTACT_ID,
                ContactsContract.CommonDataKinds.Event.START_DATE,
        };

        String where =
                ContactsContract.Data.MIMETYPE
                        + "= ? AND "
                        + ContactsContract.CommonDataKinds.Event.TYPE
                        + "="
                        + ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY;
        String[] selectionArgs = new String[]{ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE};
        return contentResolver.query(uri, projection, where, selectionArgs, null);
    }
}
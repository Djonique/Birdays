package com.djonique.birdays.utils;


import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

public class ContactsInfo {

    // Retrieves name from picked contact
    public static String retrieveName(ContentResolver contentResolver, String id) {
        String phoneNumber = null;
        Cursor phoneCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ?",
                new String[]{id}, null);
        if (phoneCursor != null && phoneCursor.moveToFirst()) {
            phoneNumber =
                    phoneCursor.getString(phoneCursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
        }
        if (phoneCursor != null) {
            phoneCursor.close();
        }
        return phoneNumber;
    }

    // Retrieves phone number from picked contact
    public static String retrievePhoneNumber(ContentResolver contentResolver, Cursor cursor, String id) {
        String phoneNumber = null;
        if (Integer.parseInt(cursor.getString(
                cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
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
        }
        return phoneNumber;
    }

    // Retrieves email from picked contact
    public static String retrieveEmail(ContentResolver contentResolver, String id) {
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
}

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

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.widget.Toast;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.database.DbHelper;
import com.eblis.whenwasit.models.AnniversaryType;
import com.eblis.whenwasit.models.Person;

import java.util.ArrayList;
import java.util.List;

public class ContactsHelper {

    private Context context;
    private final Activity activity;
    private ContentResolver contentResolver;
    private LoadingContactsListener loadingContactsListener;

    public ContactsHelper(Context context, ContentResolver contentResolver) {
        this.context = context;
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }
        else {
            this.activity = null;
        }
        this.contentResolver = contentResolver;
        loadingContactsListener = (LoadingContactsListener) activity;
    }

    /**
     * Returns name from certain contact
     */
    public String getContactName(ContentResolver contentResolver, String id) {
        String name = "";
        Cursor nameCursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null,
                ContactsContract.Contacts._ID + " = ?",
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
    public synchronized String getContactPhoneNumber(ContentResolver contentResolver, String id) {
        final Cursor phoneCursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[]{id}, null);
        if (phoneCursor != null) {
            try {
                phoneCursor.moveToFirst();
                return phoneCursor.getString(phoneCursor.getColumnIndex
                        (ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            catch (Exception ex) {
                //no-op
            }
            finally {
                try {
                    phoneCursor.close();
                }
                catch (Exception ex) {
                    //nothing we can do :(
                }
            }
        }

        return "";
    }

    /**
     * Returns email from certain contact
     */
    public String getContactEmail(ContentResolver contentResolver, String id) {
        String email = "";
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

    private String getTypeLabel(Resources res, int type, String label) {
        if (type == ContactsContract.CommonDataKinds.BaseTypes.TYPE_CUSTOM && !TextUtils.isEmpty(label)) {
            return label;
        } else {
            final int labelRes = ContactsContract.CommonDataKinds.Event.getTypeResource(type);
            return res.getText(labelRes).toString();
        }
    }

    private AnniversaryType getAnniversaryType(int type) {
        switch (type) {
            case ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY:
                return AnniversaryType.BIRTHDAY;

            case ContactsContract.CommonDataKinds.Event.TYPE_ANNIVERSARY:
            default:
                return AnniversaryType.ANNIVERSARY;
        }
    }

    /**
     * Returns all contacts with Birthdays
     */
    private List<Person> getAllContactsWithBirthdays(ContentResolver contentResolver) {

        List<Person> contacts = new ArrayList<>();

        Cursor cursor = getContactsCursor(contentResolver);

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    final String id = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Event.CONTACT_ID));
                    final String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    final String dateString = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE));
                    final int type = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.TYPE));
                    final String label = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Event.LABEL));
                    final String anniversary = getTypeLabel(context.getResources(), type, label);
                    final String category = context.getResources().getString(R.string.friends);

                    final long date;
                    try {
                        date = Utils.formatDateToLong(dateString);
                    } catch (Exception e) {
                        continue;
                    }
                    if (date == 0) continue;

                    boolean yearUnknown = Utils.isYearUnknown(dateString);
                    String phoneNumber = getContactPhoneNumber(contentResolver, id);
                    String email = getContactEmail(contentResolver, id);

                    Person person = new Person(Long.valueOf(id), name, date, yearUnknown, phoneNumber, email, anniversary, getAnniversaryType(type), category);
                    contacts.add(person);
                }
            }
            finally {
                cursor.close();
            }
        }

        return contacts;
    }

    /**
     * Returns cursor with contacts with specified Birthdays
     */
    private Cursor getContactsCursor(ContentResolver contentResolver) {
        Uri uri = ContactsContract.Data.CONTENT_URI;

       /* String[] projection = new String[]{
                ContactsContract.Contacts.DISPLAY_NAME,
             /   ContactsContract.CommonDataKinds.Event.CONTACT_ID,
                ContactsContract.CommonDataKinds.Event.START_DATE,
        };*/

        String where =
                ContactsContract.Data.MIMETYPE
                        + "= ?";
//                        + ContactsContract.CommonDataKinds.Event.TYPE
//                        + "="
//                        + ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY;
        String[] selectionArgs = new String[]{ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE};
        return contentResolver.query(uri, null, where, selectionArgs, null);
    }

    /**
     * Loads all persons with Birthdays from Contacts, compares them with persons from Database and
     * saves them into DB, sets alarm for added persons
     */
    public void loadContactsWithProgress(SharedPreferences preferences) {
        if (PermissionManager.readingContactsPermissionGranted(context)) {
            try {
                new ContactsAsyncLoading().execute();
                preferences.edit().putBoolean(Constants.CONTACTS_UPLOADED, true).apply();
            } catch (Exception e) {
                preferences.edit().putBoolean(Constants.WRONG_CONTACTS_FORMAT, true).apply();
                Toast.makeText(context, R.string.loading_contacts_error, Toast.LENGTH_LONG).show();
            }
        } else {
            if (activity != null) {
                PermissionManager.requestReadingContactsPermission(activity);
            }
        }
    }

    public void updateContactsNow() {
        DbHelper dbHelper = new DbHelper(context);

        List<Person> dbPersons = dbHelper.query().getPersons();
        List<Person> contacts = getAllContactsWithBirthdays(contentResolver);

        for (Person person : contacts) {
            final Person existing = Utils.getPersonAlreadyInDb(person, dbPersons);
            if (existing == null) {
                dbHelper.addRecord(person);
                dbPersons.add(person);
            }
            else {
                person.setTimeStamp(existing.getTimeStamp()); //make sure they're the same for update purposes
                dbHelper.updateRecord(person);
            }
        }
    }

    public interface LoadingContactsListener {
        void onContactsUploaded();
    }

    private class ContactsAsyncLoading extends AsyncTask<Void, Void, Void> {

        ProgressDialogHelper progressDialogHelper = new ProgressDialogHelper(context);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialogHelper.startProgressDialog(context.getString(R.string.loading_contacts));
        }

        @Override
        protected Void doInBackground(Void... params) {
            updateContactsNow();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialogHelper.dismissProgressDialog();
            loadingContactsListener.onContactsUploaded();
            Utils.notifyWidget(context);
            Toast.makeText(context, R.string.contacts_uploaded, Toast.LENGTH_SHORT).show();
        }
    }
}
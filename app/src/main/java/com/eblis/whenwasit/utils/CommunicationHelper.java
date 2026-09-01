package com.eblis.whenwasit.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.widget.Toast;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.models.Person;

import java.net.URLEncoder;

public class CommunicationHelper {
    public static void sendEmail(Context context, String email) {
        context.startActivity(Intent.createChooser(new Intent(Intent.ACTION_SENDTO)
                .setType(Constants.TYPE_EMAIL)
                .putExtra(Intent.EXTRA_EMAIL, new String[]{email})
                .putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.happy_birthday))
                .setData(Uri.parse(Constants.MAILTO + email)), null));
    }

    public static void call(Context context, String phoneNumber) {
        context.startActivity(Intent.createChooser(new Intent(Intent.ACTION_DIAL,
                Uri.parse(Constants.TEL + phoneNumber)), null));

    }

    public static void sms(Context context, String phoneNumber) {
        context.startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW)
                .setType(Constants.TYPE_SMS)
                .putExtra(Constants.ADDRESS, phoneNumber)
                .setData(Uri.parse(Constants.SMSTO + phoneNumber)), null));
    }

    public static boolean hasWhatsapp(Context context, Person person) {
        try {
            String[] projection = new String[] { ContactsContract.RawContacts._ID };
            String selection = ContactsContract.Data.CONTACT_ID + " = ? AND account_type IN (?)";
            String[] selectionArgs = new String[] { person.getContactId().toString(), "com.whatsapp" };
            Cursor cursor = context.getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI, projection, selection, selectionArgs, null);
            if (cursor != null) {
                boolean hasWhatsApp = cursor.moveToNext();
                cursor.close();
                return hasWhatsApp;
            }
        }
        catch (Exception ex) {
            //pass
        }

        return false;

    }

    public static String getWhatsappPhoneNumber(Context context, Person person) {
        String[] projection = new String[] { ContactsContract.Data.DATA3 };
        String selection = ContactsContract.Data.MIMETYPE + " LIKE ? AND " + ContactsContract.Data.RAW_CONTACT_ID + " = ? ";
        String[] selectionArgs = new String[] { "%whatsapp%", person.getContactId().toString() };
        Cursor cursor = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, projection, selection, selectionArgs, "1 LIMIT 1");
        try {
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    return cursor.getString(0);
                }
            }
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return person.getPhoneNumber();
    }

    public static void whatsapp(Context context, Person person) {
//        try {
            final String phone = PhoneNumberUtils.stripSeparators(getWhatsappPhoneNumber(context, person)).replace("+", "");
//            PackageManager packageManager = context.getPackageManager();
//            Intent i = new Intent(Intent.ACTION_VIEW);
//
//            String url = "https://api.whatsapp.com/send?phone="+ phone +"&text=" + URLEncoder.encode(context.getString(R.string.happy_birthday), "UTF-8");
//            i.setPackage("com.whatsapp");
//            i.setData(Uri.parse(url));
//            if (i.resolveActivity(packageManager) != null) {
//                context.startActivity(i);
//            }
//        } catch (Exception e){
//            Toast.makeText(context, "Could not open WhatsApp messenger: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }

        Intent intent = new Intent(Intent.ACTION_MAIN)
                //.setAction(Intent.ACTION_SEND)
                .setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"))
                .putExtra(Intent.EXTRA_TEXT, context.getText(R.string.happy_birthday))
                .putExtra("jid", phone + "@s.whatsapp.net");
                //.setType("text/plain");
                //.setPackage("com.whatsapp");

        context.startActivity(intent);
    }

    public static void genericContact(Context context, Person person) {
        context.startActivity(Intent.createChooser(new Intent(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_EMAIL, new String[] { person.getEmail() })
                .putExtra(Constants.ADDRESS, person.getPhoneNumber())
                .setData(Uri.parse(Constants.MAILTO + person.getEmail()))
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, context.getString(R.string.happy_birthday)), null));
    }
}

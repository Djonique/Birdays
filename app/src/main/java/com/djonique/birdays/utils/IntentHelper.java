package com.djonique.birdays.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.djonique.birdays.R;

public class IntentHelper {

    private final static String TYPE_EMAIL = "message/rfc822";
    private final static String MAILTO = "mailto:";
    private final static String TYPE_SMS = "vnd.android-dir/mms-sms";
    private final static String ADDRESS = "address";
    private final static String SMSTO = "smsto:";
    private final static String TEL = "tel: ";

    public static void call(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse(TEL + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void sendSms(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType(TYPE_SMS);
        intent.putExtra(ADDRESS, phoneNumber);
        intent.setData(Uri.parse(SMSTO + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void sendEmail(Context context, String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType(TYPE_EMAIL);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.happy_birthday));
        intent.setData(Uri.parse(MAILTO + email));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.send_email)));
    }
}

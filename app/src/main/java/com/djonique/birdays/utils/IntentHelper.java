package com.djonique.birdays.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.djonique.birdays.R;

public class IntentHelper {

    public static void call(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse(ConstantManager.TEL + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void sendSms(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType(ConstantManager.TYPE_SMS);
        intent.putExtra(ConstantManager.ADDRESS, phoneNumber);
        intent.setData(Uri.parse(ConstantManager.SMSTO + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void sendEmail(Context context, String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType(ConstantManager.TYPE_EMAIL);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.happy_birthday));
        intent.setData(Uri.parse(ConstantManager.MAILTO + email));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.send_email)));
    }
}

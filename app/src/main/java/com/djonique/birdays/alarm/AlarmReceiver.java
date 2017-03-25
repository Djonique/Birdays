package com.djonique.birdays.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import com.djonique.birdays.R;
import com.djonique.birdays.activities.MainActivity;
import com.djonique.birdays.utils.BirdaysApplication;
import com.djonique.birdays.utils.ConstantManager;

public class AlarmReceiver extends BroadcastReceiver {

    public static final String VIBRATION = "vibration";

    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean vibrate = preferences.getBoolean(VIBRATION, false);

        String string = intent.getStringExtra(ConstantManager.NAME);
        long timeStamp = intent.getLongExtra(ConstantManager.TIME_STAMP, 0);

        Intent resultIntent = new Intent(context, MainActivity.class);

        if (BirdaysApplication.isActivityVisible()) {
            resultIntent = intent;
        }

        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) timeStamp,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle(context.getString(R.string.app_name));
        builder.setContentText(string);
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(Notification.DEFAULT_SOUND);
        if (vibrate) {
            builder.setDefaults(Notification.DEFAULT_ALL);
        }
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager =
                ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE));
        notificationManager.notify((int) timeStamp, notification);
    }
}

package com.djonique.birdays.alarm;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.djonique.birdays.utils.MyApplication;
import com.djonique.birdays.R;
import com.djonique.birdays.activities.MainActivity;
import com.djonique.birdays.utils.Utils;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String string = intent.getStringExtra("name");
        long date = intent.getLongExtra("date", 0);
        long timeStamp = intent.getLongExtra("time_stamp", 0);

        Intent resultIntent = new Intent(context, MainActivity.class);

        if (MyApplication.isActivityVisible()) {
            resultIntent = intent;
        }

        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) timeStamp, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        if (Utils.isCurrentDay(date)) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setContentTitle("Birdays");
            builder.setContentText(string);
            builder.setSmallIcon(R.drawable.ic_add_white_24dp);
            builder.setDefaults(Notification.DEFAULT_ALL);
            builder.setContentIntent(pendingIntent);

            Notification notification = builder.build();
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            NotificationManager notificationManager =
                    ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE));
            notificationManager.notify((int) timeStamp, notification);
        }
    }
}

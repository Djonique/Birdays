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

package com.djonique.birdays.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.FileUriExposedException;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.djonique.birdays.R;
import com.djonique.birdays.activities.DetailActivity;
import com.djonique.birdays.utils.BirdaysApplication;
import com.djonique.birdays.utils.Constants;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String ringtone = preferences.getString(Constants.RINGTONE_KEY,
                Settings.System.DEFAULT_NOTIFICATION_URI.toString());
        Uri ringtoneUri = Uri.parse(ringtone);

        String name = intent.getStringExtra(Constants.NAME);
        long timeStamp = intent.getLongExtra(Constants.TIME_STAMP, 0);

        Intent resultIntent = new Intent(context, DetailActivity.class);
        resultIntent.putExtra(Constants.TIME_STAMP, timeStamp);

        if (BirdaysApplication.isActivityVisible()) {
            resultIntent = intent;
        }

        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = TaskStackBuilder.create(context)
                .addNextIntentWithParentStack(resultIntent)
                .getPendingIntent(((int) timeStamp), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, name);
        builder.setContentTitle(context.getString(R.string.app_name));
        builder.setContentText(name);
        builder.setSmallIcon(R.drawable.ic_notification);
        try {
            builder.setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);
            builder.setSound(ringtoneUri);
        } catch (FileUriExposedException e) {
            builder.setDefaults(Notification.DEFAULT_ALL);
        }
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager =
                ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE));
        if (notificationManager != null) {
            notificationManager.notify((int) timeStamp, notification);
        }
    }
}
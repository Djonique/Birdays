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

package com.eblis.whenwasit.widget;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.activities.DetailActivity;
import com.eblis.whenwasit.utils.Constants;
import com.eblis.whenwasit.utils.Utils;

import org.joda.time.LocalDate;

import java.util.Calendar;

public class WidgetProvider extends AppWidgetProvider {

    private static final String ACTION_ON_CLICK = "ACTION_ON_CLICK";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int i : appWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            Intent serviceIntent = new Intent(context, WidgetService.class);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
            remoteViews.setRemoteAdapter(R.id.listview_widget, serviceIntent);

            remoteViews.setTextViewText(R.id.textview_widget_header_date,
                    Utils.getDateWithoutYear(new LocalDate()));

            Intent clickIntent = new Intent(context, WidgetProvider.class);
            clickIntent.setAction(ACTION_ON_CLICK);

            PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context, 0,
                    clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setPendingIntentTemplate(R.id.listview_widget, clickPendingIntent);

            appWidgetManager.updateAppWidget(i, remoteViews);
            appWidgetManager.notifyAppWidgetViewDataChanged(i, R.id.listview_widget);
        }
    }

    // Opens DetailActivity
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction() != null && intent.getAction().equals(ACTION_ON_CLICK)) {
            long timeStamp = intent.getLongExtra(Constants.TIME_STAMP, 0);
            Intent resultIntent = new Intent(context, DetailActivity.class);
            resultIntent.putExtra(Constants.TIME_STAMP, timeStamp);
            resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            TaskStackBuilder.create(context).addNextIntentWithParentStack(resultIntent).startActivities();
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }
}
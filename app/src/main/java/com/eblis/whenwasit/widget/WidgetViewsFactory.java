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

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.database.DbHelper;
import com.eblis.whenwasit.models.DisplayedAge;
import com.eblis.whenwasit.models.Person;
import com.eblis.whenwasit.utils.Constants;
import com.eblis.whenwasit.utils.Utils;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class WidgetViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private DbHelper dbHelper;
    private List<Person> widgetList;

    WidgetViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        dbHelper = new DbHelper(context);
        widgetList = new ArrayList<>();
    }

    @Override
    public void onDataSetChanged() {
        widgetList.clear();

        List<Person> persons = dbHelper.query().getPersons();
        Collections.sort(persons);

        final LocalDate now = new LocalDate();
        int position = 0;

        // Finds the position of the Person with closest date
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getMonth() == now.getMonthOfYear() && person.getDay() >= now.getDayOfMonth() || person.getMonth() > now.getMonthOfYear()) {
                position = i;
                break;
            }
        }

        // Shifts list
        for (int i = position; i < persons.size(); i++) {
            widgetList.add(persons.get(i));
        }
        for (int i = 0; i < position; i++) {
            widgetList.add(persons.get(i));
        }
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return widgetList.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews view = new RemoteViews(context.getPackageName(),
                R.layout.description_widget_list_view);

        final Person person = widgetList.get(i);
        final String name = person.getName();
        final LocalDate date = person.getDate();
        final boolean yearUnknown = person.isYearUnknown();

        // Age column
        if (!yearUnknown) {
            DisplayedAge displayedAge = Utils.getDisplayedAge(PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(Constants.DISPLAYED_AGE_KEY, DisplayedAge.CURRENT.name()));
            int age = Utils.getAge(date, displayedAge);
            view.setTextViewText(R.id.textview_widget_age, String.valueOf(age));
        } else {
            view.setTextViewText(R.id.textview_widget_age, "");
        }

        // Name column
        view.setTextViewText(R.id.textview_widget_name, name);

        // Date column
        String today = context.getString(R.string.today);
        if (Utils.daysLeftPretty(context, person).equals(today)) {
            view.setTextViewText(R.id.textview_widget_date, today);
            setTextColor(view, ContextCompat.getColor(context, R.color.red_alert));
        } else {
            view.setTextViewText(R.id.textview_widget_date, Utils.getDateWithoutYear(date));
            setTextColor(view, ContextCompat.getColor(context, android.R.color.white));
        }

        // OnClick handling
        Intent clickIntent = new Intent();
        clickIntent.putExtra(Constants.RECORD_ID, widgetList.get(i).getId());
        view.setOnClickFillInIntent(R.id.relativelayout_widget, clickIntent);

        return view;
    }

    private void setTextColor(RemoteViews views, int color) {
        views.setTextColor(R.id.textview_widget_age, color);
        views.setTextColor(R.id.textview_widget_name, color);
        views.setTextColor(R.id.textview_widget_date, color);
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
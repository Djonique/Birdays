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

package com.djonique.birdays.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.djonique.birdays.R;
import com.djonique.birdays.fragments.AllFragment;
import com.djonique.birdays.fragments.FamousFragment;
import com.djonique.birdays.fragments.MonthFragment;
import com.djonique.birdays.models.Person;

import java.util.Calendar;

public class PagerAdapter extends FragmentPagerAdapter {

    public static final int FAMOUS_FRAGMENT_POSITION = 2;

    private Context context;
    private MonthFragment monthFragment;
    private AllFragment allFragment;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MonthFragment();
            case 1:
                return new AllFragment();
            case 2:
                return new FamousFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return getMonth();
            case 1:
                return context.getString(R.string.all);
            case 2:
                return context.getString(R.string.famous);
        }
        return null;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        switch (position) {
            case 0:
                monthFragment = ((MonthFragment) createdFragment);
                break;
            case 1:
                allFragment = ((AllFragment) createdFragment);
                break;
        }
        return createdFragment;
    }

    private String getMonth() {
        String[] months = context.getResources().getStringArray(R.array.months);
        return months[Calendar.getInstance().get(Calendar.MONTH)];
    }

    public void search(String text) {
        if (allFragment != null) {
            allFragment.findPerson(text);
        }
        if (monthFragment != null) {
            monthFragment.findPerson(text);
        }
    }

    public void addPersonsFromDb() {
        if (monthFragment != null) {
            monthFragment.addMonthPersonsFromDb();
        }
        if (allFragment != null) {
            allFragment.refreshAllPersonsFromDb();
        }
    }

    public void addPerson(Person person) {
        if (monthFragment != null) {
            monthFragment.addPerson(person);
        }
        if (allFragment != null) {
            allFragment.addPerson(person, true);
        }
    }

    public void deletePerson(long timeStamp) {
        if (monthFragment != null) {
            monthFragment.deleteRecord(timeStamp);
        }
    }
}
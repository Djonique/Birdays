package com.djonique.birdays.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.djonique.birdays.R;
import com.djonique.birdays.fragments.AllFragment;
import com.djonique.birdays.fragments.FamousFragment;
import com.djonique.birdays.fragments.MonthFragment;
import com.djonique.birdays.model.Person;

import java.util.Calendar;

public class PagerAdapter extends FragmentPagerAdapter {

    public static final int FAMOUS_FRAGMENT_POSITION = 2;

    private MonthFragment monthFragment;
    private AllFragment allFragment;
    private Context context;

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
        Calendar today = Calendar.getInstance();
        return months[today.get(Calendar.MONTH)];
    }

    public void search(String text) {
        if (allFragment != null) {
            allFragment.findPerson(text);
        }
        if (monthFragment != null) {
            monthFragment.findPerson(text);
        }
    }

    public void addRecordsFromDB() {
        if (monthFragment != null) {
            monthFragment.addPersonFromDB();
        }
        if (allFragment != null) {
            allFragment.addPersonFromDB();
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

    public void startDialog(int position) {
        if (allFragment != null) {
            allFragment.removePersonDialog(position);
        }
    }

    public void deleteRecord(long timeStamp) {
        if (monthFragment != null) {
            monthFragment.deleteRecord(timeStamp);
        }
    }
}

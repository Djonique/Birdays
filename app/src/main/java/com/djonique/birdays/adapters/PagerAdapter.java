package com.djonique.birdays.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.djonique.birdays.R;
import com.djonique.birdays.fragments.AllFragment;
import com.djonique.birdays.fragments.FamousFragment;
import com.djonique.birdays.fragments.MonthFragment;

import java.util.Calendar;

public class PagerAdapter extends FragmentPagerAdapter {

    public static final int MONTH_FRAGMENT_POSITION = 0;
    public static final int ALL_FRAGMENT_POSITION = 1;
    public static final int FAMOUS_FRAGMENT_POSITION = 2;
    private MonthFragment monthFragment;
    private AllFragment allFragment;
    private FamousFragment famousFragment;
    private Context context;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        monthFragment = new MonthFragment();
        allFragment = new AllFragment();
        famousFragment = new FamousFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return monthFragment;
            case 1:
                return allFragment;
            case 2:
                return famousFragment;
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

    private String getMonth() {
        String[] months = context.getResources().getStringArray(R.array.months);
        Calendar today = Calendar.getInstance();
        return months[today.get(Calendar.MONTH)];
    }
}

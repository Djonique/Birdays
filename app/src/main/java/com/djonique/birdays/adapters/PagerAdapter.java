package com.djonique.birdays.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.djonique.birdays.R;
import com.djonique.birdays.fragments.AllFragment;
import com.djonique.birdays.fragments.FavoritesFragment;
import com.djonique.birdays.fragments.MonthFragment;

import java.util.Calendar;

public class PagerAdapter extends FragmentPagerAdapter {

    public static final int MONTH_FRAGMENT_POSITION = 0;
    public static final int ALL_FRAGMENT_POSITION = 1;
    public static final int FAVORITES_FRAGMENT_POSITION = 2;
    private Context context;
    private MonthFragment mf;
    private AllFragment af;
    private FavoritesFragment ff;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        mf = new MonthFragment();
        af = new AllFragment();
        ff = new FavoritesFragment();

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mf;
            case 1:
                return af;
            case 2:
                return ff;
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
                return context.getString(R.string.favorites);
        }
        return null;
    }

    public String getMonth() {

        String month = context.getString(R.string.month);
        Calendar today = Calendar.getInstance();

        switch (today.get(Calendar.MONTH)) {
            case 0:
                month = context.getString(R.string.january);
                break;
            case 1:
                month = context.getString(R.string.february);
                break;
            case 2:
                month = context.getString(R.string.march);
                break;
            case 3:
                month = context.getString(R.string.april);
                break;
            case 4:
                month = context.getString(R.string.may);
                break;
            case 5:
                month = context.getString(R.string.june);
                break;
            case 6:
                month = context.getString(R.string.july);
                break;
            case 7:
                month = context.getString(R.string.august);
                break;
            case 8:
                month = context.getString(R.string.september);
                break;
            case 9:
                month = context.getString(R.string.october);
                break;
            case 10:
                month = context.getString(R.string.november);
                break;
            case 11:
                month = context.getString(R.string.december);
                break;
            default:
                return month;
        }
        return month;
    }
}

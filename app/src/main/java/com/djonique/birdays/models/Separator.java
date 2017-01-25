package com.djonique.birdays.models;


import com.djonique.birdays.R;

public class Separator implements Item{
    public static final int TYPE_JANUARY = R.string.january;
    public static final int TYPE_FEBRUARY = R.string.february;
    public static final int TYPE_MARCH = R.string.march;
    public static final int TYPE_APRIL = R.string.april;
    public static final int TYPE_MAY = R.string.may;
    public static final int TYPE_JUNE = R.string.june;
    public static final int TYPE_JULY = R.string.july;
    public static final int TYPE_AUGUST = R.string.august;
    public static final int TYPE_SEPTEMBER = R.string.september;
    public static final int TYPE_OCTOBER = R.string.october;
    public static final int TYPE_NOVEMBER = R.string.november;
    public static final int TYPE_DECEMBER = R.string.december;

    private int type;

    public Separator(int type) {
        this.type = type;
    }

    public boolean isPerson() {
        return false;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}


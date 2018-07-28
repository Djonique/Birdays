package com.eblis.whenwasit.utils;

import android.support.annotation.VisibleForTesting;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static junit.framework.Assert.assertEquals;

public class UtilsTest {

    private Random random = new Random();

    private LocalDate getDate(int year, int month, int day) {
        return new LocalDate(year, month, day);
    }

    @Test
    public void fewDaysUntilBirthdayWorks() {
        LocalDate birthday = getDate(2018, 3, 4);
        assertEquals(1, Utils.daysUntilNextBirthday(birthday, getDate(2018, 3, 3)));
        assertEquals(2, Utils.daysUntilNextBirthday(birthday, getDate(2018, 3, 2)));
        assertEquals(28, Utils.daysUntilNextBirthday(birthday, getDate(2018, 2, 4)));
    }

    @Test
    public void birthdayInPastReturnsNaturalNumber() {
        LocalDate birthday = getDate(2018, 1, 1);
        assertEquals(364, Utils.daysUntilNextBirthday(birthday, getDate(2018, 1, 2)));
        assertEquals(365, Utils.daysUntilNextBirthday(birthday, getDate(2020,1,2)));
        assertEquals(364, Utils.daysUntilNextBirthday(birthday, getDate(2021,1,2)));
    }

    @Test
    public void leapYears() {
        LocalDate birthday = getDate(2018, 4, 1);
        assertEquals(353, Utils.daysUntilNextBirthday(birthday, getDate(2019, 4, 14)));
        assertEquals(352, Utils.daysUntilNextBirthday(birthday, getDate(2020, 4, 14)));

        birthday = getDate(2016, 2, 29);
        assertEquals(0, Utils.daysUntilNextBirthday(birthday, getDate(2020, 2, 29)));
        assertEquals(0, Utils.daysUntilNextBirthday(birthday, getDate(2017, 2, 28)));
    }
}
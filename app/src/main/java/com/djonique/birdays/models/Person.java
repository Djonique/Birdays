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

package com.djonique.birdays.models;

import java.util.Calendar;
import java.util.Date;

public class Person implements Item {

    private String name, phoneNumber, email;
    private long date, timeStamp;
    private boolean yearUnknown;

    /**
     * Default constructor
     */
    public Person() {
        this.timeStamp = new Date().getTime();
    }

    /**
     * Constructor for database with famous persons
     */
    public Person(String name, long date) {
        this.name = name;
        this.date = date;
    }

    /**
     * Constructor for importing from Contacts
     */
    public Person(String name, long date, boolean yearUnknown, String phoneNumber, String email) {
        this.name = name;
        this.date = date;
        this.yearUnknown = yearUnknown;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.timeStamp = new Date().getTime();
    }

    /**
     * Constructor for DbQueryManager
     */
    public Person(String name, long date, boolean yearUnknown, String phoneNumber, String email,
                  long timeStamp) {
        this.name = name;
        this.date = date;
        this.yearUnknown = yearUnknown;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isYearUnknown() {
        return yearUnknown;
    }

    public void setYearUnknown(boolean yearUnknown) {
        this.yearUnknown = yearUnknown;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public boolean isPerson() {
        return true;
    }

    public int getMonth(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.MONTH);
    }

    public int getDay(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Person.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        Person person = (Person) obj;
        return this.name.equalsIgnoreCase(person.name);
    }
}
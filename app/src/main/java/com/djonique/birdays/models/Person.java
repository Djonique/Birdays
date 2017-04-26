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

    private String name;
    private long date;
    private boolean unknownYear;
    private String phoneNumber;
    private String email;
    private long timeStamp;
    private String lowerCaseName;

    public Person() {
        this.timeStamp = new Date().getTime();
    }

    public Person(String name, long date) {
        this.name = name;
        this.date = date;
    }

    public Person(String name,
                  long date,
                  boolean unknownYear,
                  String phoneNumber,
                  String email,
                  long timeStamp,
                  String lowerCaseName) {
        this.name = name;
        this.date = date;
        this.unknownYear = unknownYear;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.timeStamp = timeStamp;
        this.lowerCaseName = lowerCaseName;
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
        return unknownYear;
    }

    public void setYearUnknown(boolean unknownYear) {
        this.unknownYear = unknownYear;
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

    public String getLowerCaseName() {
        return lowerCaseName;
    }

    public void setLowerCaseName(String lowerCaseName) {
        this.lowerCaseName = lowerCaseName;
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
}



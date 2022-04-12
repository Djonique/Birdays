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

package com.eblis.whenwasit.models;

import android.support.annotation.NonNull;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDate;

import java.util.Calendar;
import java.util.Date;

public class Person implements Item, Comparable<Person> {
    public static final int DEFAULT_YEAR = 2000;

    private Long id;
    private Long contactId;
    private String name, phoneNumber, email;
    private long timeStamp;
    private LocalDate date;
    private boolean yearUnknown;
    private String anniversaryLabel;
    private String contactCategory;
    private AnniversaryType anniversaryType;

    public Person() {
        super();
    }

    /**
     * Constructor for database with famous persons
     */
    public Person(String name, long date) {
        this(name, new LocalDate(date, DateTimeZone.UTC));
    }

    public Person(String name, LocalDate date) {
        setName(name);
        setDate(date);
    }

    /**
     * Constructor for importing from Contacts
     */
    public Person(long id, Long contactId, String name, long date, boolean yearUnknown, String phoneNumber, String email, String anniversaryLabel, AnniversaryType anniversaryType, String contactCategory) {
        this(id, contactId, name, date, yearUnknown, phoneNumber, email, anniversaryLabel, anniversaryType, contactCategory, new Date().getTime());
    }

    /**
     * Constructor for DbQueryManager
     */
    public Person(long id, Long contactId, String name, long date, boolean yearUnknown, String phoneNumber, String email, String anniversaryLabel, AnniversaryType anniversaryType, String contactCategory, long timeStamp) {
        this(name, date);
        this.id = id;
        this.contactId = contactId;
        this.yearUnknown = yearUnknown;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.anniversaryLabel = anniversaryLabel;
        this.anniversaryType = anniversaryType;
        this.contactCategory = contactCategory;
        this.setTimeStamp(timeStamp);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isYearUnknown() {
        return yearUnknown;
    }

    public void setYearUnknown(boolean yearUnknown) {
        this.yearUnknown = yearUnknown;
    }

    @Override
    public int getMonth() {
        return date.getMonthOfYear();
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public String getAnniversaryLabel() {
        return anniversaryLabel;
    }

    public void setAnniversaryLabel(String anniversaryLabel) {
        this.anniversaryLabel = anniversaryLabel;
    }

    public String getContactCategory() {
        return contactCategory;
    }

    public void setContactCategory(String contactCategory) {
        this.contactCategory = contactCategory;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", anniversaryLabel='" + anniversaryLabel + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public ItemType getItemType() {
        return ItemType.PERSON;
    }

    @Override
    public AnniversaryType getAnniversaryType() {
        return anniversaryType;
    }

    public void setAnniversaryType(AnniversaryType anniversaryType) {
        this.anniversaryType = anniversaryType;
    }

    @Override
    public boolean isSeparator() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Person.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        Person that = (Person) obj;
        return this.getName().equalsIgnoreCase(that.getName()) &&
                this.getAnniversaryLabel().equalsIgnoreCase(that.getAnniversaryLabel()) &&
                this.getDate().equals(that.getDate());
    }

    @Override
    public int compareTo(@NonNull Person person) {
        //we're only interested in the day/month, assume both persons have same year
        int compare = this.date.withYear(DEFAULT_YEAR).compareTo(person.date.withYear(DEFAULT_YEAR));
        if (compare != 0) {
            return compare; 
        }
        return this.getName().compareTo(person.getName());
    }
}
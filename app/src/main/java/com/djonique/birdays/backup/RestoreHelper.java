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

package com.djonique.birdays.backup;

import android.content.Context;
import android.widget.Toast;

import com.djonique.birdays.models.Person;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.djonique.birdays.backup.ExportHelper.DATE;
import static com.djonique.birdays.backup.ExportHelper.EMAIL;
import static com.djonique.birdays.backup.ExportHelper.IO_EXCEPTION;
import static com.djonique.birdays.backup.ExportHelper.NAME;
import static com.djonique.birdays.backup.ExportHelper.PERSON;
import static com.djonique.birdays.backup.ExportHelper.PHONE_NUMBER;
import static com.djonique.birdays.backup.ExportHelper.UNKNOWN_YEAR;

public class RestoreHelper {

    private static final String XML_PULL_PARSER_EXCEPTION = "XmlPullParserException";
    private static final String FILE_NOT_FOUND_EXCEPTION = "FileNotFoundException";

    private Context mContext;

    public RestoreHelper(Context context) {
        mContext = context;
    }

    public void restoreRecords(String path) {
        XmlPullParserFactory pullParserFactory;
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            pullParserFactory.setNamespaceAware(true);
            XmlPullParser parser = pullParserFactory.newPullParser();
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            parser.setInput(new InputStreamReader(fis));
            parseXml(parser);
        } catch (XmlPullParserException e) {
            Toast.makeText(mContext, XML_PULL_PARSER_EXCEPTION, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(mContext, FILE_NOT_FOUND_EXCEPTION, Toast.LENGTH_LONG).show();
        }
    }

    private void parseXml(XmlPullParser parser) {
        List<Person> persons = null;
        Person person = null;

        try {
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name;
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        persons = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if (name.equals(PERSON)) {
                            person = new Person();
                        } else if (person != null) {
                            switch (name) {
                                case NAME:
                                    person.setName(parser.nextText());
                                    break;
                                case DATE:
                                    person.setDate(Long.valueOf(parser.nextText()));
                                    break;
                                case UNKNOWN_YEAR:
                                    person.setYearUnknown(Boolean.valueOf(parser.nextText()));
                                    break;
                                case PHONE_NUMBER:
                                    person.setPhoneNumber(parser.nextText());
                                    break;
                                case EMAIL:
                                    person.setEmail(parser.nextText());
                                    break;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (name.equals(PERSON) && persons != null && person != null) {
                            persons.add(person);
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            Toast.makeText(mContext, XML_PULL_PARSER_EXCEPTION, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(mContext, IO_EXCEPTION, Toast.LENGTH_LONG).show();
        }
    }
}
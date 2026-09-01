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

package com.eblis.whenwasit.backup;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.widget.Toast;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.alarm.AlarmHelper;
import com.eblis.whenwasit.database.DbHelper;
import com.eblis.whenwasit.models.AnniversaryType;
import com.eblis.whenwasit.models.Person;
import com.eblis.whenwasit.utils.Utils;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;

public class RecoverHelper {

    // XML constants
    private static final String PERSON = "person";
    private static final String NAME = "name";
    private static final String DATE = "date";
    private static final String YEAR_UNKNOWN = "year_unknown";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String EMAIL = "email";
    private static final String CATEGORY = "category";
    private static final String ANNIVERSARY_LABEL = "anniversary_label";
    private static final String ANNIVERSARY_TYPE = "anniversary_type";
    private static final String TIMESTAMP = "timestamp";

    // Exceptions constants
    private static final String XML_PULL_PARSER_EXCEPTION = "XmlPullParserException";
    private static final String FILE_NOT_FOUND_EXCEPTION = "FileNotFoundException";
    private static final String IO_EXCEPTION = "IOException";

    // Path constants
    private static final String PRIMARY = "primary";
    private static final String CONTENT_DOWNLOADS = "content://downloads/public_downloads";
    private static final String AUTHORITY_EXTERNAL_STORAGE = "com.android.externalstorage.documents";
    private static final String AUTHORITY_DOWNLOADS = "com.android.providers.downloads.documents";
    private static final String COLUMN_DATA = "_data";

    private Context context;

    public RecoverHelper(Context context) {
        this.context = context;
    }

    public void recoverRecords(Context context, Uri uri) {
        String path = getPath(context, uri);
        if (path == null) path = uri.getPath();
        XmlPullParserFactory pullParserFactory;
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            pullParserFactory.setNamespaceAware(true);
            XmlPullParser parser = pullParserFactory.newPullParser();
            File file = new File("storage/emulated/0/Download").listFiles()[0];
            FileInputStream inputStream = new FileInputStream(file);
            parser.setInput(new InputStreamReader(inputStream));
            parseXml(parser);
            Utils.notifyWidget(context);
            Toast.makeText(context, R.string.records_recovered, Toast.LENGTH_LONG).show();
        } catch (XmlPullParserException e) {
            Toast.makeText(context, XML_PULL_PARSER_EXCEPTION, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(context, FILE_NOT_FOUND_EXCEPTION, Toast.LENGTH_LONG).show();
        }
    }

    private void parseXml(XmlPullParser parser) {
        DbHelper dbHelper = new DbHelper(context);
        List<Person> dbPersons = dbHelper.query().getPersons();
        Person person = null;
        int index = 1;

        try {
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name;
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
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
                                    person.setDate(new LocalDate(parser.nextText(), DateTimeZone.UTC));
                                    break;
                                case YEAR_UNKNOWN:
                                    person.setYearUnknown(Boolean.valueOf(parser.nextText()));
                                    break;
                                case PHONE_NUMBER:
                                    person.setPhoneNumber(parser.nextText());
                                    break;
                                case EMAIL:
                                    person.setEmail(parser.nextText());
                                    break;
                                case CATEGORY:
                                    person.setContactCategory(parser.nextText());
                                    break;
                                case ANNIVERSARY_LABEL:
                                    person.setAnniversaryLabel(parser.nextText());
                                    break;
                                case ANNIVERSARY_TYPE:
                                    person.setAnniversaryType(AnniversaryType.valueOf(parser.nextText()));
                                    break;
                                case TIMESTAMP:
                                    person.setTimeStamp(Long.parseLong(parser.nextText()));
                                    break;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (person != null && person.getTimeStamp() == 0) {
                            // we don't have a timestamp, parse one now from date
                            person.setTimeStamp(person.getDate().toDate().getTime() / 1000 + index++);
                        }
                        if (name.equals(PERSON) && person != null && person.getDate() != null) {
                            if (Utils.getPersonAlreadyInDb(person, dbPersons) == null) {
                                dbHelper.addRecord(person);
                                dbPersons.add(person);
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            Toast.makeText(context, XML_PULL_PARSER_EXCEPTION, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context, IO_EXCEPTION, Toast.LENGTH_LONG).show();
        }
    }

    private String getPath(Context context, Uri uri) {
        if (isExternalStorageDocument(uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            String[] split = docId.split(":");
            String type = split[0];

            if (PRIMARY.equalsIgnoreCase(type)) {
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            }
        } else if (isDownloadsDocument(uri)) {
            String id = DocumentsContract.getDocumentId(uri);
            Uri contentUri = Uri.parse(id.split(":")[1]);
            return getDataColumn(context, contentUri);
        }
        return null;
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return uri.getAuthority().equals(AUTHORITY_EXTERNAL_STORAGE);
    }

    private boolean isDownloadsDocument(Uri uri) {
        return uri.getAuthority().equals(AUTHORITY_DOWNLOADS);
    }

    private String getDataColumn(Context context, Uri uri) {
        Cursor cursor = null;
        String column = COLUMN_DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
}
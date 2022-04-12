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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Xml;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.database.DbHelper;
import com.eblis.whenwasit.models.Person;
import com.eblis.whenwasit.utils.ProgressDialogHelper;
import com.eblis.whenwasit.utils.Utils;

import org.joda.time.LocalDate;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;

public class ExportHelper {

    // XML constants
    private static final String RECORDS = "records";
    private static final String PERSON = "person";
    private static final String NAME = "name";
    private static final String DATE = "date";
    private static final String YEAR_UNKNOWN = "year_unknown";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String EMAIL = "email";
    private static final String BACKUP = "backup";
    private static final String UTF_8 = "UTF-8";
    private static final String CATEGORY = "category";
    private static final String ANNIVERSARY_LABEL = "anniversary_label";
    private static final String ANNIVERSARY_TYPE = "anniversary_type";
    private static final String TIMESTMAP = "timestamp";

    // Exceptions constants
    private static final String ILLEGAL_ARGUMENT_EXCEPTION = "IllegalArgumentException";
    private static final String ILLEGAL_STATE_EXCEPTION = "IllegalStateException";
    private static final String IO_EXCEPTION = "IOException";
    private static final String FILE_NOT_FOUND_EXCEPTION = "FileNotFoundException";

    private Activity activity;
    private File folder;
    private boolean storageAvailable = true;

    public ExportHelper(Activity activity) {
        this.activity = activity;
    }

    public void exportRecords() {
        new ExportAsyncTask().execute();
    }

    private boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    private String getBackupFileName() {
        LocalDate now = new LocalDate();
        return BACKUP + "_" + Utils.getDate(now) + "_" + String.valueOf(now) + ".xml";
    }

    private void writeTag(String tag, String value, XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(null, tag);
        xmlSerializer.text(value);
        xmlSerializer.endTag(null, tag);
    }

    private String writeXml(List<Person> persons) {
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter stringWriter = new StringWriter();
        try {
            xmlSerializer.setOutput(stringWriter);
            xmlSerializer.startDocument(UTF_8, true);
            xmlSerializer.startTag(null, RECORDS);
            for (Person person : persons) {
                xmlSerializer.startTag(null, PERSON);
                // name
                writeTag(NAME, person.getName(), xmlSerializer);
                // date
                writeTag(DATE, String.valueOf(person.getDate()), xmlSerializer);
                // year unknown
                writeTag(YEAR_UNKNOWN, String.valueOf(person.isYearUnknown()), xmlSerializer);
                // phone number
                writeTag(PHONE_NUMBER, person.getPhoneNumber() == null ? "" : person.getPhoneNumber(), xmlSerializer);
                // email
                writeTag(EMAIL, person.getEmail() == null ? "" : person.getEmail(), xmlSerializer);
                //category
                writeTag(CATEGORY, person.getContactCategory(), xmlSerializer);
                //label
                writeTag(ANNIVERSARY_LABEL, person.getAnniversaryLabel(), xmlSerializer);
                //type
                writeTag(ANNIVERSARY_TYPE, person.getAnniversaryType().toString(), xmlSerializer);
                //timestamp
                writeTag(TIMESTMAP, Long.toString(person.getTimeStamp()), xmlSerializer);
                xmlSerializer.endTag(null, PERSON);
            }
            xmlSerializer.endTag(null, RECORDS);
            xmlSerializer.endDocument();
            xmlSerializer.flush();
        } catch (IllegalArgumentException e) {
            showAlertDialog(activity, ILLEGAL_ARGUMENT_EXCEPTION);
        } catch (IllegalStateException e) {
            showAlertDialog(activity, ILLEGAL_STATE_EXCEPTION);
        } catch (IOException e) {
            showAlertDialog(activity, IO_EXCEPTION);
        }
        return stringWriter.toString();
    }

    private void showAlertDialog(final Activity activity, final String text) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage(text);
                builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class ExportAsyncTask extends AsyncTask<Void, Void, Void> {

        ProgressDialogHelper progressDialogHelper = new ProgressDialogHelper(activity);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialogHelper.startProgressDialog(activity.getString(R.string.exporting_records));
        }

        @SuppressWarnings("ResultOfMethodCallIgnored")
        @Override
        protected Void doInBackground(Void... params) {
            File sd = Environment.getExternalStorageDirectory();
            if (isExternalStorageWritable()) {
                try {
                    folder = new File(sd.getPath() + File.separator + activity.getString(R.string.app_name));
                    if (!folder.exists()) {
                        folder.mkdir();
                    }
                    File backupFile = new File(folder + File.separator + getBackupFileName());
                    backupFile.createNewFile();
                    FileOutputStream outputStream = new FileOutputStream(backupFile);
                    List<Person> persons = new DbHelper(activity).query().getPersons();
                    outputStream.write(writeXml(persons).getBytes());
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    showAlertDialog(activity, FILE_NOT_FOUND_EXCEPTION);
                } catch (IOException e) {
                    showAlertDialog(activity, IO_EXCEPTION);
                }
            } else {
                storageAvailable = false;
                showAlertDialog(activity, activity.getString(R.string.ext_storage_error));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialogHelper.dismissProgressDialog();
            if (storageAvailable) {
                showAlertDialog(activity, activity.getString(R.string.backup_finished) + folder);
            }
        }
    }
}
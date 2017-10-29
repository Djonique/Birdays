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

package com.djonique.birdays.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djonique.birdays.R;
import com.djonique.birdays.activities.MainActivity;
import com.djonique.birdays.adapters.AllFragmentAdapter;
import com.djonique.birdays.alarm.AlarmHelper;
import com.djonique.birdays.database.DbHelper;
import com.djonique.birdays.models.Item;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.models.Separator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AllFragment extends Fragment {

    private MainActivity activity;
    private AllFragmentAdapter adapter;
    private DeletingRecordListener deletingRecordListener;
    private AlarmHelper alarmHelper;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            deletingRecordListener = (MainActivity) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            activity = (MainActivity) getActivity();
            addAllPersonsFromDb();
        }
        alarmHelper = new AlarmHelper(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new AllFragmentAdapter(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void addPerson(Person newPerson, boolean saveToDb) {
        int position = -1;
        Separator separator = null;

        for (int i = 0; i < adapter.getItemCount(); i++) {
            if (adapter.getItem(i).isPerson()) {
                Person person = ((Person) adapter.getItem(i));

                if (newPerson.getMonth(newPerson.getDate()) < person.getMonth(person.getDate())) {
                    position = i;
                    break;
                } else if (newPerson.getMonth(newPerson.getDate()) == person.getMonth(person.getDate())) {
                    if (newPerson.getDay(newPerson.getDate()) <= person.getDay(person.getDate())) {
                        // If date before
                        position = i;
                        break;
                    } else { // If date after
                        if (adapter.getItemCount() > (i + 1) && adapter.getItem(i + 1).isPerson()) { // If after person, not adapter
                            Person person1 = ((Person) adapter.getItem(i + 1));
                            if (newPerson.getDay(newPerson.getDate()) <= person1.getDay(person1.getDate())) {
                                // If date before, else if date after - continue
                                position = i + 1;
                                break;
                            }
                        } else { // If after adapter, not person
                            position = i + 1;
                            break;
                        }
                    }
                }
            }
        }

        if (newPerson.getDate() != 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(newPerson.getDate());

            separator = getSeparator(newPerson);
        }

        if (position != -1) {
            if (separator != null) {
                adapter.addItem(position - 1, separator);
            }
            adapter.addItem(position, newPerson);
        } else {
            if (separator != null) {
                adapter.addItem(separator);
            }
            adapter.addItem(newPerson);
        }

        if (saveToDb) {
            activity.dbHelper.addRecord(newPerson);
        }
    }

    public void addAllPersonsFromDb() {
        adapter.removeAllPersons();
        List<Person> persons = new ArrayList<>();
        persons.addAll(activity.dbHelper.query().getPersons());

        for (int i = 0; i < persons.size(); i++) {
            addPerson(persons.get(i), false);
        }
    }

    private Separator getSeparator(Person person) {
        Separator separator = null;
        switch (person.getMonth(person.getDate())) {
            case Calendar.JANUARY:
                if (!adapter.containsSeparatorJanuary) {
                    adapter.containsSeparatorJanuary = true;
                    separator = new Separator(Separator.TYPE_JANUARY);
                }
                break;
            case Calendar.FEBRUARY:
                if (!adapter.containsSeparatorFebruary) {
                    adapter.containsSeparatorFebruary = true;
                    separator = new Separator(Separator.TYPE_FEBRUARY);
                }
                break;
            case Calendar.MARCH:
                if (!adapter.containsSeparatorMarch) {
                    adapter.containsSeparatorMarch = true;
                    separator = new Separator(Separator.TYPE_MARCH);
                }
                break;
            case Calendar.APRIL:
                if (!adapter.containsSeparatorApril) {
                    adapter.containsSeparatorApril = true;
                    separator = new Separator(Separator.TYPE_APRIL);
                }
                break;
            case Calendar.MAY:
                if (!adapter.containsSeparatorMay) {
                    adapter.containsSeparatorMay = true;
                    separator = new Separator(Separator.TYPE_MAY);
                }
                break;
            case Calendar.JUNE:
                if (!adapter.containsSeparatorJune) {
                    adapter.containsSeparatorJune = true;
                    separator = new Separator(Separator.TYPE_JUNE);
                }
                break;
            case Calendar.JULY:
                if (!adapter.containsSeparatorJuly) {
                    adapter.containsSeparatorJuly = true;
                    separator = new Separator(Separator.TYPE_JULY);
                }
                break;
            case Calendar.AUGUST:
                if (!adapter.containsSeparatorAugust) {
                    adapter.containsSeparatorAugust = true;
                    separator = new Separator(Separator.TYPE_AUGUST);
                }
                break;
            case Calendar.SEPTEMBER:
                if (!adapter.containsSeparatorSeptember) {
                    adapter.containsSeparatorSeptember = true;
                    separator = new Separator(Separator.TYPE_SEPTEMBER);
                }
                break;
            case Calendar.OCTOBER:
                if (!adapter.containsSeparatorOctober) {
                    adapter.containsSeparatorOctober = true;
                    separator = new Separator(Separator.TYPE_OCTOBER);
                }
                break;
            case Calendar.NOVEMBER:
                if (!adapter.containsSeparatorNovember) {
                    adapter.containsSeparatorNovember = true;
                    separator = new Separator(Separator.TYPE_NOVEMBER);
                }
                break;
            case Calendar.DECEMBER:
                if (!adapter.containsSeparatorDecember) {
                    adapter.containsSeparatorDecember = true;
                    separator = new Separator(Separator.TYPE_DECEMBER);
                }
                break;
        }
        return separator;
    }

    public void removePersonDialog(final int location) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        Item item = adapter.getItem(location);

        Person personText = ((Person) item);
        builder.setMessage(getString(R.string.delete_record_text) + personText.getName() + "?");

        if (item.isPerson()) {
            Person person = ((Person) item);
            final long timeStamp = person.getTimeStamp();
            final boolean[] isRemoved = {false};

            builder.setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    adapter.removePerson(location);
                    isRemoved[0] = true;
                    Snackbar snackbar = Snackbar.make(activity.findViewById(R.id.container_main),
                            R.string.record_deleted, Snackbar.LENGTH_SHORT);

                    snackbar.setAction(getString(R.string.undo), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addPerson(activity.dbHelper.query().getPerson(timeStamp), false);
                            isRemoved[0] = false;
                        }
                    });

                    snackbar.getView().addOnAttachStateChangeListener(
                            new View.OnAttachStateChangeListener() {
                                @Override
                                public void onViewAttachedToWindow(View v) {
                                }

                                @Override
                                public void onViewDetachedFromWindow(View v) {
                                    if (isRemoved[0]) {
                                        alarmHelper.removeAlarms(timeStamp);
                                        activity.dbHelper.removePerson(timeStamp);
                                        deletingRecordListener.onRecordDeleted(timeStamp);
                                    }
                                }
                            });
                    snackbar.show();
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        }
        builder.show();
    }

    public void findPerson(String name) {
        adapter.removeAllPersons();
        List<Person> persons = new ArrayList<>();
        persons.addAll(activity.dbHelper.query().getSearchPerson(DbHelper.SELECTION_LIKE_NAME,
                new String[]{"%" + name + "%"}, DbHelper.COLUMN_NAME));

        for (int i = 0; i < persons.size(); i++) {
            addPerson(persons.get(i), false);
        }
    }

    public interface DeletingRecordListener {
        void onRecordDeleted(long timeStamp);
    }
}
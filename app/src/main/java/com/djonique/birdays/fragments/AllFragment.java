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

import java.util.Collections;
import java.util.List;

public class AllFragment extends Fragment {

    private MainActivity activity;
    private AllFragmentAdapter adapter;
    private DeletingPersonListener deletingPersonListener;
    private AlarmHelper alarmHelper;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            deletingPersonListener = (MainActivity) getActivity();
        } catch (ClassCastException ignored) {
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            activity = (MainActivity) getActivity();
            refreshAllPersonsFromDb();
            alarmHelper = new AlarmHelper(activity);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AllFragmentAdapter(this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void addPerson(Person person, boolean saveToDb) {
        if (saveToDb) {
            activity.dbHelper.addRecord(person);
            refreshAllPersonsFromDb();
        }
    }

//    public List<Item> addSeparatorsToPersons(List<Person> persons) {
//        List<Item> fullList = new ArrayList<>();
//        Person last = null;
//        //need the persons to be sorted by month
//        Collections.sort(persons);
//        for (Person person : persons) {
//            //we just switched months
//            if ((last == null) || (person.getMonth() > last.getMonth())) {
//                fullList.add(new Separator(person.getMonth()));
//            }
//            fullList.add(person);
//            last = person;
//        }
//
//        return fullList;
//    }

    public void showPersons(List<Person> persons) {
        //final List<Item> fullList = addSeparatorsToPersons(persons);
        Collections.sort(persons);
        for (Item item : persons) {
            adapter.addItem(item);
        }
    }

    public void refreshAllPersonsFromDb() {
        adapter.removeAllPersons();
        final List<Person> persons = activity.dbHelper.query().getPersons();
        showPersons(persons);
    }

    public void removePersonDialog(final int location) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        final Person person = (Person) adapter.getItem(location);
        final long timeStamp = person.getTimeStamp();

        builder.setMessage(getString(R.string.delete_record_text) + person.getName() + "?");

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
                        refreshAllPersonsFromDb();
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
                                    alarmHelper.removeAlarms(person);
                                    activity.dbHelper.removeRecord(timeStamp);
                                    deletingPersonListener.onPersonDeleted(timeStamp);
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
        builder.show();
    }

    public void findPerson(String name) {
        adapter.removeAllPersons();
        List<Person> persons = activity.dbHelper.query().getSearchPerson(DbHelper.SELECTION_LIKE_NAME,
                new String[]{"%" + name + "%"}, DbHelper.COLUMN_NAME);

        showPersons(persons);
    }

    public interface DeletingPersonListener {
        void onPersonDeleted(long timeStamp);
    }
}
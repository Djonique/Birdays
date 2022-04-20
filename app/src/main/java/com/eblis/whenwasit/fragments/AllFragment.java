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

package com.eblis.whenwasit.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.activities.MainActivity;
import com.eblis.whenwasit.adapters.AllFragmentAdapter;
import com.eblis.whenwasit.alarm.AlarmHelper;
import com.eblis.whenwasit.database.DbHelper;
import com.eblis.whenwasit.models.Item;
import com.eblis.whenwasit.models.Person;

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

    private void showPersons(List<Person> persons) {
        //final List<Item> fullList = addSeparatorsToPersons(persons);
        Collections.sort(persons);
        for (Item item : persons) {
            adapter.addItem(item);
        }
        adapter.scrollToClosestPerson();
    }

    public void refreshAllPersonsFromDb() {
        adapter.removeAllPersons();
        final List<Person> persons = activity.dbHelper.query().getPersons();
        showPersons(persons);
    }

    public void removePersonDialog(final int location) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        final Person person = (Person) adapter.getItem(location);
        final long recordId = person.getId();

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
                                    activity.dbHelper.removeRecord(recordId);
                                    deletingPersonListener.onPersonDeleted(recordId);
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

    public void findPerson(final String text) {
        adapter.removeAllPersons();
        List<Person> persons = activity.dbHelper.query().getSearchPerson(DbHelper.SEARCH_QUERY,
                new String[]{"%" + text + "%", "%" + text + "%"}, DbHelper.COLUMN_NAME);

        showPersons(persons);
    }

    public interface DeletingPersonListener {
        void onPersonDeleted(long timeStamp);
    }
}
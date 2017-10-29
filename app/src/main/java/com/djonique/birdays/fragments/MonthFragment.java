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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djonique.birdays.R;
import com.djonique.birdays.activities.MainActivity;
import com.djonique.birdays.adapters.MonthFragmentAdapter;
import com.djonique.birdays.database.DbHelper;
import com.djonique.birdays.models.Person;

import java.util.ArrayList;
import java.util.List;

public class MonthFragment extends Fragment {

    private MainActivity activity;
    private MonthFragmentAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            activity = (MainActivity) getActivity();
            addMonthPersonsFromDb();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new MonthFragmentAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void addPerson(Person newPerson) {
        int position = -1;

        for (int i = 0; i < adapter.getItemCount(); i++) {
            Person person = ((Person) adapter.getItem(i));
            if (newPerson.getDay(newPerson.getDate()) < person.getDay(person.getDate())) {
                position = i;
                break;
            }
        }

        if (position != -1) {
            adapter.addItem(position, newPerson);
        } else {
            adapter.addItem(newPerson);
        }
    }

    public void addMonthPersonsFromDb() {
        adapter.removeAllPersons();
        List<Person> persons = new ArrayList<>();
        persons.addAll(activity.dbHelper.query().getThisMonthPersons());

        for (int i = 0; i < persons.size(); i++) {
            addPerson(persons.get(i));
        }
    }

    public void deleteRecord(long timeStamp) {
        adapter.removePerson(timeStamp);
    }

    public void findPerson(String name) {
        adapter.removeAllPersons();
        List<Person> persons = new ArrayList<>();
        persons.addAll(activity.dbHelper.query().getSearchMonthPerson(DbHelper.SELECTION_LIKE_NAME,
                new String[]{"%" + name + "%"}, DbHelper.COLUMN_NAME));

        for (int i = 0; i < persons.size(); i++) {
            addPerson(persons.get(i));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.notifyDataSetChanged();
    }
}
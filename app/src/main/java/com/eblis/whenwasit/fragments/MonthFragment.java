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

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.activities.MainActivity;
import com.eblis.whenwasit.adapters.MonthFragmentAdapter;
import com.eblis.whenwasit.database.DbHelper;
import com.eblis.whenwasit.models.Person;

import java.util.ArrayList;
import java.util.Collections;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
            Person person = adapter.getPerson(i);
            if (newPerson.getDay() < person.getDay()) {
                position = i;
                break;
            }
        }

        if (position != -1) {
            adapter.addPerson(position, newPerson);
        } else {
            adapter.addPerson(newPerson);
        }
    }

    public void addMonthPersonsFromDb() {
        adapter.removeAllPersons();
        List<Person> persons = new ArrayList<>(activity.dbHelper.query().getThisMonthPersons());
        Collections.sort(persons);

        for (int i = 0; i < persons.size(); i++) {
            addPerson(persons.get(i));
        }
    }

    public void deleteRecord(long recordId) {
        adapter.removePerson(recordId);
    }

    public void findPerson(final String text) {
        adapter.removeAllPersons();
        List<Person> persons = new ArrayList<>(activity.dbHelper.query().getSearchMonthPersons(
                DbHelper.SEARCH_QUERY,
                new String[]{ "%" + text + "%", "%" + text + "%" },
                DbHelper.COLUMN_NAME));
        Collections.sort(persons);

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
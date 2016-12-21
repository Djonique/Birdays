package com.djonique.birdays.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djonique.birdays.activities.MainActivity;
import com.djonique.birdays.R;
import com.djonique.birdays.adapters.MonthFragmentAdapter;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MonthFragment extends Fragment {

    public MainActivity activity;
    private MonthFragmentAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            activity = (MainActivity) getActivity();
            addPersonFromDB();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new MonthFragmentAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void addPerson(Person newPerson) {
        int position = -1;

        for (int i = 0; i < adapter.getItemCount(); i++) {
            Person person = ((Person) adapter.getItem(i));
            if (newPerson.getDate() < person.getDate()) {
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

    public void addPersonFromDB() {
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
        persons.addAll(activity.dbHelper.query().getSearchMonthPerson(DBHelper.SELECTION_LIKE_NAME,
                new String[]{"%" + name + "%"}, DBHelper.COLUMN_NAME));

        for (int i = 0; i < persons.size(); i++) {
            addPerson(persons.get(i));
        }
    }
}

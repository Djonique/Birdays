package com.djonique.birdays.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djonique.birdays.AlarmHelper;
import com.djonique.birdays.activities.MainActivity;
import com.djonique.birdays.R;
import com.djonique.birdays.adapters.AllFragmentAdapter;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.model.Item;
import com.djonique.birdays.model.Person;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment {

    public MainActivity activity;
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
            addPersonFromDB();
        }

        alarmHelper = AlarmHelper.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new AllFragmentAdapter(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void addPerson(Person newPerson, boolean saveToDB) {
        int position = -1;

        for (int i = 0; i < adapter.getItemCount(); i++) {
            if (adapter.getItem(i).isPerson()) {
                Person person = ((Person) adapter.getItem(i));

                if (newPerson.getMonth(newPerson.getDate()) < person.getMonth(person.getDate())) {
                    position = i;
                    break;
                } else if (newPerson.getMonth(newPerson.getDate()) ==
                        person.getMonth(person.getDate())) {
                    if (newPerson.getDay(newPerson.getDate()) < person.getDay(person.getDate())) {
                        position = i;
                        break;
                    }
                }
            }
        }

        if (position != -1) {
            adapter.addItem(position, newPerson);
        } else {
            adapter.addItem(newPerson);
        }

        if (saveToDB) {
            activity.dbHelper.addRec(newPerson);
        }
    }

    public void addPersonFromDB() {
        adapter.removeAllPersons();
        List<Person> persons = new ArrayList<>();
        persons.addAll(activity.dbHelper.query().getPersons());

        for (int i = 0; i < persons.size(); i++) {
            addPerson(persons.get(i), false);
        }
    }

    public void removePersonDialog(final int location) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Item item = adapter.getItem(location);

        Person personText = ((Person) item);
        builder.setMessage(getString(R.string.delete_record_text) + " " + personText.getName());

        if (item.isPerson()) {
            Person person = ((Person) item);
            final long timeStamp = person.getTimeStamp();
            final boolean[] isRemoved = {false};

            builder.setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    adapter.removePerson(location);
                    isRemoved[0] = true;
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.container),
                            R.string.record_removed, Snackbar.LENGTH_LONG);

                    snackbar.setAction(getString(R.string.cancel_button), new View.OnClickListener() {
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
                                        alarmHelper.removeAlarm(timeStamp);
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
        persons.addAll(activity.dbHelper.query().getSearchPerson(DBHelper.SELECTION_LIKE_NAME,
                new String[]{"%" + name + "%"}, DBHelper.COLUMN_NAME));

        for (int i = 0; i < persons.size(); i++) {
            addPerson(persons.get(i), false);
        }
    }

    public interface DeletingRecordListener {
        void onRecordDeleted(long timeStamp);
    }
}

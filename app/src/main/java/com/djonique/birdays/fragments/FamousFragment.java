package com.djonique.birdays.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djonique.birdays.R;

public class FamousFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        //FloatingActionButton fab = ((MainActivity) getActivity()).getFab();
        //fab.setVisibility(View.GONE);
        return view;
    }
}

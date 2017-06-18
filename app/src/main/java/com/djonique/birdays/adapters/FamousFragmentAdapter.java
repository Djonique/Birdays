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

package com.djonique.birdays.adapters;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.djonique.birdays.R;
import com.djonique.birdays.models.Item;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.Utils;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class FamousFragmentAdapter extends RecyclerView.Adapter<FamousFragmentAdapter.ListViewHolder> {

    private List<Item> items;
    private Context context;
    private FirebaseAnalytics mFirebaseAnalytics;

    public FamousFragmentAdapter() {
        this.items = new ArrayList<>();
    }

    private Item getItem(int position) {
        return items.get(position);
    }

    public void addItem(Item item) {
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public void removeAllPersons() {
        if (getItemCount() != 0) {
            items = new ArrayList<>();
            notifyDataSetChanged();
        }
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        View view = LayoutInflater.from(context).inflate(R.layout.description_famous_list_view,
                parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, int position) {
        holder.itemView.setEnabled(true);
        View itemView = holder.itemView;
        Item item = getItem(position);
        final Person person = (Person) item;
        final String name = person.getName();

        holder.tvName.setText(name);
        holder.tvDate.setText(Utils.getDate(person.getDate()));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAnalytics.logEvent(ConstantManager.FAMOUS_PERSON_CLICKED, new Bundle());
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, name);
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(context, R.string.famous_search_error_text, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDate;

        ListViewHolder(View itemView) {
            super(itemView);
            tvName = ButterKnife.findById(itemView, R.id.tvFamousName);
            tvDate = ButterKnife.findById(itemView, R.id.tvFamousDate);
        }
    }
}
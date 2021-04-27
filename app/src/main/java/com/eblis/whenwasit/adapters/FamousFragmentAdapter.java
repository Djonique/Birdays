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

package com.eblis.whenwasit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eblis.whenwasit.R;
import com.eblis.whenwasit.models.Person;
import com.eblis.whenwasit.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FamousFragmentAdapter extends RecyclerView.Adapter<FamousFragmentAdapter.ListViewHolder> {

    private List<Person> famous;
    private Context context;

    public FamousFragmentAdapter() {
        famous = new ArrayList<>();
    }

    private Person getPerson(int position) {
        return famous.get(position);
    }

    public void addPerson(Person person) {
        famous.add(person);
        notifyItemInserted(getItemCount() - 1);
    }

    public void removeAllPersons() {
        if (getItemCount() != 0) {
            famous = new ArrayList<>();
            notifyDataSetChanged();
        }
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.description_famous_list_view,
                parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, int position) {
        holder.itemView.setEnabled(true);
        View itemView = holder.itemView;
        final Person person = getPerson(position);
        final String name = person.getName();

        holder.tvName.setText(name);
        holder.tvDate.setText(Utils.getDate(person.getDate()));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openBrowser(context, context.getString(R.string.google_search) + name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return famous.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDate;

        ListViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textview_famous_name);
            tvDate = itemView.findViewById(R.id.textview_famous_date);
        }
    }
}
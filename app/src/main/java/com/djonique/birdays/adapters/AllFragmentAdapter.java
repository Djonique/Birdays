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

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djonique.birdays.R;
import com.djonique.birdays.activities.DetailActivity;
import com.djonique.birdays.activities.MainActivity;
import com.djonique.birdays.fragments.AllFragment;
import com.djonique.birdays.models.DisplayedAge;
import com.djonique.birdays.models.Item;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.models.Separator;
import com.djonique.birdays.utils.Constants;
import com.djonique.birdays.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AllFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public boolean[] containedSeparators = new boolean[12]; //all months

    private List<Item> items;
    private AllFragment allFragment;
    private Context context;
    private DisplayedAge displayedAge;

    public AllFragmentAdapter(AllFragment allFragment) {
        this.allFragment = allFragment;
        items = new ArrayList<>();
    }

    private AllFragment getAllFragment() {
        return allFragment;
    }

    public Item getItem(int position) {
        return items.get(position);
    }

    public void addItem(Item item) {
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addItem(int location, Item item) {
        items.add(location, item);
        notifyItemInserted(location);
    }

    public void removePerson(int location) {
        if (location >= 0 && location < getItemCount()) {
            items.remove(location);
            notifyItemRemoved(location);

            if (location - 1 >= 0 && !getItem(location - 1).isPerson()) {

                if (location != getItemCount() && !getItem(location).isPerson() && !getItem(location - 1).isPerson()) {
                    Separator separator = (Separator) getItem(location - 1);
                    checkSeparator(separator.getType());
                    items.remove(location - 1);
                    notifyItemRemoved(location - 1);

                } else if (location == getItemCount() && !getItem(location - 1).isPerson()) {
                    Separator separator = (Separator) getItem(location - 1);
                    checkSeparator(separator.getType());
                    items.remove(location - 1);
                    notifyItemRemoved(location - 1);
                }

            } else if (getItemCount() - 1 >= 0 && !getItem(getItemCount() - 1).isPerson()) {
                Separator separator = (Separator) getItem(getItemCount() - 1);
                checkSeparator(separator.getType());
                int locationTemp = getItemCount() - 1;
                items.remove(locationTemp);
                notifyItemRemoved(locationTemp);
            }
        }
    }

    private void checkSeparator(int type) {
        containedSeparators[type] = false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        displayedAge = Utils.getDisplayedAge(PreferenceManager.getDefaultSharedPreferences(context)
                .getString(Constants.DISPLAYED_AGE_KEY, DisplayedAge.CURRENT.name()));
        switch (ItemType.values()[viewType]) {
            case PERSON:
                View view = LayoutInflater.from(context).inflate(R.layout.description_list_view,
                        parent, false);
                return new ListViewHolder(view);
            case SEPARATOR:
                View separator = LayoutInflater.from(context).inflate(R.layout.model_separator,
                        parent, false);
                return new SeparatorViewHolder(separator);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Item item = getItem(position);

        String[] months = holder.itemView.getResources().getStringArray(R.array.months);

        if (item.isPerson()) {
            holder.itemView.setEnabled(true);
            View itemView = holder.itemView;
            final Person person = ((Person) item);
            final ListViewHolder listViewHolder = ((ListViewHolder) holder);

            listViewHolder.tvName.setText(person.getName());

            long date = person.getDate();

            if (person.isYearUnknown()) {
                listViewHolder.tvAge.setVisibility(View.GONE);
                listViewHolder.tvDate.setText(Utils.getDateWithoutYear(date));
            } else {
                listViewHolder.tvAge.setVisibility(View.VISIBLE);
                final int age = Utils.getAge(date, displayedAge);
                listViewHolder.tvDate.setText(Utils.getDate(date));
                GradientDrawable ageCircle = (GradientDrawable) listViewHolder.tvAge.getBackground();
                ageCircle.setColor(ContextCompat.getColor(context, getAgeCircleColor(age)));
                listViewHolder.tvAge.setText(String.valueOf(age));
            }

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    getAllFragment().removePersonDialog(listViewHolder.getLayoutPosition());
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    allFragment.startActivity(new Intent(context, DetailActivity.class).
                            putExtra(Constants.TIME_STAMP, person.getTimeStamp()));
                    if (context instanceof MainActivity) {
                        ((MainActivity) context).overridePendingTransition(R.anim.activity_secondary_in,
                                R.anim.activity_primary_out);
                    }
                }
            });
        } else {
            Separator separator = ((Separator) item);
            ((SeparatorViewHolder) holder).separatorView.setText(months[separator.getType()]);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removeAllPersons() {
        if (getItemCount() != 0) {
            items = new ArrayList<>();
            notifyDataSetChanged();
            for (int i = 0; i < containedSeparators.length; i++) {
                containedSeparators[i] = false;
            }
        }
    }

    /**
     * Selects certain color depending on person's age
     */
    private int getAgeCircleColor(int age) {
        int colorResId;
        if (age < 10) colorResId = R.color.age1;
        else if (age >= 10 && age < 20) colorResId = R.color.age2;
        else if (age >= 20 && age < 30) colorResId = R.color.age3;
        else if (age >= 30 && age < 40) colorResId = R.color.age4;
        else if (age >= 40 && age < 50) colorResId = R.color.age5;
        else if (age >= 50 && age < 60) colorResId = R.color.age6;
        else if (age >= 60 && age < 70) colorResId = R.color.age7;
        else colorResId = R.color.age8;
        return colorResId;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).isPerson() ? ItemType.PERSON.ordinal() : ItemType.SEPARATOR.ordinal();
    }

    private static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDate, tvAge;

        ListViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textview_all_name);
            tvDate = itemView.findViewById(R.id.textview_all_date);
            tvAge = itemView.findViewById(R.id.textview_all_age);
        }
    }

    private class SeparatorViewHolder extends RecyclerView.ViewHolder {
        TextView separatorView;

        SeparatorViewHolder(View itemView) {
            super(itemView);
            separatorView = itemView.findViewById(R.id.textview_separator);
        }
    }
}
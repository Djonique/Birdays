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
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.djonique.birdays.R;
import com.djonique.birdays.activities.DetailActivity;
import com.djonique.birdays.activities.MainActivity;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.Constants;
import com.djonique.birdays.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MonthFragmentAdapter extends RecyclerView.Adapter<MonthFragmentAdapter.CardViewHolder> {

    private Context context;
    private SharedPreferences preferences;
    private List<Person> persons;

    public MonthFragmentAdapter() {
        persons = new ArrayList<>();
    }

    public Person getPerson(int position) {
        return persons.get(position);
    }

    public void addPerson(Person person) {
        if (Utils.isCurrentMonth(person.getDate())) {
            persons.add(person);
            notifyItemInserted(getItemCount() - 1);
        }
    }

    public void addPerson(int location, Person person) {
        if (Utils.isCurrentMonth(person.getDate())) {
            persons.add(location, person);
            notifyItemInserted(location);
        }
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        View view = LayoutInflater.from(context).inflate(
                R.layout.description_card_view, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        final Person person = persons.get(position);
        long date = person.getDate();
        final String email = person.getEmail();
        final String phoneNumber = person.getPhoneNumber();

        holder.tvName.setText(person.getName());

        if (person.isYearUnknown()) {
            holder.tvDate.setText(Utils.getDateWithoutYear(date));
            holder.tvAge.setVisibility(View.GONE);
            changeCardViewBackgroundColor(date, holder.cardView, holder.tvDaysLeft);
        } else {
            holder.tvDate.setText(Utils.getDate(date));
            holder.tvAge.setVisibility(View.VISIBLE);
            String age = context.getString(R.string.age) + Utils.getCurrentAge(date);
            holder.tvAge.setText(age);
            changeCardViewBackgroundColor(date, holder.cardView, holder.tvDaysLeft);
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailActivity.class)
                        .putExtra(Constants.TIME_STAMP, person.getTimeStamp()));
                if (context instanceof MainActivity) {
                    ((MainActivity) context).overridePendingTransition(R.anim.activity_secondary_in, R.anim.activity_primary_out);
                }
            }
        });

        if (email != null && !email.equals("")) {
            enableButton(holder.btnEmail);
            holder.btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(Intent.createChooser(new Intent(Intent.ACTION_SENDTO)
                            .setType(Constants.TYPE_EMAIL)
                            .putExtra(Intent.EXTRA_EMAIL, new String[]{email})
                            .putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.happy_birthday))
                            .setData(Uri.parse(Constants.MAILTO + email)), null));
                }
            });
        } else {
            disableButton(holder.btnEmail);
        }

        if (phoneNumber != null && !phoneNumber.equals("")) {
            enableButton(holder.btnCall);
            enableButton(holder.btnChat);
            holder.btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(Intent.createChooser(new Intent(Intent.ACTION_DIAL,
                            Uri.parse(Constants.TEL + phoneNumber)), null));
                }
            });

            holder.btnChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW)
                            .setType(Constants.TYPE_SMS)
                            .putExtra(Constants.ADDRESS, phoneNumber)
                            .setData(Uri.parse(Constants.SMSTO + phoneNumber)), null));
                }
            });
        } else {
            disableButton(holder.btnCall);
            disableButton(holder.btnChat);
        }
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public void removePerson(long timeStamp) {
        for (int i = 0; i < getItemCount(); i++) {
            Person person = getPerson(i);

            if (person.getTimeStamp() == timeStamp) {
                persons.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public void removeAllPersons() {
        if (getItemCount() != 0) {
            persons = new ArrayList<>();
            notifyDataSetChanged();
        }
    }

    private void enableButton(ImageButton button) {
        if (nightMode()) {
            button.setColorFilter(Color.rgb(104, 239, 173));
        } else {
            button.setColorFilter(Color.rgb(33, 150, 243));
        }
        button.setClickable(true);
    }

    private void disableButton(ImageButton button) {
        if (nightMode()) {
            button.setColorFilter(Color.rgb(112, 112, 112));
        } else {
            button.setColorFilter(Color.rgb(224, 224, 224));
        }
        button.setClickable(false);
    }

    private boolean nightMode() {
        return preferences.getBoolean(Constants.NIGHT_MODE_KEY, false);
    }

    private void changeCardViewBackgroundColor(long date, CardView cardView, TextView textView) {
        String daysLeft = Utils.daysLeft(context, date);
        if (Utils.isBirthdayPassed(date)) {
            textView.setVisibility(View.GONE);
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.cardview_background));
        } else {
            textView.setVisibility(View.VISIBLE);
            String today = context.getString(R.string.today);
            if (daysLeft.equals(today)) {
                cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.cardview_birthday));
                textView.setText(today);
            } else {
                cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.cardview_background));
                String summary = context.getString(R.string.days_left) + ": " + daysLeft;
                textView.setText(summary);
            }
        }
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        RelativeLayout relativeLayout;
        TextView tvName, tvDate, tvAge, tvDaysLeft;
        ImageButton btnEmail, btnChat, btnCall;

        CardViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_card);
            relativeLayout = itemView.findViewById(R.id.relativelayout_card);
            tvName = itemView.findViewById(R.id.textview_card_name);
            tvDate = itemView.findViewById(R.id.textview_card_date);
            tvAge = itemView.findViewById(R.id.textview_card_age);
            tvDaysLeft = itemView.findViewById(R.id.textview_card_left);
            btnEmail = itemView.findViewById(R.id.imagebutton_card_email);
            btnChat = itemView.findViewById(R.id.imagebutton_card_chat);
            btnCall = itemView.findViewById(R.id.imagebutton_card_call);
        }
    }
}
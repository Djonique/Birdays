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
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.djonique.birdays.models.Item;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.Utils;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

public class MonthFragmentAdapter extends RecyclerView.Adapter<MonthFragmentAdapter.CardViewHolder> {

    private Context context;
    private SharedPreferences sharedPreferences;
    private FirebaseAnalytics mFirebaseAnalytics;
    private List<Item> items;

    public MonthFragmentAdapter() {
        items = new ArrayList<>();
    }

    public Item getItem(int position) {
        return items.get(position);
    }

    public void addItem(Item item) {
        Person person = (Person) item;
        if (Utils.isCurrentMonth(person.getDate())) {
            items.add(item);
            notifyItemInserted(getItemCount() - 1);
        }
    }

    public void addItem(int location, Item item) {
        Person person = (Person) item;
        if (Utils.isCurrentMonth(person.getDate())) {
            items.add(location, item);
            notifyItemInserted(location);
        }
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        View view = LayoutInflater.from(context).inflate(
                R.layout.description_card_view, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        final Item item = items.get(position);
        final Person person = (Person) item;
        long date = person.getDate();
        boolean unknownYear = person.isYearUnknown();
        final String email = person.getEmail();
        final String phoneNumber = person.getPhoneNumber();

        holder.tvName.setText(person.getName());

        if (unknownYear) {
            holder.tvDate.setText(Utils.getDateWithoutYear(date));
            holder.tvAge.setVisibility(View.GONE);
        } else {
            holder.tvDate.setText(Utils.getDate(date));
            String age = context.getString(R.string.turns) + Integer.toString(Utils.getFutureAge(date));
            holder.tvAge.setVisibility(View.VISIBLE);
            holder.tvAge.setText(age);
        }

        String daysLeft = Utils.daysLeft(context, date);
        String today = context.getString(R.string.today);
        holder.tvDaysLeft.setText(daysLeft.equals(today) ?
                today : context.getString(R.string.days_left) + ": " + daysLeft);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(ConstantManager.TIME_STAMP, person.getTimeStamp());
                context.startActivity(intent);
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
                    mFirebaseAnalytics.logEvent(ConstantManager.SEND_EMAIL, new Bundle());
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setType(ConstantManager.TYPE_EMAIL);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.happy_birthday));
                    intent.setData(Uri.parse(ConstantManager.MAILTO + email));
                    context.startActivity(Intent.createChooser(intent, context.getString(R.string.send_email)));
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
                    mFirebaseAnalytics.logEvent(ConstantManager.MAKE_CALL, new Bundle());
                    context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(ConstantManager.TEL + phoneNumber)));
                }
            });

            holder.btnChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFirebaseAnalytics.logEvent(ConstantManager.SEND_MESSAGE, new Bundle());
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setType(ConstantManager.TYPE_SMS);
                    intent.putExtra(ConstantManager.ADDRESS, phoneNumber);
                    intent.setData(Uri.parse(ConstantManager.SMSTO + phoneNumber));
                    context.startActivity(intent);
                }
            });
        } else {
            disableButton(holder.btnCall);
            disableButton(holder.btnChat);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removePerson(long timeStamp) {
        for (int i = 0; i < getItemCount(); i++) {
            Item item = getItem(i);
            Person person = ((Person) item);

            if (person.getTimeStamp() == timeStamp) {
                items.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public void removeAllPersons() {
        if (getItemCount() != 0) {
            items = new ArrayList<>();
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
        return sharedPreferences.getBoolean(ConstantManager.NIGHT_MODE_KEY, false);
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        RelativeLayout relativeLayout;
        TextView tvName, tvDate, tvAge, tvDaysLeft;
        ImageButton btnEmail, btnChat, btnCall;

        CardViewHolder(View itemView) {
            super(itemView);
            cardView = ((CardView) itemView.findViewById(R.id.cardview_card));
            relativeLayout = ((RelativeLayout) itemView.findViewById(R.id.relativelayout_card));
            tvName = ((TextView) itemView.findViewById(R.id.textview_card_name));
            tvDate = ((TextView) itemView.findViewById(R.id.textview_card_date));
            tvAge = ((TextView) itemView.findViewById(R.id.textview_card_age));
            tvDaysLeft = ((TextView) itemView.findViewById(R.id.textview_card_left));
            btnEmail = ((ImageButton) itemView.findViewById(R.id.imagebutton_card_email));
            btnEmail.setImageResource(R.drawable.ic_email_blue_green_24dp);
            btnChat = ((ImageButton) itemView.findViewById(R.id.imagebutton_card_chat));
            btnChat.setImageResource(R.drawable.ic_chat_blue_green_24dp);
            btnCall = ((ImageButton) itemView.findViewById(R.id.imagebutton_card_call));
            btnCall.setImageResource(R.drawable.ic_call_blue_green_24dp);
        }
    }
}
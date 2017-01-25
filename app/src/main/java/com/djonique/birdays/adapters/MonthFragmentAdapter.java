package com.djonique.birdays.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.djonique.birdays.R;
import com.djonique.birdays.models.Item;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.IntentHelper;
import com.djonique.birdays.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class MonthFragmentAdapter extends RecyclerView.Adapter<MonthFragmentAdapter.CardViewHolder> {

    private Context context;
    private List<Item> items;
    private String email, phoneNumber;

    private int enabled = Color.rgb(156, 39, 176);
    private int disabled = Color.rgb(224, 224, 224);

    public MonthFragmentAdapter(Context context) {
        this.context = context;
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
        email = person.getEmail();
        phoneNumber = person.getPhoneNumber();

        holder.tvName.setText(person.getName());

        if (unknownYear) {
            holder.tvDate.setText(Utils.getUnknownDate(date));
            holder.tvAge.setVisibility(View.GONE);
        } else {
            holder.tvDate.setText(Utils.getDate(date));
            String age = context.getString(R.string.age_text) + Integer.toString(Utils.getAge(date));
            holder.tvAge.setVisibility(View.VISIBLE);
            holder.tvAge.setText(age);
        }

        if (email != null) {
            enableButton(holder.btnEmail);
            holder.btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentHelper.sendEmail(context, email);
                }
            });
        } else {
            disableButton(holder.btnEmail);
        }

        if (phoneNumber != null) {
            enableButton(holder.btnPhone);
            enableButton(holder.btnSMS);
            holder.btnPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentHelper.call(context, phoneNumber);
                }
            });

            holder.btnSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentHelper.sendSms(context, phoneNumber);
                }
            });

        } else {
            disableButton(holder.btnPhone);
            disableButton(holder.btnSMS);
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
        button.setColorFilter(enabled);
        button.setClickable(true);
    }

    private void disableButton(ImageButton button) {
        button.setColorFilter(disabled);
        button.setClickable(false);
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        RelativeLayout relativeLayout;
        TextView tvName, tvDate, tvAge;
        ImageButton btnPhone, btnEmail, btnSMS;

        CardViewHolder(View itemView) {
            super(itemView);
            cardView = ButterKnife.findById(itemView, R.id.cardView);
            relativeLayout = ButterKnife.findById(itemView, R.id.relativeLayout);
            tvName = ButterKnife.findById(itemView, R.id.tvName);
            tvDate = ButterKnife.findById(itemView, R.id.tvDate);
            tvAge = ButterKnife.findById(itemView, R.id.tvAge);
            btnEmail = ButterKnife.findById(itemView, R.id.btnEmail);
            btnEmail.setImageResource(R.drawable.ic_email_purple_24dp);
            btnSMS = ButterKnife.findById(itemView, R.id.btnSMS);
            btnSMS.setImageResource(R.drawable.ic_chat_purple_24dp);
            btnPhone = ButterKnife.findById(itemView, R.id.btnPhone);
            btnPhone.setImageResource(R.drawable.ic_call_purple_24dp);
        }
    }
}



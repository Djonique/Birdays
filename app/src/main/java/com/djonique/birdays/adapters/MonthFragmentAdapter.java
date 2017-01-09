package com.djonique.birdays.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import com.djonique.birdays.model.Item;
import com.djonique.birdays.model.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class MonthFragmentAdapter extends RecyclerView.Adapter<MonthFragmentAdapter.CardViewHolder> {

    private Context context;
    private List<Item> items;
    private int disabled = Color.rgb(224, 224, 224);
    private int enabled = Color.rgb(104, 239, 173);

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
        String age = context.getString(R.string.age_text) + Integer.toString(Utils.getAge(date));

        holder.tvName.setText(person.getName());
        holder.tvDate.setText(Utils.getDate(date));
        holder.tvAge.setText(age);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(ConstantManager.TIME_STAMP, person.getTimeStamp());
                context.startActivity(intent);
                if (context instanceof MainActivity) {
                    ((MainActivity) context).overridePendingTransition(R.anim.main_detail_in, R.anim.main_detail_out);
                }
            }
        });

        if (person.getPhoneNumber() != null) {
            holder.btnPhone.setColorFilter(enabled);
            holder.btnSMS.setColorFilter(enabled);
            holder.btnPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse(ConstantManager.TEL + person.getPhoneNumber()));
                    context.startActivity(intent);
                }
            });

            holder.btnSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phoneNumber = person.getPhoneNumber();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setType(ConstantManager.TYPE_SMS);
                    intent.putExtra(ConstantManager.ADDRESS, phoneNumber);
                    intent.setData(Uri.parse(ConstantManager.SMSTO + phoneNumber));
                    context.startActivity(intent);
                }
            });

        } else {
            disableButton(holder.btnPhone);
            disableButton(holder.btnSMS);
        }

        if (person.getEmail() != null) {
            holder.btnEmail.setColorFilter(enabled);
            holder.btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = person.getEmail();
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

    private void disableButton(ImageButton button) {
        button.setEnabled(true);
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
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            relativeLayout = ((RelativeLayout) itemView.findViewById(R.id.relativeLayout));
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvAge = (TextView) itemView.findViewById(R.id.tvAge);
            btnEmail = (ImageButton) itemView.findViewById(R.id.btnEmail);
            btnEmail.setImageResource(R.drawable.ic_email_purple_24dp);
            btnSMS = (ImageButton) itemView.findViewById(R.id.btnSMS);
            btnSMS.setImageResource(R.drawable.ic_chat_purple_24dp);
            btnPhone = (ImageButton) itemView.findViewById(R.id.btnPhone);
            btnPhone.setImageResource(R.drawable.ic_call_purple_24dp);
        }
    }
}



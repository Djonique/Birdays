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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.djonique.birdays.R;
import com.djonique.birdays.Utils;
import com.djonique.birdays.fragments.MonthFragment;
import com.djonique.birdays.model.Item;
import com.djonique.birdays.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MonthFragmentAdapter extends RecyclerView.Adapter<MonthFragmentAdapter.CardViewHolder> {

    public static final String TYPE_EMAIL = "message/rfc822";
    public static final String MAILTO = "mailto:";
    public static final String TYPE_SMS = "vnd.android-dir/mms-sms";
    public static final String ADDRESS = "address";
    public static final String SMSTO = "smsto:";
    public static final String TEL = "tel: ";

    private Context context;
    private List<Item> items;
    MonthFragment monthFragment;

    public MonthFragmentAdapter(MonthFragment monthFragment) {
        this.monthFragment = monthFragment;
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

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_view_description, parent, false);

        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MonthFragmentAdapter.CardViewHolder holder, int position) {
        final Item item = items.get(position);
        final Person person = (Person) item;

        holder.tvName.setText(person.getName());
        if (person.getDate() != 0) {
            holder.tvDate.setText(Utils.getDate(person.getDate()));
            String age = context.getString(R.string.age_text) + Integer.toString(person.getAge());
            holder.tvAge.setText(age);
        }

        if (person.getPhoneNumber() != 0) {

            holder.btnPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation animation = AnimationUtils.loadAnimation(context, R.anim.icon_click_anim);
                    holder.btnPhone.startAnimation(animation);
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(TEL + person.getPhoneNumber()));
                    context.startActivity(intent);
                }
            });

            holder.btnSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation animation = AnimationUtils.loadAnimation(context, R.anim.icon_click_anim);
                    holder.btnSMS.startAnimation(animation);
                    long phoneNumber = person.getPhoneNumber();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setType(TYPE_SMS);
                    intent.putExtra(ADDRESS, phoneNumber);
                    intent.setData(Uri.parse(SMSTO + phoneNumber));
                    context.startActivity(intent);
                }
            });

        } else {
            holder.btnPhone.setEnabled(true);
            holder.btnSMS.setEnabled(true);
            holder.btnPhone.setColorFilter(Color.rgb(224, 224, 224));
            holder.btnSMS.setColorFilter(Color.rgb(224, 224, 224));
        }

        if (!person.getEmail().equals(" ")) {
            holder.btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation animation = AnimationUtils.loadAnimation(context, R.anim.icon_click_anim);
                    holder.btnEmail.startAnimation(animation);
                    String email = person.getEmail();
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setType(TYPE_EMAIL);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.happy_birthday));
                    intent.setData(Uri.parse(MAILTO + email));
                    context.startActivity(Intent.createChooser(intent, context.getString(R.string.send_email)));
                }
            });
        } else {
            holder.btnEmail.setEnabled(true);
            holder.btnEmail.setColorFilter(Color.rgb(224, 224, 224));
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

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvName, tvDate, tvAge;
        ImageButton btnPhone, btnEmail, btnSMS;


        public CardViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvAge = (TextView) itemView.findViewById(R.id.tvAge);
            btnPhone = (ImageButton) itemView.findViewById(R.id.btnPhone);
            btnEmail = (ImageButton) itemView.findViewById(R.id.btnEmail);
            btnSMS = (ImageButton) itemView.findViewById(R.id.btnSMS);
        }
    }

    public void removeAllPersons() {
        if (getItemCount() != 0) {
            items = new ArrayList<>();
            notifyDataSetChanged();
        }
    }

    public void startAnim() {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.icon_click_anim);
    }
}



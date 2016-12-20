package com.djonique.birdays.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djonique.birdays.DetailActivity;
import com.djonique.birdays.R;
import com.djonique.birdays.Utils;
import com.djonique.birdays.fragments.AllFragment;
import com.djonique.birdays.model.Item;
import com.djonique.birdays.model.Person;

import java.util.ArrayList;
import java.util.List;

public class AllFragmentAdapter extends RecyclerView.Adapter<AllFragmentAdapter.ListViewHolder> {

    public static final String DETAIL_NAME = "DetailName";
    public static final String DETAIL_PHONE = "DetailPhone";
    public static final String DETAIL_EMAIL = "DetailEmail";
    public static final String DETAIL_DATE = "DetailDate";
    public static final String DETAIL_AGE = "DetailAge";

    private List<Item> items;
    private AllFragment allFragment;
    private Context context;

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
        if (location >= 0 && location <= (getItemCount() - 1)) {
            items.remove(location);
            notifyItemRemoved(location);
        }
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.description_list_view,
                parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {
        holder.itemView.setEnabled(true);
        View itemView = holder.itemView;

        Item item = getItem(position);
        final Person person = ((Person) item);

        long date = person.getDate();
        final int age = Utils.getAge(date);

        holder.tvName.setText(person.getName());
        holder.tvDate.setText(Utils.getDate(date));

        GradientDrawable ageCircle = (GradientDrawable) holder.tvAge.getBackground();
        int ageCircleColor = ContextCompat.getColor(context, Utils.getAgeCircleColor(age));
        ageCircle.setColor(ageCircleColor);
        holder.tvAge.setText(String.valueOf(age));

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getAllFragment().removePersonDialog(holder.getLayoutPosition());
                    }
                }, 500);

                return true;
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DETAIL_NAME, person.getName());
                intent.putExtra(DETAIL_PHONE, person.getPhoneNumber());
                intent.putExtra(DETAIL_EMAIL, person.getEmail());
                intent.putExtra(DETAIL_DATE, person.getDate());
                intent.putExtra(DETAIL_AGE, age);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removeAllPersons() {
        if (getItemCount() != 0) {
            items = new ArrayList<>();
            notifyDataSetChanged();
        }
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDate, tvAge;

        ListViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvPersonName);
            tvDate = (TextView) itemView.findViewById(R.id.tvPersonDate);
            tvAge = (TextView) itemView.findViewById(R.id.tvListAge);
        }
    }
}

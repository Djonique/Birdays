package com.djonique.birdays.adapters;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djonique.birdays.R;
import com.djonique.birdays.Utils;
import com.djonique.birdays.model.Item;
import com.djonique.birdays.model.Person;

import java.util.ArrayList;
import java.util.List;

public class FamousFragmentAdapter extends RecyclerView.Adapter<FamousFragmentAdapter.ListViewHolder> {

    private List<Item> items;
    private Context context;

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
        View view = LayoutInflater.from(context).inflate(R.layout.description_famous_list_view,
                parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, int position) {
        holder.itemView.setEnabled(true);
        View itemView = holder.itemView;
        Item item = getItem(position);
        final Person person = ((Person) item);
        final String name = person.getName();

        holder.tvName.setText(name);
        holder.tvDate.setText(Utils.getDate(person.getDate()));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, name);
                context.startActivity(intent);
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
            tvName = (TextView) itemView.findViewById(R.id.tvFamousName);
            tvDate = (TextView) itemView.findViewById(R.id.tvFamousDate);
        }
    }
}

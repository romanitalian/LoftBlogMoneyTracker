package com.loftschool.loftmoneytracker.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loftschool.loftmoneytracker.R;
import com.loftschool.loftmoneytracker.database.Categories;

import java.util.List;

/**
 * Created by Constantine on 14.09.2015.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CardViewHolder> {
    private  List<Categories> categories;

    public CategoriesAdapter(List<Categories> categories){
        this.categories = categories;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Categories category = categories.get(position);
        holder.name.setText(category.name);
        holder.date.setText("Здесь могла быть ваша дата");
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        protected TextView name;
        protected TextView date;

        public CardViewHolder (View itemView){
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.category_name);
            date = (TextView) itemView.findViewById(R.id.category_date);
        }
    }
}

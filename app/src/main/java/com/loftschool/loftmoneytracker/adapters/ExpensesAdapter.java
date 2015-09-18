package com.loftschool.loftmoneytracker.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loftschool.loftmoneytracker.R;
import com.loftschool.loftmoneytracker.database.Expenses;

import java.util.List;

/**
 * Created by Andrew on 25.08.2015.
 */
public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.CardViewHolder> {
    private  List<Expenses> expenses;

    public ExpensesAdapter(List<Expenses> expenses){
        this.expenses = expenses;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Expenses expense = expenses.get(position);
        holder.name.setText(expense.name);
        holder.price.setText(expense.price);
        holder.date.setText(expense.date);
        holder.category.setText(expense.category.toString());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        protected TextView name;
        protected TextView price;
        protected TextView date;
        protected TextView category;

        public CardViewHolder (View itemView){
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.category_name);
            price = (TextView) itemView.findViewById(R.id.price_text);
            date = (TextView) itemView.findViewById(R.id.category_date);
            category = (TextView) itemView.findViewById(R.id.category_text);
        }
    }
}
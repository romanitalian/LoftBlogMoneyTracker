package com.loftschool.loftmoneytracker.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loftschool.loftmoneytracker.R;
import com.loftschool.loftmoneytracker.database.Expenses;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Andrew on 25.08.2015.
 */
public class ExpensesAdapter extends SelectableAdapter<ExpensesAdapter.CardViewHolder> {
    private List<Expenses> expenses;
    private CardViewHolder.ClickListener clickListener;

    public ExpensesAdapter(List<Expenses> expenses, CardViewHolder.ClickListener clickListener) {
        this.expenses = expenses;
        this.clickListener = clickListener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CardViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Expenses expense = expenses.get(position);
        holder.name.setText(expense.name);
        holder.price.setText(expense.price);
        holder.date.setText(expense.date);
        holder.category.setText(expense.category.toString());
        holder.selectedOverlay.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
    }

    private void removeItem(int position) {
        removeExpences(position);
        notifyItemRemoved(position);
    }

    public void removeItems(List<Integer> positions) {
        Collections.sort(positions, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return rhs - lhs;
            }
        });
        while (!positions.isEmpty()) {
            if (positions.size() == 1) {
                removeItem(positions.get(0));
                positions.remove(0);
            } else {
                int count = 1;
                while (positions.size() > count) {
                    count++;
                }

                removeRange(positions.get(count - 1), count);
                for (int i = 0; i < count; i++) {
                    positions.remove(0);
                }
            }
        }
    }

    private void removeExpences(int position) {
        if (expenses.get(position) != null) {
            expenses.get(position).delete();
            expenses.remove(position);
        }
    }

    private void removeRange(int positionStart, int itemCount) {
        for (int position = 0; position < itemCount; position++) {
            removeExpences(position);
        }
        notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        protected TextView name;
        protected TextView price;
        protected TextView date;
        protected TextView category;
        protected View selectedOverlay;
        private ClickListener clickListener;

        public CardViewHolder(View itemView, ClickListener clickListener) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.category_name);
            price = (TextView) itemView.findViewById(R.id.price_text);
            date = (TextView) itemView.findViewById(R.id.category_date);
            category = (TextView) itemView.findViewById(R.id.category_text);
            selectedOverlay = itemView.findViewById(R.id.selected_overlay);
            this.clickListener = clickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onItemClicked(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            if (clickListener != null) {
                return clickListener.onItemLongClicked(getAdapterPosition());
            }
            return false;
        }

        public interface ClickListener {
            public void onItemClicked(int position);

            public boolean onItemLongClicked(int position);
        }
    }
}
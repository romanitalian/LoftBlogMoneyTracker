package com.loftschool.loftmoneytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 26.08.2015.
 */
public class ExpensesFragment extends Fragment {
    private ExpensesAdapter expensesAdapter;
    private FloatingActionButton floatingActionButton;
    List<Expense> data = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.expenses_fragment, container, false);
        List<Expense> adapterData = getDataList();
        expensesAdapter = new ExpensesAdapter(adapterData);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_content);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(expensesAdapter);
        getActivity().setTitle("First Fragment");
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), AddTransactionActivity.class);
                getActivity().startActivity(myIntent);

            }
        });
        return view;
    }

    private List<Expense> getDataList() {

        data.add(new Expense("Telephone", "2000"));
        data.add(new Expense("T-Shirts", "3000"));
        data.add(new Expense("Jeans", "1000"));
        data.add(new Expense("Snickers", "2000"));
        data.add(new Expense("Bar", "3000"));
        data.add(new Expense("Cafe", "1000"));
        data.add(new Expense("Telephone", "2000"));
        data.add(new Expense("T-Shirts", "3000"));
        data.add(new Expense("Jeans", "1000"));
        data.add(new Expense("Snickers", "2000"));
        data.add(new Expense("Bar", "3000"));
        data.add(new Expense("Cafe", "1000"));
        data.add(new Expense("Telephone", "2000"));
        data.add(new Expense("T-Shirts", "3000"));
        data.add(new Expense("Jeans", "1000"));
        data.add(new Expense("Snickers", "2000"));
        data.add(new Expense("Bar", "3000"));
        data.add(new Expense("Cafe", "1000"));
        data.add(new Expense("Telephone", "2000"));
        data.add(new Expense("T-Shirts", "3000"));
        data.add(new Expense("Jeans", "1000"));
        data.add(new Expense("Snickers", "2000"));
        data.add(new Expense("Bar", "3000"));
        data.add(new Expense("Cafe", "1000"));
        data.add(new Expense("Telephone", "2000"));
        data.add(new Expense("T-Shirts", "3000"));
        data.add(new Expense("Jeans", "1000"));
        data.add(new Expense("Snickers", "2000"));
        data.add(new Expense("Bar", "3000"));
        data.add(new Expense("Cafe", "1000"));

        return data;
    }
}

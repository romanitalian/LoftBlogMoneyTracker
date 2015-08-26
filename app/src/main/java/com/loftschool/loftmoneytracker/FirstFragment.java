package com.loftschool.loftmoneytracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 26.08.2015.
 */
public class FirstFragment extends Fragment {
    private ListView listView;
    private TransactionAdapter transactionAdapter;
    List<Transactions> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        List<Transactions> adapterData = getDataList();
        transactionAdapter = new TransactionAdapter(getActivity(), adapterData);
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(transactionAdapter);
        getActivity().setTitle("First Fragment");
        return view;
    }

    private List<Transactions> getDataList() {

        data.add(new Transactions("Telephone", "2000"));
        data.add(new Transactions("T-Shirts", "3000"));
        data.add(new Transactions("Jeans", "1000"));

        return data;
    }
}

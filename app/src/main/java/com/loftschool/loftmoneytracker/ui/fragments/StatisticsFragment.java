package com.loftschool.loftmoneytracker.ui.fragments;

import android.support.v4.app.Fragment;

import com.loftschool.loftmoneytracker.PieChartView;
import com.loftschool.loftmoneytracker.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Constantine on 14.09.2015.
 */
@EFragment(R.layout.statistics_fragment)
public class StatisticsFragment extends Fragment {

    float[] datapoints = {450, 1230, 200, 400, 500};

    @ViewById(R.id.piechart)
    PieChartView pieChartView;

    @AfterViews
    void ready() {
        getActivity().setTitle(getResources().getString(R.string.nav_drawer_statistics));
        pieChartView.setDatapoints(datapoints);
    }
}

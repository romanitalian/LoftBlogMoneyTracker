package com.loftschool.loftmoneytracker.ui.fragments;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.loftschool.loftmoneytracker.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Constantine on 14.09.2015.
 */
@EFragment(R.layout.settings_fragment)
public class SettingsFragment extends Fragment{

    @ViewById(R.id.settings_textview)
    TextView textView;

    @AfterViews
    void ready(){
        getActivity().setTitle(getResources().getString(R.string.nav_drawer_statistics));
        textView.setText(getResources().getString(R.string.settings_text));
    }

}

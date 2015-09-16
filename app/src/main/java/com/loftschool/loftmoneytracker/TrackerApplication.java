package com.loftschool.loftmoneytracker;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by Constantine on 14.09.2015.
 */
public class TrackerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

}

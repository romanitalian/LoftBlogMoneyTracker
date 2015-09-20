package com.loftschool.loftmoneytracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.loftschool.loftmoneytracker.utils.TokenKeyStorage;

/**
 * Created by Constantine on 14.09.2015.
 */
public class TrackerApplication extends Application implements TokenKeyStorage {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

    public static void setToken(Context context, String token) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.commit();
    }

    public static String getToken(Context context) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString(TOKEN_KEY, "1");
    }

}

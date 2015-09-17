package com.loftschool.loftmoneytracker.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Constantine on 16.09.2015.
 */
public class NetworkStatusChecker {

    /**
     * Returns true if the network is available or about become available.
     * @param context used to get the ConnectivityManager
     * @return
     * **/
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}

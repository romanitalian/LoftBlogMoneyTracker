package com.loftschool.loftmoneytracker.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Constantine on 19.10.2015.
 */
public class TrackerSyncService extends Service {

    private static final Object sSyncAdapterLock = new Object();
    private static TrackerSyncAdapter sTrackerSyncAdapter = null;

    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock){
            if (sTrackerSyncAdapter == null) {
                sTrackerSyncAdapter = new TrackerSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sTrackerSyncAdapter.getSyncAdapterBinder();
    }
}

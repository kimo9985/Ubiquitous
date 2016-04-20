package com.example.android.sunshine.app;

import android.util.Log;

import com.example.android.sunshine.app.sync.SunshineSyncAdapter;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.WearableListenerService;

public class MyWearableService extends WearableListenerService {

    public final String LOG_TAG = MyWearableService.class.getSimpleName();

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent dataEvent : dataEvents) {
            if (dataEvent.getType() == DataEvent.TYPE_CHANGED) {
                String path = dataEvent.getDataItem().getUri().getPath();

                Log.d(LOG_TAG, "MyWearableService path:" + path);

                if (path.equals("/weather-temp")) {
                    SunshineSyncAdapter.syncImmediately(this);
                }
            }
        }
    }
}

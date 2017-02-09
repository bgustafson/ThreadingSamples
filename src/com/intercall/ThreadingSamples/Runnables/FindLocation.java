package com.intercall.ThreadingSamples.Runnables;


import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import java.util.List;


public class FindLocation extends Thread {

    LocationManager lm;
    Context context;

    public FindLocation(Context context) {

        this.context = context;
    }

    @Override
    public void run() {

        Log.d("TEST", "This is in a background thread.");
        lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> allProviders = lm.getAllProviders();
        Location location = lm.getLastKnownLocation(allProviders.get(0));
    }
}

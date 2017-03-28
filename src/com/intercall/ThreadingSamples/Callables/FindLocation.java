package com.intercall.ThreadingSamples.Callables;


import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;


public class FindLocation implements Callable {

    private Context context;


    public FindLocation(Context context) {
        this.context = context;
    }


    @Override
    public Location call() throws Exception {

        Log.d("TEST", "This is a callable.");
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> allProviders = lm.getAllProviders();

        int providerIndex = 0;
        if (allProviders.contains("gps")) {
            providerIndex = allProviders.indexOf("gps");
        }

        Location lastLocation = lm.getLastKnownLocation(allProviders.get(providerIndex));

        //Sends an SMS from the background thread
        //SmsManager manager = SmsManager.getDefault();
        //manager.sendTextMessage("", null, "Hello from: Lat:" + lastLocation.getLatitude() + ", Long:" + lastLocation.getLongitude(), null, null);

        return lastLocation;
    }
}

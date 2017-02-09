package com.intercall.ThreadingSamples.Services;


import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

public class BindableIntentService extends IntentService {

    public BindableIntentService() {
        super(BindableIntentService.class.getName());
        setIntentRedelivery(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}

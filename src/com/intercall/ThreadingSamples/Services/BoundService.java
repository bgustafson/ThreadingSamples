package com.intercall.ThreadingSamples.Services;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BoundService extends Service{

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();




    public String saySomething() {
        return "Hello From Bound Service";
    }




    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    //LocalBinder Class
    public class LocalBinder extends Binder {

        public BoundService getService() {
            // Return this instance of FetchCertificateService so clients can call public methods
            return BoundService.this;
        }
    }
}

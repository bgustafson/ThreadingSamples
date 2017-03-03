package com.intercall.ThreadingSamples;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.intercall.ThreadingSamples.AsyncTasks.InteruptionTask;
import com.intercall.ThreadingSamples.Examples.ConsumerProducer;
import com.intercall.ThreadingSamples.Runnables.FindLocation;
import com.intercall.ThreadingSamples.Services.BoundService;

import java.util.concurrent.*;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private Handler mHandler;
    private Button startButton;
    private TextView outputView;

    private Button downloadBtn;
    private final String mURL = "www.google.com";

    private AsyncTask interuptionTask;


    //Bound Service
    private BoundService mService;
    private boolean mBound = false;

    private Button bound_serviceBTN;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "Starting MainActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        startButton = (Button) findViewById(R.id.start);
        outputView = (TextView) findViewById(R.id.output);
        startButton.setOnClickListener(new StartButtonOnClickListner());

        downloadBtn = (Button) findViewById(R.id.download);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.intercall.ThreadingSamples.ACTION_DOWNLOAD");
                intent.setData(Uri.parse(mURL));
                startService(intent);
            }
        });



        //Using the public methods in a bound service
        bound_serviceBTN = (Button) findViewById(R.id.bound_service);
        bound_serviceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBound) {
                    String something = mService.saySomething();
                    Toast.makeText(getApplicationContext(), something, Toast.LENGTH_SHORT).show();
                }
            }
        });



        //Thread - cannot return results
        FindLocation fl = new FindLocation(getApplicationContext());
        fl.start();
        //End Thread


        //Callable - returns results
        //https://blogs.oracle.com/CoreJavaTechTips/entry/get_netbeans_6
        ExecutorService pool = Executors.newFixedThreadPool(3);

        Callable<Location> callable = new com.intercall.ThreadingSamples.Callables.FindLocation(getApplicationContext());
        Future<Location> future = pool.submit(callable);

        try {
            Location location = future.get();
            double altitude = location.getAltitude() * 3.28084;
            Toast.makeText(this, "The current altitude: " + altitude + "ft", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //End Callable


        //Delaying a task
        ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
        Runnable task = new Runnable() {
            public void run() {
                /* Do something… */
            }
        };
        worker.schedule(task, 5, TimeUnit.SECONDS);
        //End Delaying a task


        //Start AsyncTask


        //End AsyncTask
    }


    public void onExecute(View v) {

        if (interuptionTask != null && interuptionTask.getStatus() != AsyncTask.Status.RUNNING) {
            interuptionTask = new InteruptionTask().execute("Something");
        }
    }


    public void useConsumerProducer() {

        final ConsumerProducer cp = new ConsumerProducer();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    cp.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    cp.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
    }


    public void handlerThreadSample() {

        HandlerThread thread = new HandlerThread("HandlerThread");
        thread.start();

        mHandler = new Handler(thread.getLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //Process messages here.
            }
        };
    }


    //FetchCertificateService Binding
    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BoundService.LocalBinder binder = (BoundService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }
}

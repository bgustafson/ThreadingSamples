package com.intercall.ThreadingSamples;

import android.app.Activity;
import android.content.Intent;
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

import java.util.concurrent.*;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private Handler mHandler;
    Button startButton;
    TextView outputView;

    private Button downloadBtn;
    private final String mURL = "www.google.com";

    private AsyncTask interuptionTask;

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
}

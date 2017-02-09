package com.intercall.ThreadingSamples.Threads;

import android.util.Log;

import java.util.concurrent.ThreadFactory;

//Useage: Executors.newFixedThreadPool(10, new LowPriorityThreadFactory());
public class LowPriorityThreadFactory implements ThreadFactory {

    private static int COUNT = 1;

    @Override
    public Thread newThread(Runnable r) {

        Thread t = new Thread(r);
        t.setName("LowPriority Thread: " + COUNT++);
        t.setPriority(4);
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                Log.d("LOW_PRIORITY_THREAD", "Thread = " + thread.getName() + ", error = " + ex.getMessage());
            }
        });

        return t;
    }
}

package com.intercall.ThreadingSamples.Executors;

import android.os.AsyncTask;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

public class QueueExecuter implements Executor {

    final ArrayDeque<Runnable> mTasks = new ArrayDeque<Runnable>();
    Runnable mActive;

    @Override
    public void execute(final Runnable command) {

        mTasks.offer(new Runnable() {
            @Override
            public void run() {

                try {
                    command.run();
                } finally {
                    scheduleNext();
                }
            }
        });

        if (mActive == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {

        if ((mActive = mTasks.poll()) != null) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(mActive);
        }
    }
}

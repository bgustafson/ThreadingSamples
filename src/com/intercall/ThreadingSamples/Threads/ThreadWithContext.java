package com.intercall.ThreadingSamples.Threads;

import android.content.Context;

public class ThreadWithContext extends Thread {

    Context context;

    public ThreadWithContext(Context context) {

        super();
        this.context = context;
    }

    public ThreadWithContext(String name, Context context) {

        super(name);
        this.context = context;
    }

    public ThreadWithContext(Runnable runnable, Context context) {

        super(runnable);
        this.context = context;
    }

    public ThreadWithContext(Runnable runnable, String name, Context context) {

        super(runnable, name);
        this.context = context;
    }
}

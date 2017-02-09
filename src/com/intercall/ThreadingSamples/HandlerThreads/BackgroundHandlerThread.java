package com.intercall.ThreadingSamples.HandlerThreads;


import android.os.*;
import android.os.Process;
import android.os.Handler;
import android.os.HandlerThread;


public class BackgroundHandlerThread extends HandlerThread {

    private Handler mHandler;

    public BackgroundHandlerThread(String name) {
        super(name, Process.THREAD_PRIORITY_BACKGROUND);
    }

    @Override
    protected void onLooperPrepared() {

        super.onLooperPrepared();

        mHandler = new Handler(getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        //Handle Message
                        break;
                    case 2:
                        //Handle Message
                        break;
                }
            }
        };
    }

    public void resetHandlerThread() {
        mHandler.removeCallbacksAndMessages(null);
    }

    public void quitHandlerThread() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Looper.myLooper().quit();
            }
        });
    }

    public void stopHandlerThread() {
        this.quit();
        this.interrupt();
    }


    //These methods can be changed.  These should be changed to make public methods available.
    public void method() {
        mHandler.sendEmptyMessage(1);
    }

    public void method2() {
        mHandler.sendEmptyMessage(2);
    }
}
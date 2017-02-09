package com.intercall.ThreadingSamples.HandlerThreads;


import android.content.SharedPreferences;
import android.os.*;
import android.os.Process;

public class SharedPrefsHandlerThread extends HandlerThread {

    private static final String KEY = "key";
    private SharedPreferences mPrefs;
    private static final int READ = 1;
    private static final int WRITE = 2;

    private Handler mHandler;

    public SharedPrefsHandlerThread(String name, SharedPreferences prefs) {
        super(name, Process.THREAD_PRIORITY_BACKGROUND);
        this.mPrefs = prefs;
    }


    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler(getLooper()) {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case READ:
                        //sendMessage();//(mPrefs.getInt(KEY, 0));
                        break;
                    case WRITE:
                        SharedPreferences.Editor editor = mPrefs.edit();
                        editor.putInt(KEY, (Integer) msg.obj);
                        editor.commit();
                        break;
                }
            }
        };
    }

    public void read() {
        mHandler.sendEmptyMessage(READ);
    }

    public void write(int i) {
        mHandler.sendMessage(Message.obtain(Message.obtain(mHandler,
                WRITE, i)));
    }
}

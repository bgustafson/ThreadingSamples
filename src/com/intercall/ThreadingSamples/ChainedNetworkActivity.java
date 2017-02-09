package com.intercall.ThreadingSamples;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.*;
import android.os.Process;


public class ChainedNetworkActivity extends Activity {

    private static final int DIALOG_LOADING = 0;
    private static final int SHOW_LOADING = 1;
    private static final int DISMISS_LOADING = 2;

    Handler dialogHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_LOADING:
                    showDialog(DIALOG_LOADING);
                    break;
                case DISMISS_LOADING:
                    dismissDialog(DIALOG_LOADING);
                    break;
            }
        }
    };

    private class NetworkHandlerThread extends HandlerThread {

        private static final int STATE_A = 1;
        private static final int STATE_B = 2;
        private Handler mHandler;

        public NetworkHandlerThread(String name) {
            super(name, Process.THREAD_PRIORITY_BACKGROUND);
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            mHandler = new Handler(getLooper()) {

                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    switch (msg.what) {
                        case STATE_A:
                            dialogHandler.sendEmptyMessage(SHOW_LOADING);
                            String result = networkOperation1();

                            if (result != null) {
                                sendMessage(obtainMessage(STATE_B, result));
                            } else {
                                dialogHandler.sendEmptyMessage(DISMISS_LOADING);
                            }
                            break;
                        case STATE_B:
                            networkOperation2((String) msg.obj);
                            dialogHandler.sendEmptyMessage(DISMISS_LOADING);
                            break;
                    }
                }
            };

            fetchDataFromNetwork();
        }

        private String networkOperation1() {
            SystemClock.sleep(2000);
            return "String A";
        }

        private void networkOperation2(String data) {
            SystemClock.sleep(2000);
        }

        public void fetchDataFromNetwork() {
            mHandler.sendEmptyMessage(STATE_A);
        }
    }

    private NetworkHandlerThread mThread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mThread = new NetworkHandlerThread("NetworkThread");
        mThread.start();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;

        switch (id) {
            case DIALOG_LOADING:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Loading...");
                dialog = builder.create();
                break;
        }

        return dialog;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThread.quit();
    }
}
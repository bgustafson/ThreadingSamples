package com.intercall.ThreadingSamples.AsyncTasks;

import android.os.AsyncTask;

public class InteruptionTask extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {

        try {
            while (!isCancelled()) {
                //Do something
            }
        } catch (Exception e) {
        }

        return null;
    }
}

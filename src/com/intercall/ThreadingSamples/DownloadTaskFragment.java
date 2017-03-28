package com.intercall.ThreadingSamples;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import com.intercall.ThreadingSamples.AsyncTasks.DownloadTask;


public class DownloadTaskFragment extends Fragment {

    public static final String TAG = "DOWNLOAD_FRAGMENT";

    private DownloadTask mAsyncTask = new DownloadTask();

    public DownloadTaskFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    public void download(String[] urls) {
        if(mAsyncTask.getStatus() == AsyncTask.Status.RUNNING) return;
        if(mAsyncTask.getStatus() == AsyncTask.Status.FINISHED) {
            mAsyncTask = new DownloadTask();
            mAsyncTask.setActivity((FileDownloadActivity) getActivity());
        }

        mAsyncTask.execute(urls);
    }


    public void stop() {
        if(mAsyncTask.getStatus() != AsyncTask.Status.RUNNING) return;
        mAsyncTask.cancel(true);
    }
}

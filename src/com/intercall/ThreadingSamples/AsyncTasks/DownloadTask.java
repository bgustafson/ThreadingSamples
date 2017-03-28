package com.intercall.ThreadingSamples.AsyncTasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import com.intercall.ThreadingSamples.FileDownloadActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class DownloadTask extends AsyncTask<String, Bitmap, Void> {

    private FileDownloadActivity mActivity;
    private int mCount = 1;

    public DownloadTask() {

    }

    public void setActivity(FileDownloadActivity activity) {
        mActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mActivity.mProgressBar.setVisibility(View.VISIBLE);
        mActivity.mProgressBar.setProgress(0);
    }

    @Override
    protected Void doInBackground(String... urls) {

        for (String url : urls) {
            if (!isCancelled()) {
                Bitmap bitmap = downloadFile(url);
                publishProgress(bitmap);
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Bitmap... bitmaps) {
        super.onProgressUpdate(bitmaps);

        if (mActivity != null) {
            mActivity.mProgressBar.setProgress(++mCount);
            ImageView iv = new ImageView(mActivity);
            iv.setImageBitmap(bitmaps[0]);
            mActivity.mLayoutImages.addView(iv);
        }
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);

        if (mActivity != null) {
            mActivity.mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        if (mActivity != null) {
            mActivity.mProgressBar.setVisibility(View.GONE);
        }
    }


    private Bitmap downloadFile(String url) {

        Bitmap bitmap = null;

        try {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}

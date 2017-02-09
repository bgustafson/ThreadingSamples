package com.intercall.ThreadingSamples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.intercall.ThreadingSamples.AsyncTasks.DownloadTask;


public class FileDownloadActivity extends Activity {

    private static final String[] DOWNLOAD_URLS = {
            "http://developer.android.com/design/media/devices_displays_density@2x.png",
            "http://developer.android.com/design/media/iconography_launcher_example2.png",
            "http://developer.android.com/design/media/iconography_actionbar_focal.png",
            "http://developer.android.com/design/media/iconography_actionbar_colors.png"
    };

    DownloadTask mFileDownloaderTask;

    //Views
    public ProgressBar mProgressBar;
    public LinearLayout mLayoutImages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_download);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setMax(DOWNLOAD_URLS.length);
        mLayoutImages = (LinearLayout) findViewById(R.id.imageLayout);


        FileDownloadActivity a = this;
        mFileDownloaderTask = new DownloadTask(a);
        mFileDownloaderTask.execute(DOWNLOAD_URLS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFileDownloaderTask.setActivity(null);
        mFileDownloaderTask.cancel(true);
    }
}
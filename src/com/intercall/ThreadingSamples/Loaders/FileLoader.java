package com.intercall.ThreadingSamples.Loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.FileObserver;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileLoader extends AsyncTaskLoader<List<String>> {

    private List<String> mFileNames;
    private SdCardObserver mSdCardObserver;

    public FileLoader(Context context) {
        super(context);
        String path = context.getFilesDir().getPath();
        mSdCardObserver = new SdCardObserver(path);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        mSdCardObserver.startWatching();

        if (mFileNames != null) {
            deliverResult(mFileNames);
        }

        if (mFileNames == null || takeContentChanged()) {
            forceLoad();
        }
    }

    @Override
    public List<String> loadInBackground() {
        File directory = getContext().getFilesDir();
        return Arrays.asList(directory.list());
    }

    @Override
    public void deliverResult(List<String> data) {
        if (isReset()) {
            return;
        }
        // Cache the data
        mFileNames = data;
        // Only deliver result if the loader is started.
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        mSdCardObserver.stopWatching();
        clearResources();
    }

    private void clearResources() {
        mFileNames = null;
    }


    //Internal Classes
    private class SdCardObserver extends FileObserver {

        public SdCardObserver(String path) {
            super(path, FileObserver.CREATE | FileObserver.DELETE);
        }

        @Override
        public void onEvent(int event, String path) {
            onContentChanged();
        }
    }

}

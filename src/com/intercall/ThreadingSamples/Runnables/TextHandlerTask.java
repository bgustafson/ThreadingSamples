package com.intercall.ThreadingSamples.Runnables;


import android.util.Log;

import java.io.IOException;
import java.io.PipedReader;

public class TextHandlerTask implements Runnable {

    private final PipedReader reader;
    private static final String TAG = "PipeExampleActivity";

    public TextHandlerTask(PipedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        try {
            int i;
            while ((i = reader.read()) != -1) {
                char c = (char) i;
                Log.d(TAG, "char = " + c);
            }
        } catch (IOException e) {

        }
    }
}

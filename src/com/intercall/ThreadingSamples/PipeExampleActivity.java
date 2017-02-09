package com.intercall.ThreadingSamples;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.intercall.ThreadingSamples.Runnables.TextHandlerTask;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipeExampleActivity extends Activity {

    private EditText editText;

    PipedReader r;
    PipedWriter w;

    private Thread workerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setup and connect the writer and reader
        r = new PipedReader();
        w = new PipedWriter();

        try {
            w.connect(r);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.pipeactivity_layout);
        editText = (EditText) findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    //only handle the addition of characters
                    if (count > before) {
                        //Write the last character entered to the pipe
                        w.write(s.subSequence(before, count).toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        workerThread = new Thread(new TextHandlerTask(r));
        workerThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        workerThread.interrupt();
        try {
            r.close();
            w.close();
        } catch (IOException e) {

        }
    }
}

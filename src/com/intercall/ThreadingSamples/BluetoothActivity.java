package com.intercall.ThreadingSamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.intercall.ThreadingSamples.Services.BluetoothService;


public class BluetoothActivity extends Activity {

    private Button startBtn;
    private Button stopBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_layout);

        startBtn = (Button) findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BluetoothService.class);
                intent.putExtra(BluetoothService.COMMAND_KEY,
                        BluetoothService.COMMAND_START_LISTENING);
                startService(intent);
            }
        });

        stopBtn = (Button) findViewById(R.id.stop);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BluetoothService.class);
                stopService(intent);
            }
        });
    }

}
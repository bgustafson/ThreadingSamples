package com.intercall.ThreadingSamples;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class StartButtonOnClickListner implements View.OnClickListener {

    @Override
    public void onClick(View v) {

        Context context = v.getContext();
        Toast.makeText(context, "This is a sample", Toast.LENGTH_LONG).show();

        //By accessing the context from the view you can move a lot of button click code
        //out of the activity class.

        //Start the new activity
        Intent myIntent = new Intent(context, BluetoothActivity.class);//FileDownloadActivity.class);//ChainedNetworkActivity.class);//, PipeExampleActivity.class);
        context.startActivity(myIntent);

    }
}

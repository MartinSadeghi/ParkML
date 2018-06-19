package com.example.mohammadrezasadeghi.parkml;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import hrituc.studenti.uniroma1.it.generocityframework.Constants;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("RECEIVER", "------------------RECEIVED NULL--------------");
        if (intent != null) {
            String action = intent.getAction();
            if (action.equals( Constants.TRIP_POINT)) {
                Log.e("RECEIVER", "------------------RECEIVED TRIPPOINT--------------");
                StringBuilder sb = new StringBuilder();
                sb.append("Action: " + intent.getAction() + "\n");
                sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
                String log = sb.toString();
                Log.d(TAG, log);
                Toast.makeText(context, log, Toast.LENGTH_LONG).show();
            }
        }
    }
}
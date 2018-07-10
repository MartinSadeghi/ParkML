package com.example.mohammadrezasadeghi.parkml;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import hrituc.studenti.uniroma1.it.generocityframework.Constants;
import hrituc.studenti.uniroma1.it.generocityframework.FrameworkInitializer;
import hrituc.studenti.uniroma1.it.generocityframework.FrameworkLogic;

public class HomeActivity extends AppCompatActivity {
    private Button button2;
    private BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        br = new FunctionCallsReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.TRIP_POINT);
        this.registerReceiver(br, filter);
        StartListener();

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(br);
    }

    public void openDialog3() {
        Dialog3 dialog = new Dialog3();
        dialog.show(getSupportFragmentManager(), "Example one");
        unregisterReceiver(br);
    }


    public void StartListener() {
        FrameworkInitializer fi = new FrameworkInitializer(this);
        fi.start();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("values", Context.MODE_PRIVATE);
        boolean isInCar = sharedPreferences.getBoolean("is_in_car", false);
        if (!isInCar) {
            Intent didEnterCarIntent = new Intent(this, FrameworkLogic.class);
            didEnterCarIntent.setAction("BLUETOOTH_IN_VEHICLE");
            PendingIntent pendingIntent = PendingIntent.getService(this, 123, didEnterCarIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException ex) {
                ex.printStackTrace();
            }
        } else {
            Intent didExitCarIntent = new Intent(this, FrameworkLogic.class);
            didExitCarIntent.setAction("BLUETOOTH_NOT_IN_VEHICLE");
            PendingIntent pendingIntent = PendingIntent.getService(this, 123, didExitCarIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException ex) {
                ex.printStackTrace();
            }

        }
    }

}



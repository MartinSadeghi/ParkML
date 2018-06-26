package com.example.mohammadrezasadeghi.parkml;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;

import hrituc.studenti.uniroma1.it.generocityframework.FrameworkInitializer;
import hrituc.studenti.uniroma1.it.generocityframework.FrameworkLogic;

public class HomeActivity extends AppCompatActivity {
    private Button button2 ;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button2 = (Button) findViewById( R.id.button2 );
        button2.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openDialog1();
            }
        } );

        button1 =(Button) findViewById( R.id.button1 );
        button1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog2();
                StartListener( v);
            }
        } );

    }

    public void openDialog1() {
        Dialog1 dialog = new Dialog1();
        dialog.show( getSupportFragmentManager(), "Example one" );

    }
    public void openDialog2() {
        Dialog2 dialog = new Dialog2();
        dialog.show( getSupportFragmentManager(), "Example one" );

    }




    public void StartListener(View view) {
        FrameworkInitializer fi = new FrameworkInitializer( this );
        fi.start();

            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("values", Context.MODE_PRIVATE);
            boolean isInCar = sharedPreferences.getBoolean("is_in_car",false);
            if (!isInCar) {
                Intent didEnterCarIntent = new Intent(this,FrameworkLogic.class);
                didEnterCarIntent.setAction("BLUETOOTH_IN_VEHICLE");
                PendingIntent pendingIntent = PendingIntent.getService(this,123,didEnterCarIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException ex) {
                    ex.printStackTrace();
                }
            } else {
                Intent didExitCarIntent = new Intent(this,FrameworkLogic.class);
                didExitCarIntent.setAction("BLUETOOTH_NOT_IN_VEHICLE");
                PendingIntent pendingIntent = PendingIntent.getService(this,123,didExitCarIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException ex) {
                    ex.printStackTrace();
                }

        }
    }
}



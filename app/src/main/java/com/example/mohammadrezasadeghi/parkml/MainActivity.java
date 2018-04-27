package com.example.mohammadrezasadeghi.parkml;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4004;
    //Button mapbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        new Handler(  ).postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent homeintent = new Intent( MainActivity.this, HomeActivity.class );

                startActivity( homeintent );
                finish();
            }
        },SPLASH_TIME_OUT );

}
    }
       /* @Override
        public void onClick (View view){
            Intent i = getPackageManager().getLaunchIntentForPackage();
            startActivity( i );
        }
        mapbtn = findViewById( R.id.mapbtn );
        mapbtn.setOnClickListener( new View.OnClickListener() {

                newHandler().postDelayed( new Runnable() {
                @Override
                public void run () {

        }

    }

    /*public void process(View view) {
        Intent intent = null, chooser = null;
        if (view.getId() == R.id.mapbtn) {
            intent = new Intent( Intent.ACTION_VIEW );
            intent.setData( Uri.parse( "geo:19.067,72.877" ) );
            chooser = Intent.createChooser( intent, "GOOGLE MAPS" );
        }
            startActivity( chooser );
        }*/


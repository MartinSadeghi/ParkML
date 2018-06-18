package com.example.mohammadrezasadeghi.parkml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private Button button2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button2 = (Button) findViewById( R.id.button2 );
        button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        } );
    }

    public void openDialog() {
        Dialog1 dialog = new Dialog1();
        dialog.show( getSupportFragmentManager(), "Example one" );
    }
    }



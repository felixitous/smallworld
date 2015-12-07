package com.myapps.materialapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ComplicatedProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complicated_profile);
        final Button button = (Button) findViewById(R.id.locationbutton);
        button.setText("poop");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "hello", Toast.LENGTH_LONG);
                mapsWindow();
            }
        });

    }
    public void mapsWindow() {
        Intent intent = new Intent(this, Destination.class);
        startActivity(intent);
    }
}

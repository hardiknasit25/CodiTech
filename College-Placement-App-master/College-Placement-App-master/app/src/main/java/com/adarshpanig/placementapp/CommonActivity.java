package com.adarshpanig.placementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CommonActivity extends AppCompatActivity {

    TextView titletext,descriptiontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        titletext=findViewById(R.id.titletext);
        descriptiontext=findViewById(R.id.descriptiontext);

        Intent intent = getIntent();
        String t=intent.getStringExtra("titletext1");
        String d=intent.getStringExtra("descriptiontext1");

        titletext.setText(t.toString());
        descriptiontext.setText(d.toString());
    }
}

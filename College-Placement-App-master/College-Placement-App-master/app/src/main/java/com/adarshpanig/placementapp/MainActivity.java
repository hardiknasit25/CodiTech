package com.adarshpanig.placementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView title;
    Button adminbtn,topbtn,studentbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    public void AdminClicked(View view) {
        Intent intent1 = new Intent(MainActivity.this,AdminLogin.class);
        startActivity(intent1);
    }

    public void TpoClicked(View view) {
        Intent i7 = new Intent(MainActivity.this,TPOLogin.class);
        startActivity(i7);
    }

    public void StudentClicked(View view) {
        Intent i8 = new Intent(MainActivity.this,StudentLogin.class);
        startActivity(i8);
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }

    public void LogoutTPO(View view) {
    }

}

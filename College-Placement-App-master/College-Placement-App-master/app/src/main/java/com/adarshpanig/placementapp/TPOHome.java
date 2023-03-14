package com.adarshpanig.placementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TPOHome extends AppCompatActivity {
//     Button addcompay,addnotif,previouspapers,selectedstudents;
//     TextView welcometpo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpohome);
//        addcompay=findViewById(R.id.addcompany);
//        addnotif= findViewById(R.id.addnotif);
//        previouspapers=findViewById(R.id.previouspapers);
//        selectedstudents=findViewById(R.id.selectedstudents);
    }

    public void AddCompany(View view) {
        Intent i2 = new Intent(TPOHome.this, AddingCompany.class);
           startActivity(i2);
    }

    public void AddNotification(View view) {
    }

    public void PreviousPapers(View view) {
        Intent i2 = new Intent(TPOHome.this, AddingPapers.class);
        startActivity(i2);
    }

    public void SelectedStudents(View view) {
        Intent i2 = new Intent(TPOHome.this, AddingSelected.class);
        startActivity(i2);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i= new Intent(TPOHome.this,MainActivity.class);
        startActivity(i);
    }

    public void LogoutTPO(View view) {
        Intent i= new Intent(TPOHome.this,MainActivity.class);
        startActivity(i);
    }
}

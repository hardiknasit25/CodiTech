package com.example.placementmanagementsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class company_main_page extends AppCompatActivity {
    CardView addjob,sprofile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_main_page);

        addjob = findViewById(R.id.addjob);
        sprofile = findViewById(R.id.sprofile);

        addjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(company_main_page.this,add_job.class);
                startActivity(intent);
            }
        });

        sprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(company_main_page.this,company_student_profile.class);
                startActivity(intent);
            }
        });
    }
}
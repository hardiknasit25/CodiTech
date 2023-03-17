package com.example.placementmanagementsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Select_Profile extends AppCompatActivity {
    private CardView student,company;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_profile);

        student = findViewById(R.id.student);
        company = findViewById(R.id.company);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Select_Profile.this, student_profile.class);
                startActivity(intent);
                finish();
            }
        });

        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Select_Profile.this, company_profile.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
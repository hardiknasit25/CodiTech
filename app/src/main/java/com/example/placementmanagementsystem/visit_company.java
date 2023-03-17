package com.example.placementmanagementsystem;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class visit_company extends AppCompatActivity {

    RecyclerView recycle;
    ArrayList<AddjobModel> arrJob;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_company);

        recycle = findViewById(R.id.recycle);
        arrJob = new ArrayList<>();
        arrJob.clear();

        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(new StudentCompanyAdapter(this,arrJob));

    }
}
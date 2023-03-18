package com.example.placementmanagementsystem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class company_student_profile extends AppCompatActivity {

    RecyclerView recycle;
    ArrayList<StudentModel> arrStudent;
    FirebaseFirestore db;
    Button search;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_student_profile);

        recycle = findViewById(R.id.recycle);
        search = findViewById(R.id.search);
        db = FirebaseFirestore.getInstance();
        arrStudent = new ArrayList<>();
        arrStudent.clear();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("user").addSnapshotListener(new EventListener<QuerySnapshot>() {

                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error == null)
                        {
                            List<StudentModel> data = value.toObjects(StudentModel.class);
                            arrStudent.addAll(data);
                            recycle.setLayoutManager(new LinearLayoutManager(company_student_profile.this));
                            recycle.setAdapter(new RecycleProfileAdapter(company_student_profile.this,arrStudent));
//                            recycle.setVisibility(View.VISIBLE);
                        }

                    }
                });

            }
        });
    }
}


package com.example.placementmanagementsystem;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class company_student_profile extends AppCompatActivity {

    RecyclerView recycle;
    ArrayList<StudentModel> arrStudent;
    FirebaseFirestore db;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_student_profile);

        text = findViewById(R.id.text);
        recycle = findViewById(R.id.recycle);
        db = FirebaseFirestore.getInstance();
//        recycle.setLayoutManager(new LinearLayoutManager(this));
        getAllStudentData();

    }

    private void getAllStudentData() {
        RecycleProfileAdapter RecycleProfileAdapter = new RecycleProfileAdapter(company_student_profile.this,arrStudent);

        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(RecycleProfileAdapter);
    }
}

//    public void getAllStudentData() {
//        arrStudent = new ArrayList<>();
//        arrStudent.clear();

//        db.collection("user").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                if (error == null) {
//                    List<StudentModel> data = value.toObjects(StudentModel.class);
//                    arrStudent.addAll(data);
//                    recycle.setLayoutManager(new LinearLayoutManager(company_student_profile.this));
//                    recycle.setAdapter(new RecycleProfileAdapter(company_student_profile.this, arrStudent));
//
//                    if (arrStudent.size() > 0) {
//                        recycle.setVisibility(View.VISIBLE);
//                        text.setVisibility(View.GONE);
//                        recycle.setAdapter(new RecycleProfileAdapter(company_student_profile.this, arrStudent));
//
//                    } else {
//                        text.setVisibility(View.VISIBLE);
//                        recycle.setVisibility(View.GONE);
//                    }
//
//                }
//
//            }
//        });

//        }catch (Exception e){
//            Toast.makeText(this, " " + e, Toast.LENGTH_SHORT).show();
//        }

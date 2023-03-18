package com.example.placementmanagementsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

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

public class visit_company extends AppCompatActivity {

    RecyclerView recycle;
    ArrayList<AddjobModel> arrJob;
    FirebaseFirestore db;
    SearchView searchView;
    ImageView linkedin;
    Button search;

//    RecyclerView.Adapter adapter = new Adapter();


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_company);

        searchView = findViewById(R.id.searchView);
        search = findViewById(R.id.search);
        recycle = findViewById(R.id.recycle);
        db = FirebaseFirestore.getInstance();
        arrJob = new ArrayList<>();
//        arrname = new ArrayList<>();
        arrJob.clear();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("job").addSnapshotListener(new EventListener<QuerySnapshot>() {

                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error == null)
                        {
                            List<AddjobModel> data = value.toObjects(AddjobModel.class);
                            arrJob.addAll(data);
                            recycle.setLayoutManager(new LinearLayoutManager(visit_company.this));
                            recycle.setAdapter(new StudentCompanyAdapter(visit_company.this,arrJob));
//                            recycle.setVisibility(View.VISIBLE);
                        }

                    }
                });

            }
        });

        linkedin = findViewById(R.id.linkedin);
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(visit_company.this, Web_View_linkedin.class);
                startActivity(intent);
            }
        });

    }
}
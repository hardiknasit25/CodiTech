package com.example.placementmanagementsystem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_company);

        recycle = findViewById(R.id.recycle);
        db = FirebaseFirestore.getInstance();
        getAllCompanyData();

//        recycle.setLayoutManager(new LinearLayoutManager(this));
//        recycle.setAdapter(new StudentCompanyAdapter(this,arrJob));

        public void getAllCompanyData(){

            arrJob = new ArrayList<>();
            arrJob.clear();
            db.collection("job").addSnapshotListener(new EventListener<QuerySnapshot>() {

                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (error == null){
                        List<AddjobModel> data = value.toObjects(AddjobModel.class);
                        arrJob.addAll(data);
                        recycle.setLayoutManager(new LinearLayoutManager(visit_company.this));
                        recycle.setAdapter(new RecyclerAddjobAdapter(visit_company.this,arrJob));

                        if(arrJob.size()>0){
                            recycle.setVisibility(View.VISIBLE);
                            recycle.setAdapter(new RecyclerAddjobAdapter(visit_company.this, arrJob));

                        }
                        else{
                            recycle.setVisibility(View.GONE);
                        }

                    }

                }
            });



    }
}
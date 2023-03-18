package com.example.placementmanagementsystem;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class add_job extends AppCompatActivity {

    Button add;
    Button plus;
    RecyclerView recycle;

    FirebaseFirestore db;

    ArrayList<AddjobModel> arrJob ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        add = findViewById(R.id.add);
        plus = findViewById(R.id.plus);
        recycle = findViewById(R.id.recycle);

//        getAllData();
        plus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(add_job.this);
                dialog.setContentView(R.layout.addjob);

                EditText date,packag,cgpa,skill;
                Button job;

                date = dialog.findViewById(R.id.date);
                packag = dialog.findViewById(R.id.packag);
                cgpa = dialog.findViewById(R.id.cgpa);
                skill = dialog.findViewById(R.id.skill);
                job = dialog.findViewById(R.id.job);
                db = FirebaseFirestore.getInstance();

                job.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String adate = date.getText().toString();
                        String apackag = packag.getText().toString();
                        String acgpa = cgpa.getText().toString();
                        String askill = skill.getText().toString();

                        AddjobModel data = new AddjobModel(adate,apackag,acgpa,askill);

                        if((!adate.equals("")) || (!apackag.equals("")) || (!acgpa.equals("")) || (!askill.equals(""))){

                            db.collection("job").document().set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(add_job.this, "job Enter Successfuly", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(add_job.this,student_main_page.class);
//                                        startActivity(intent);
//                                        finish();
//                                        getAllData();
                                    }else{
                                        Toast.makeText(add_job.this, "Please Fill Data Properly", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                            dialog.dismiss();

                        }else{
                            Toast.makeText(add_job.this, "Please Fill Detail Properly !", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                getAllData();
                dialog.show();

            }

        });
//        getAllData();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plus.performClick();
            }
        });

    }

        public void getAllData(){

        arrJob = new ArrayList<>();
        arrJob.clear();
        db.collection("job").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null){
                    List<AddjobModel> data = value.toObjects(AddjobModel.class);
                    arrJob.addAll(data);
                    recycle.setLayoutManager(new LinearLayoutManager(add_job.this));
                    recycle.setAdapter(new RecyclerAddjobAdapter(add_job.this,arrJob));


                    if(arrJob.size()>0){
                        recycle.setVisibility(View.VISIBLE);
                        add.setVisibility(View.GONE);
//                        recycle.setAdapter(new RecyclerAddjobAdapter(add_job.this, arrJob));

                    }
                    else{
                        add.setVisibility(View.VISIBLE);
                        recycle.setVisibility(View.GONE);
                    }

                }

            }
        });

        }

}
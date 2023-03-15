package com.example.placementmanagementsystem;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class add_job extends AppCompatActivity {

    Button add;
    FloatingActionButton plus;
    RecyclerView recycle;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        add = findViewById(R.id.add);
        plus = findViewById(R.id.plus);
        recycle = findViewById(R.id.recycle);

        recycle.setLayoutManager(new GridLayoutManager(this,1));

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

//                showNotes();


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
                                        Toast.makeText(add_job.this, "Enter job", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(add_job.this,student_main_page.class);
//                                        startActivity(intent);
//                                        finish();
                                    }else{
                                        Toast.makeText(add_job.this, "Please Fill Data Properly", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

//                            showNotes();
                            dialog.dismiss();

                        }else{
                            Toast.makeText(add_job.this, "Please Fill Detail Properly !", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                dialog.show();

            }

        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plus.performClick();
            }
        });

    }

//    private void showNotes() {
//            ArrayList<Note> arrNotes = (ArrayList<Note>) db.collection("job").document().getFirestore();
//
//            if(arrNotes.size()>0){
//                recycle.setVisibility(View.VISIBLE);
//                add.setVisibility(View.GONE);
//
//            }else{
//                add.setVisibility(View.VISIBLE);
//                recycle.setVisibility(View.GONE);
//            }
//
//    }


}
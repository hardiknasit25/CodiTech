package com.example.placementmanagementsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class student_profile extends AppCompatActivity {
    Button save;
    EditText name,email,number,collage,branch,marks,graduation,skill;
    TextView add;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        save = findViewById(R.id.save);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        number = findViewById(R.id.number);
        collage = findViewById(R.id.collage);
        branch = findViewById(R.id.branch);
        marks = findViewById(R.id.marks);
        graduation = findViewById(R.id.graduation);
        skill = findViewById(R.id.skill);
        add = findViewById(R.id.add);

        db = FirebaseFirestore.getInstance();

        save. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sname = name.getText().toString();
                String semail = email.getText().toString();
                String snumber = number.getText().toString();
                String scollage = collage.getText().toString();
                String sbranch = branch.getText().toString();
                String smarks = marks.getText().toString();
                String sgraduation = graduation.getText().toString();
                String sskill = skill.getText().toString();

                StudentModel data = new StudentModel(sname,semail,snumber,scollage,sbranch,smarks,sgraduation,sskill);

                Boolean flag = true;

                if (TextUtils.isEmpty(sname)){
                    Toast.makeText(student_profile.this, "Please Enter company name", Toast.LENGTH_SHORT).show();
//                            flag = false;
                    return;
                }

                if (TextUtils.isEmpty(semail)){
                    Toast.makeText(student_profile.this, "Please Enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(snumber) || snumber.length() < 10){
                    Toast.makeText(student_profile.this, "Please Enter number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(scollage)){
                    Toast.makeText(student_profile.this, "Please Enter collage", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(sbranch)){
                    Toast.makeText(student_profile.this, "Please Enter branch name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(smarks)){
                    Toast.makeText(student_profile.this, "Please Enter marks", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(sgraduation)){
                    Toast.makeText(student_profile.this, "Please Enter graduation year", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(sskill)){
                    Toast.makeText(student_profile.this, "Please Enter skills", Toast.LENGTH_SHORT).show();
                    return;
                }

                else {
                    
                    db.collection("user").document().set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()) {
                                Toast.makeText(student_profile.this, "Profile Make Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(student_profile.this, student_main_page.class);
//                                Intent intent = new Intent(student_profile.this, company_student_profile.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(student_profile.this, "Please fill data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }
}
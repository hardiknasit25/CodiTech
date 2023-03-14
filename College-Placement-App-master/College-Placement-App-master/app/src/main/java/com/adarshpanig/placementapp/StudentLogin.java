package com.adarshpanig.placementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentLogin extends AppCompatActivity {

    EditText studentidL,studentpasswordL;
    Button studentlogin;
    String sname1="";
    FirebaseDatabase database;
    DatabaseReference students;
    public final static String EXTRA_MESSAGE="" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        studentidL=findViewById(R.id.studentidL);
        studentpasswordL=findViewById(R.id.studentpasswordL);
        studentlogin=findViewById(R.id.studentlogin);

        database= FirebaseDatabase.getInstance();
        students=database.getReference("STUDENTS");
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent i= new Intent(StudentLogin.this,MainActivity.class);
        startActivity(i);
    }

    public void StudentLoggedIn(View view) {
        final String sname2 = studentidL.getText().toString().trim(), spwd2 = studentpasswordL.getText().toString().trim();

        students.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(sname2).exists()) {
                    if (!sname2.isEmpty()) {
                        if (!spwd2.isEmpty()) {
                            Student s1 = dataSnapshot.child(sname2).getValue(Student.class);
                            if (s1.getSid().equals(sname2)) {
                                if (s1.getSpassword().equals(spwd2)) {
                                    sname1 = s1.getSname();
                                    Intent intent = new Intent(StudentLogin.this, StudentHome.class);
                                    intent.putExtra(EXTRA_MESSAGE, sname1);
                                    startActivity(intent);
                                    Toast.makeText(StudentLogin.this, "Login Successful", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(StudentLogin.this, "Wrong Password", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(StudentLogin.this, "Wrong TPO ID", Toast.LENGTH_LONG).show();
                            }
                        } else
                            Toast.makeText(StudentLogin.this, "Password required", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(StudentLogin.this, "Student ID required", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

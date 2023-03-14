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

public class AddingSelected extends AppCompatActivity {

    EditText editTextId,editTextCompany;
    Button buttonAdd;
    DatabaseReference students,selecteds;
    Student s2;
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_selected);
        editTextId=findViewById(R.id.editTextId);
        editTextCompany=findViewById(R.id.editTextCompany);
        buttonAdd=findViewById(R.id.buttonAdd);

        students=FirebaseDatabase.getInstance().getReference("STUDENTS");
        selecteds=FirebaseDatabase.getInstance().getReference("SELECTED STUDENTS");

    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i= new Intent(AddingSelected.this,TPOHome.class);
        startActivity(i);
    }
    public void AddSelected(View view) {

         final String SstuId=editTextId.getText().toString().trim();
         final String Scom=editTextCompany.getText().toString().trim();
        students.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!SstuId.isEmpty()){
                    if(!Scom.isEmpty()){
                        if(dataSnapshot.child(SstuId).exists()){
                          final Student s3 =dataSnapshot.child(SstuId).getValue(Student.class);
                            selecteds.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.child(s3.getSid()).exists()){
                                        Toast.makeText(AddingSelected.this,"Student already selected",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        s3.setCompany(Scom);
                                        selecteds.child(s3.getSid()).setValue(s3);
                                        Toast.makeText(AddingSelected.this,"Student added",Toast.LENGTH_SHORT).show();
                                        editTextId.setText("");
                                        editTextCompany.setText("");
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                        else
                            Toast.makeText(AddingSelected.this,"No such Student found",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(AddingSelected.this,"Company Name is required",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(AddingSelected.this,"Student ID is required",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

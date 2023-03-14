package com.adarshpanig.placementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class  AddingNewTPO extends AppCompatActivity {
    EditText tponame,tpopassword,tpoid;
    String id,name,password;
    Button tpoadd;
    DatabaseReference tpos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_new_tpo);
        tponame=findViewById(R.id.tponame);
        tpopassword=findViewById(R.id.tpopassword);
        tpoid=findViewById(R.id.tpoid);
        tpoadd=findViewById(R.id.tpoadd);
        tpos=FirebaseDatabase.getInstance().getReference("TPOS");

    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i= new Intent(AddingNewTPO.this,AdminHome.class);
        startActivity(i);
    }
    public void TpoAdded(View view) {

        id=tpoid.getText().toString().trim();
        name=tponame.getText().toString().trim();
        password=tpopassword.getText().toString().trim();
        if(id!="" && name!="" && password!="") {

            final Tpo t = new Tpo(name,password,id);
            tpos.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(t.getTid()).exists()){
                        Toast.makeText(AddingNewTPO.this, "TPO Already exists ", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        tpos.child(t.getTid()).setValue(t);
                        Toast.makeText(AddingNewTPO.this, "TPO Added", Toast.LENGTH_SHORT).show();
                        Intent i1 = new Intent(AddingNewTPO.this, AdminHome.class);
                        startActivity(i1);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{
            Toast.makeText(this,"All Fields required",Toast.LENGTH_SHORT).show();
        }
    }
}

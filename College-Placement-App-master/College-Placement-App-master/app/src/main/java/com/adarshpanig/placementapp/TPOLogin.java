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

public class TPOLogin extends AppCompatActivity {

    EditText tpoidL,tpopasswordL;
    Button tpologin;
    String tname1="";
    FirebaseDatabase database;
    DatabaseReference tpos;
    public final static String EXTRA_MESSAGE="" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpologin);
        tpoidL=findViewById(R.id.tpoidL);
        tpopasswordL=findViewById(R.id.tpopasswordL);
        tpologin=findViewById(R.id.tpologin);

        database= FirebaseDatabase.getInstance();
        tpos=database.getReference("TPOS");
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i= new Intent(TPOLogin.this,MainActivity.class);
        startActivity(i);
    }
    public void TPOLoggedIn(View view) {
        final String tname2 = tpoidL.getText().toString().trim(), tpwd2 = tpopasswordL.getText().toString().trim();

        tpos.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(tname2).exists()) {
                    if (!tname2.isEmpty()) {
                        if (!tpwd2.isEmpty()) {
                            Tpo t1 = dataSnapshot.child(tname2).getValue(Tpo.class);
                            if (t1.getTid().equals(tname2)) {
                                if (t1.getTpassword().equals(tpwd2)) {
                                    tname1 = t1.getTname();
                                    Intent intent = new Intent(TPOLogin.this, TPOHome.class);
                                    intent.putExtra(EXTRA_MESSAGE, tname1);
                                    startActivity(intent);
                                    Toast.makeText(TPOLogin.this, "Login Sucessfull", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(TPOLogin.this, "Wrong Password", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(TPOLogin.this, "Wrong TPO ID", Toast.LENGTH_LONG).show();
                            }
                        } else
                            Toast.makeText(TPOLogin.this, "Password required", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(TPOLogin.this, "TPO ID required", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    }


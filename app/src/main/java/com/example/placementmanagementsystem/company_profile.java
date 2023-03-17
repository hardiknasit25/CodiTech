package com.example.placementmanagementsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class company_profile extends AppCompatActivity {

    Button save;
    EditText name,email,number,city;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);

        save = findViewById(R.id.save);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        number = findViewById(R.id.number);
        city = findViewById(R.id.city);

        db = FirebaseFirestore.getInstance();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                String cname = name.getText().toString();
                String cemail = email.getText().toString();
                String cnumber = number.getText().toString();
                String ccity = city.getText().toString();
                
                ComanyModel data = new ComanyModel(cname, cemail, cnumber, ccity);
                
                db.collection("company").document().set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("intentLog", "On Complete accessed.");

                        if (TextUtils.isEmpty(cname)){
                            Toast.makeText(company_profile.this, "Please Enter company name", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(cemail)){
                            Toast.makeText(company_profile.this, "Please Enter email address", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(cnumber) || cnumber.length() < 10){
                            Toast.makeText(company_profile.this, "Please Enter number", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(ccity)){
                            Toast.makeText(company_profile.this, "Please Enter location", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        else{
                            Log.d("intentLog", "Else condition accessed.");
                            Toast.makeText(company_profile.this, "Profile Make Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(company_profile.this, company_main_page.class);
                            startActivity(intent);
                            finish();

                        }
                        
                    }
                });
            }
        });
    }
}
package com.adarshpanig.placementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLogin extends AppCompatActivity {

    EditText adminname,adminpassword;
    Button adminlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        adminname=findViewById(R.id.adminname);
        adminpassword=findViewById(R.id.adminpassword);
        adminlogin=findViewById(R.id.adminlogin);

    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i= new Intent(AdminLogin.this,MainActivity.class);
        startActivity(i);
    }
    public void AdminLoggedIn(View view) {
        String adname="Adarsh",adpas="bestadmin";
        if((adminname.getText().toString().equals(adname))&& ( adminpassword.getText().toString().equals(adpas))){
            Intent intent2 = new Intent(AdminLogin.this,AdminHome.class);
            startActivity(intent2);
        }
        else {
            Toast.makeText(this,"Wrong Admin Name or Password !! ",Toast.LENGTH_SHORT).show();
        }
    }

}

 package com.adarshpanig.placementapp;

 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;

 import androidx.appcompat.app.AppCompatActivity;

 public class AdminHome extends AppCompatActivity {
    Button addtpobtn,addstudentbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        addtpobtn=findViewById(R.id.addtpobtn);
        addstudentbtn=findViewById(R.id.addstudentbtn);
    }
     public void onBackPressed() {
         super.onBackPressed();
         Intent i= new Intent(AdminHome.this,MainActivity.class);
         startActivity(i);
     }
     public void AddTpo(View view) {
         Intent intent5 = new Intent(AdminHome.this,AddingNewTPO.class);
         startActivity(intent5);
     }

     public void AddStudent(View view) {
         Intent intent6 = new Intent(AdminHome.this,AddingNewStudent.class);
         startActivity(intent6);
     }

     public void LogoutAdmin(View view) {
         Intent intent7 = new Intent(AdminHome.this,MainActivity.class);
         startActivity(intent7);
     }
 }

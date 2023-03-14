package com.adarshpanig.placementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddingPapers extends AppCompatActivity {

    public static final int PICK_IMAGE = 2;
    public Uri paperUri;
    EditText editTextPaper,editTextTitle;
    Button browsebutton,addbutton;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_papers);

        editTextPaper=findViewById(R.id.editTextPaper);
        browsebutton=findViewById(R.id.browsebutton);
        addbutton=findViewById(R.id.addbutton);
        editTextTitle=findViewById(R.id.editTextTitle);

        storageReference= FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference("uploadPDF");
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i= new Intent(AddingPapers.this,TPOHome.class);
        startActivity(i);
    }
    public void BrowsePDF(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select text file"), PICK_IMAGE);
        } catch (android.content.ActivityNotFoundException ex) {
           ex.printStackTrace();

        }
    }

    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        if (requestcode == PICK_IMAGE && resultcode == RESULT_OK) {
            paperUri=data.getData();
           editTextPaper.setText(paperUri.toString());
        }
    }

    public void AddPdf(View view) {
        if(paperUri!=null) {
            final ProgressDialog progressDialog= new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
           StorageReference reference= storageReference.child("uploadPDF/"+System.currentTimeMillis()+".pdf");
           reference.putFile(paperUri)
                   .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           Task<Uri> uri =taskSnapshot.getStorage().getDownloadUrl();
                           while (!uri.isComplete());
                           Uri url=uri.getResult();

                           uploadPDF uploadPDF= new uploadPDF(editTextTitle.getText().toString().trim(),url.toString());
                           databaseReference.child(databaseReference.push().getKey()).setValue(uploadPDF);
                           Toast.makeText(AddingPapers.this,"File Uploaded",Toast.LENGTH_SHORT).show();
                           progressDialog.dismiss();
                           editTextTitle.setText("");
                           editTextPaper.setText("");
                       }
                   }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                   double progress =(100.0 *snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                   progressDialog.setMessage("Uploaded  "+(int)progress+" %");
               }
           });
        }
        else
            Toast.makeText(AddingPapers.this,"Fields cannot be Empty",Toast.LENGTH_SHORT).show();
    }
}

package com.adarshpanig.placementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class AddingCompany extends AppCompatActivity {

    ImageView imageView;
    Button choosebutton;
    EditText editTextTitle, editTextDescription;
    public static final int PICK_IMAGE = 1;
    Uri imageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_company_first);
        imageView = findViewById(R.id.imageView);
        choosebutton = findViewById(R.id.choosebutton);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);

        mStorageRef= FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("uploads");

    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent i= new Intent(AddingCompany.this,TPOHome.class);
        startActivity(i);
    }
   public void ChooseImage(View view) {

        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture"),PICK_IMAGE);
   }

    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        if (requestcode == PICK_IMAGE && resultcode == RESULT_OK) {
            imageUri=data.getData();
            imageView.setImageURI(imageUri);
        }
    }


    public void TickClicked(View view) {
        String title=editTextTitle.getText().toString();
        String description=editTextDescription.getText().toString();
        Toast.makeText(this,""+title+description,Toast.LENGTH_SHORT).show();
        if(mUploadTask!= null && mUploadTask.isInProgress())
        {
            Toast.makeText(AddingCompany.this,"Upload in Progress",Toast.LENGTH_SHORT).show();
        }
        else {
            uploadFile();
        }

    }

    private  String getFileExtension(Uri uri)
    {
        ContentResolver cR= getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile()
    {
       if(imageUri != null)
       {
         final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));
         mUploadTask=fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
             @Override
             public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddingCompany.this,"Upload Successfull",Toast.LENGTH_SHORT).show();
                fileReference.getDownloadUrl().
                        addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadUri=uri;
                                Upload upload = new Upload(editTextTitle.getText().toString().trim(),
                                        editTextDescription.getText().toString().trim(),
                                        downloadUri.toString());
                                String uploadId = mDatabaseRef.push().getKey();
                                mDatabaseRef.child(uploadId).setValue(upload);
                            }
                        }
                );
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
               Toast.makeText(AddingCompany.this,e.getMessage(),Toast.LENGTH_SHORT).show();
             }
         }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
             @Override
             public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

             }
         });
       }
       else {
           Toast.makeText(this,"No file Selected",Toast.LENGTH_SHORT).show();
       }
    }

}


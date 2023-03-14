package com.adarshpanig.placementapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CompanyFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CompanyAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_company,container,false);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       mRecyclerView=view.findViewById(R.id.recyclerView);
       mRecyclerView.setHasFixedSize(true);
       mRecyclerView.setLayoutManager(new LinearLayoutManager( this.getContext()));
       mUploads=new ArrayList<>();
       mDatabaseRef= FirebaseDatabase.getInstance().getReference("uploads");
       mDatabaseRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot postSnapshot : snapshot.getChildren()){
                   Upload upload=postSnapshot.getValue(Upload.class);
                   mUploads.add(upload);
               }

               mAdapter=new CompanyAdapter(view.getContext(),mUploads);
               mRecyclerView.setAdapter(mAdapter);

               mAdapter.setOnItemClickListener(new CompanyAdapter.OnItemClickListener() {
                   @Override
                   public void onItemClick(int position) {
                       Upload upload=mUploads.get(position);
                       String title, desc;
                       title=upload.getName();
                       desc=upload.getDesc();

                       Intent intent = new Intent(view.getContext(),CommonActivity.class);
                       intent.putExtra("titletext1",title);
                       intent.putExtra("descriptiontext1",desc);
                       startActivity(intent);
                   }

                   @Override
                   public void onDeleteClick(int position) {

                   }
               });
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

    }
}

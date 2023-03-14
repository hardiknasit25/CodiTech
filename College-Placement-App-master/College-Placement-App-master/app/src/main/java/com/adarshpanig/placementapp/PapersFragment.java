package com.adarshpanig.placementapp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
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

public class PapersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private PdfAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<uploadPDF> uploadPDFS;
    View v;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v= inflater.inflate(R.layout.fragment_papers,container,false);
         return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView=view.findViewById(R.id.recyclerView2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager( this.getContext()));
        uploadPDFS=new ArrayList<>();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("uploadPDF");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    uploadPDF uploadPDF = dataSnapshot.getValue(com.adarshpanig.placementapp.uploadPDF.class);
                    uploadPDFS.add(uploadPDF);
                }
                mAdapter= new PdfAdapter(view.getContext(),uploadPDFS);
                mRecyclerView.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(new PdfAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(int position) {
                        uploadPDF uPDF=uploadPDFS.get(position);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                        browserIntent.setData(Uri.parse(uPDF.getUrl()));
                        view.getContext().startActivity(browserIntent);
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

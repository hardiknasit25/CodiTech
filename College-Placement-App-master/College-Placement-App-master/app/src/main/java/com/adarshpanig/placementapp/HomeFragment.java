package com.adarshpanig.placementapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class HomeFragment extends Fragment {

    TextView welcomestudent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_home,container,false);
         welcomestudent= v.findViewById(R.id.welcomestudent);
         return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String sname=StudentHome.message;
        if(sname==null){
            welcomestudent.append(" "+sname);
        }
        else {
            welcomestudent.append(" " + sname);
        }

        super.onViewCreated(view, savedInstanceState);
    }
}

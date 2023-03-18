package com.example.placementmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAddjobAdapter extends RecyclerView.Adapter<RecyclerAddjobAdapter.ViewHolder> {

    add_job add_job;
    ArrayList<AddjobModel> arrJob;
    public RecyclerAddjobAdapter(add_job add_job, ArrayList<AddjobModel> arrJob) {
        this.add_job = add_job;
        this.arrJob = arrJob;
    }

//    visit_company visit_company;
//    public RecyclerAddjobAdapter(visit_company visit_company, ArrayList<AddjobModel> arrJob) {
//        this.visit_company = visit_company;
//        this.arrJob = arrJob;
//
//    }

    @NonNull
    @Override
    public RecyclerAddjobAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(add_job).inflate(R.layout.company_job_format,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerAddjobAdapter.ViewHolder holder, int position) {
        holder.date.setText(arrJob.get(position).getDate());
        holder.cgpa.setText(arrJob.get(position).getCgpa());
        holder.skill.setText(arrJob.get(position).getSkill());
        holder.packag.setText(arrJob.get(position).getPackag());


    }

    @Override
    public int getItemCount() {
        return arrJob.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,date,cgpa,skill,packag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            cgpa = itemView.findViewById(R.id.cgpa);
            skill = itemView.findViewById(R.id.skill);
            packag = itemView.findViewById(R.id.packag);
        }
    }


}

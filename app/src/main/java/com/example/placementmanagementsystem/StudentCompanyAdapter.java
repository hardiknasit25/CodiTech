package com.example.placementmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentCompanyAdapter extends RecyclerView.Adapter<StudentCompanyAdapter.ViewHolder> {

    visit_company visit_company;

    ArrayList<AddjobModel> arrJob;
//    ArrayList<ComanyModel> arrname;

    public StudentCompanyAdapter(com.example.placementmanagementsystem.visit_company visit_company, ArrayList<AddjobModel> arrJob) {
        this.visit_company = visit_company;
        this.arrJob = arrJob;
//        this.arrname = arrname;
    }

//    public StudentCompanyAdapter(visit_company visit_company, ArrayList<AddjobModel> arrJob,ArrayList<ComanyModel> arrname;) {
//        this.visit_company = visit_company;
//        this.arrJob = arrJob;
//        this.arrname = arrname;
//
//    }

    @NonNull
    @Override
    public StudentCompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(visit_company).inflate(R.layout.company_job_format,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull StudentCompanyAdapter.ViewHolder holder, int position) {
//        holder.name.setText(arrname.get(position).getName());
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

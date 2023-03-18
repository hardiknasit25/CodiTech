package com.example.placementmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleProfileAdapter extends RecyclerView.Adapter<RecycleProfileAdapter.ViewHolder> {

    company_student_profile company_student_profile;
    ArrayList<StudentModel> arrStudent;
    public RecycleProfileAdapter(company_student_profile company_student_profile, ArrayList<StudentModel> arrStudent) {
        this.company_student_profile = company_student_profile;
        this.arrStudent = arrStudent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(company_student_profile).inflate(R.layout.student_job_format,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(arrStudent.get(position).getName());
        holder.collage.setText(arrStudent.get(position).getCollage());
        holder.cgpa.setText(arrStudent.get(position).getMarks());
        holder.skill.setText(arrStudent.get(position).getSkill());
        holder.graduation.setText(arrStudent.get(position).getGraduation());

    }

    @Override
    public int getItemCount() {
        return arrStudent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,collage,cgpa,skill,graduation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            collage = itemView.findViewById(R.id.collage);
            cgpa = itemView.findViewById(R.id.cgpa);
            skill = itemView.findViewById(R.id.skill);
            graduation = itemView.findViewById(R.id.graduation);
        }
    }
}

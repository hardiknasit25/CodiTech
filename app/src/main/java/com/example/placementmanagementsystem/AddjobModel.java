package com.example.placementmanagementsystem;

public class AddjobModel {
    String date;
    String packag;
    String cgpa;
    String skill;

    public AddjobModel() {
    }

    public AddjobModel(String date, String packag, String cgpa, String skill) {
        this.date = date;
        this.packag = packag;
        this.cgpa = cgpa;
        this.skill = skill;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPackag() {
        return packag;
    }

    public void setPackag(String packag) {
        this.packag = packag;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}

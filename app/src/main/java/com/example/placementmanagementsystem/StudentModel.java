package com.example.placementmanagementsystem;

public class StudentModel {
    String name;
    String email;
    String number;
    String collage;
    String branch;
    String marks;
    String graduation;
    String skill;

    public StudentModel() {
    }

    public StudentModel(String name, String email, String number, String collage, String branch, String marks, String graduation, String skill) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.collage = collage;
        this.branch = branch;
        this.marks = marks;
        this.graduation = graduation;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}

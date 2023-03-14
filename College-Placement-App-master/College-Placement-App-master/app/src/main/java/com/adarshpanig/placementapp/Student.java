package com.adarshpanig.placementapp;

public class Student {

    private String sname;
    private String spassword;
    private String sid;
    private String sbranch;
    private String sperc;
    private String scompany;

    public Student(){

    }

    public Student (String sname,String spassword,String sid,String sbranch,String sperc){
        this.sname=sname;
        this.spassword=spassword;
        this.sid=sid;
        this.sbranch=sbranch;
        this.sperc=sperc;
    }

    public String getScompany(){
        return scompany;
    }
    public void setCompany(String scompany){
        this.scompany=scompany;
    }

    public String getSname(){
        return sname;
    }
    public void setSname(String sname){
        this.sname=sname;
    }

    public String getSpassword(){
        return spassword;
    }
    public void setSpassword(String spassword){
        this.spassword=spassword;
    }

    public String getSid(){
        return sid;
    }
    public void setSid(String sid){
        this.sid=sid;
    }

    public String getSbranch(){
        return sbranch;
    }

    public void setSbranch(String sbranch){
        this.sbranch=sbranch;
    }
    public String getSperc(){
        return sperc;
    }

    public void setSperc(String sperc){
        this.sperc=sperc;
    }
}

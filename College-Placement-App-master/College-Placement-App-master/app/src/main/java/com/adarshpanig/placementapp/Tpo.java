package com.adarshpanig.placementapp;

public class Tpo {

    private String tname;
    private String tpassword;
    private String tid;

       public Tpo()
       {

       }
    public Tpo(String tname,String tpassword,String tid){
        this.tname=tname;
        this.tpassword=tpassword;
        this.tid=tid;
    }

    public String getTname(){
        return tname;
    }
    public void setTname(String tname){
        this.tname=tname;
    }

    public String getTpassword(){
        return tpassword;
    }
    public void setTpassword(String tpassword){
        this.tpassword=tpassword;
    }

    public String getTid(){
        return tid;
    }
    public void setTid(String tid){
        this.tid=tid;
    }
}

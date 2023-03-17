package com.example.placementmanagementsystem;

public class ComanyModel {

    String name;
    String email;
    String number;
    String city;

    public ComanyModel() {
    }

    public ComanyModel(String name, String email, String number, String city) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.city = city;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

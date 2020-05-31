package com.example.trac.model;

import android.util.Patterns;

public class LoginUser {

    private String name;
    private String phone;
    private String email;

    public LoginUser(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValidDetails() {
        return name.length() > 5 && Patterns.PHONE.matcher(getPhone()).matches()
                && Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

}
package com.example.trac.model;

public class UserDetails {

    private String name;
    private String emailId;
    private String mobileNumber;

    public UserDetails(String name, String emailId, String mobileNumber) {
        this.name = name;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}

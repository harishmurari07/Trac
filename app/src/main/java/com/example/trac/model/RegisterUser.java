package com.example.trac.model;

import android.util.Patterns;

public class RegisterUser {

    private String userName;
    private String emailId;
    private String mobileNumber;
    private String countryCode;
    private String deviceId;

    public RegisterUser(String name, String email, String phone, String countryCode, String deviceId) {
        this.userName = name;
        this.emailId = email;
        this.mobileNumber = phone;
        this.countryCode = countryCode;
        this.deviceId = deviceId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getPhone() {
        return mobileNumber;
    }

    public void setPhone(String phone) {
        this.mobileNumber = phone;
    }

    public String getEmail() {
        return emailId;
    }

    public void setEmail(String email) {
        this.emailId = email;
    }

    public boolean isValidDetails() {
        return getName().length() > 5 && Patterns.PHONE.matcher(getPhone()).matches()
                && Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

}

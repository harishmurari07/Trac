package com.example.trac.model;

public class LoginRequest {

    private String mobileNumber;
    private String otpNumber;
    private String otpSharedSecret;

    public LoginRequest(String mobileNumber, String otpNumber, String otpSharedSecret) {
        this.mobileNumber = mobileNumber;
        this.otpNumber = otpNumber;
        this.otpSharedSecret = otpSharedSecret;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtpNumber() {
        return otpNumber;
    }

    public void setOtpNumber(String otpNumber) {
        this.otpNumber = otpNumber;
    }

    public String getOtpSharedSecret() {
        return otpSharedSecret;
    }

    public void setOtpSharedSecret(String otpSharedSecret) {
        this.otpSharedSecret = otpSharedSecret;
    }
}

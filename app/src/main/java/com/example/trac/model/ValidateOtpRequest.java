package com.example.trac.model;

public class ValidateOtpRequest {

    private String emailId;
    private String mobileNumber;
    private String otpNumber;
    private String otpSharedSecret;

    public ValidateOtpRequest(String emailId, String mobileNumber, String otpNumber, String otpSharedSecret) {
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.otpNumber = otpNumber;
        this.otpSharedSecret = otpSharedSecret;
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

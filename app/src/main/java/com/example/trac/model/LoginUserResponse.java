package com.example.trac.model;

public class LoginUserResponse {

    public int status;
    public String message;
    public String uiErrorKey;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUiErrorKey() {
        return uiErrorKey;
    }

    public void setUiErrorKey(String uiErrorKey) {
        this.uiErrorKey = uiErrorKey;
    }
}

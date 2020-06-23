package com.example.trac.api;

public class ServiceError {

    private int status;
    private String message;
    private String uiErrorKey;

    public ServiceError(int status, String message, String uiErrorKey) {
        this.status = status;
        this.message = message;
        this.uiErrorKey = uiErrorKey;
    }

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

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

    public String getMessage() {
        return message;
    }

    public String getUiErrorKey() {
        return uiErrorKey;
    }

}

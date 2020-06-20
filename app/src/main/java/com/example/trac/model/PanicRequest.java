package com.example.trac.model;

public class PanicRequest {

    private String panicMode;
    private String longtitude;
    private String latitude;

    public PanicRequest(String panicMode, String longtitude, String latitude) {
        this.panicMode = panicMode;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public String getPanicMode() {
        return panicMode;
    }

    public void setPanicMode(String panicMode) {
        this.panicMode = panicMode;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}

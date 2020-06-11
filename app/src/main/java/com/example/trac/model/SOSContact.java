package com.example.trac.model;

public class SOSContact {

    private String sosName;
    private String sosCountry;
    private String sosMobile;

    public SOSContact(String sosName, String sosCountry, String sosMobile) {
        this.sosName = sosName;
        this.sosCountry = sosCountry;
        this.sosMobile = sosMobile;
    }

    public String getSosName() {
        return sosName;
    }

    public void setSosName(String sosName) {
        this.sosName = sosName;
    }

    public String getSosCountry() {
        return sosCountry;
    }

    public void setSosCountry(String sosCountry) {
        this.sosCountry = sosCountry;
    }

    public String getSosMobile() {
        return sosMobile;
    }

    public void setSosMobile(String sosMobile) {
        this.sosMobile = sosMobile;
    }
}

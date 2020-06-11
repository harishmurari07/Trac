package com.example.trac.model;

import java.util.List;

public class SOSResponse {

    private String sosMessage;
    private List<SOSContact> sosContacts;
    private int status;

    public String getSosMessage() {
        return sosMessage;
    }

    public void setSosMessage(String sosMessage) {
        this.sosMessage = sosMessage;
    }

    public List<SOSContact> getSosContacts() {
        return sosContacts;
    }

    public void setSosContacts(List<SOSContact> sosContacts) {
        this.sosContacts = sosContacts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

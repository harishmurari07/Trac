package com.example.trac.model;

import java.util.List;

public class SOSRequest {

    private String sosMessage;
    private List<SOSContact> sosContacts;

    public SOSRequest(String sosMessage, List<SOSContact> sosContacts) {
        this.sosMessage = sosMessage;
        this.sosContacts = sosContacts;
    }

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
}

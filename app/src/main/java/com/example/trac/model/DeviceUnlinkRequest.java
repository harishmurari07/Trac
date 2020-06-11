package com.example.trac.model;

public class DeviceUnlinkRequest {

    private String action;
    private String deviceId;

    public DeviceUnlinkRequest(String action, String deviceId) {
        this.action = action;
        this.deviceId = deviceId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}

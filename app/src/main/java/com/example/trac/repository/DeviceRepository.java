package com.example.trac.repository;

import com.example.trac.api.Retro;
import com.example.trac.api.StatusService;
import com.example.trac.model.DeviceStatusResponse;

import io.reactivex.Single;

public class DeviceRepository {

    public Single<DeviceStatusResponse> getDeviceStatus(String deviceId) {
        return Retro.getService(StatusService.class).getDeviceStatus(deviceId);
    }

}

package com.example.trac.api;

import com.example.trac.model.DeviceStatusResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface StatusService {

    //Device Status
    @GET("authentication/device")
    Single<DeviceStatusResponse> getDeviceStatus(@Header("deviceId") String deviceId);

}

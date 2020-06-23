package com.example.trac.api;

import com.example.trac.model.DeviceUnlinkRequest;
import com.example.trac.model.DeviceUnlinkResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UnLinkService {

    //Unlink Device
    @POST("wearable/device")
    Single<DeviceUnlinkResponse> unlinkDevice(@Header("Authorization") String authorization, @Body DeviceUnlinkRequest deviceUnlinkRequest);

}

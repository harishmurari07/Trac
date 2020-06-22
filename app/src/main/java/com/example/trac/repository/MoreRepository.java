package com.example.trac.repository;

import androidx.annotation.NonNull;

import com.example.trac.api.Retro;
import com.example.trac.api.UnLinkService;
import com.example.trac.model.DeviceUnlinkRequest;
import com.example.trac.model.DeviceUnlinkResponse;

import io.reactivex.Single;

public class MoreRepository {

    public Single<DeviceUnlinkResponse> unlink(@NonNull String token, @NonNull DeviceUnlinkRequest deviceUnlinkRequest) {
        return Retro.getService(UnLinkService.class).unlinkDevice(token, deviceUnlinkRequest);
    }

}

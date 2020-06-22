package com.example.trac.repository;

import com.example.trac.api.Retro;
import com.example.trac.api.SOSService;
import com.example.trac.model.SOSRequest;
import com.example.trac.model.SOSResponse;

import io.reactivex.Single;

public class SOSRepository {

    public Single<SOSResponse> updateSOSList(String token, SOSRequest sosRequest) {
        return Retro.getService(SOSService.class).submitSOSContacts(token, sosRequest);
    }

    public Single<SOSResponse> getSOSList(String token) {
        return Retro.getService(SOSService.class).getSOSList(token);
    }

}

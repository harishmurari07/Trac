package com.example.trac.api;

import com.example.trac.model.SOSRequest;
import com.example.trac.model.SOSResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SOSService {

    //Send SOS List
    @POST("wearable/sos")
    Single<SOSResponse> submitSOSContacts(@Header("Authorization") String authorization, @Body SOSRequest sosRequest);

    //Get SOS List
    @GET("wearable/sos")
    Single<SOSResponse> getSOSList(@Header("Authorization") String authorization);

}

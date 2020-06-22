package com.example.trac.api;

import com.example.trac.model.PanicRequest;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PanicService {

    //Panic Mode
    @POST("wearable/panic")
    Single<Response<Void>> sendPanicMode(@Header("Authorization") String authorization, @Body PanicRequest panicRequest);

}

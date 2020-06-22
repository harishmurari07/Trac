package com.example.trac.api;

import com.example.trac.model.LoginRequest;
import com.example.trac.model.LoginUserResponse;
import com.example.trac.model.OtpResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {

    @GET("authentication/otp")
    Single<LoginUserResponse> loginUser(@Header("mobileNumber") String mobileNumber, @Header("countryCode") String countryCode);

    @POST("authentication/login")
    Single<OtpResponse> validateOtpForLogin(@Body LoginRequest loginRequest);

}

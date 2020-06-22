package com.example.trac.api;

import com.example.trac.model.LoginUserResponse;
import com.example.trac.model.OtpResponse;
import com.example.trac.model.RegisterUserRequest;
import com.example.trac.model.ValidateOtpRequest;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {

    @POST("authentication/signup")
    Single<LoginUserResponse> registerNewUser(@Body RegisterUserRequest registerUserRequest);

    @POST("authentication/authenticate")
    Single<OtpResponse> validateOtp(@Body ValidateOtpRequest otpRequest);

}

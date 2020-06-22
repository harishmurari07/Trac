package com.example.trac.repository;

import com.example.trac.api.LoginService;
import com.example.trac.api.RegisterService;
import com.example.trac.api.Retro;
import com.example.trac.model.LoginRequest;
import com.example.trac.model.LoginUserResponse;
import com.example.trac.model.OtpResponse;
import com.example.trac.model.RegisterUserRequest;
import com.example.trac.model.ValidateOtpRequest;

import io.reactivex.Single;

public class LoginRepository {

    //New User
    public Single<LoginUserResponse> registerNewUser(RegisterUserRequest registerUserRequest) {
        return Retro.getService(RegisterService.class).registerNewUser(registerUserRequest);
    }

    //Returning User
    public Single<LoginUserResponse> loginUser(String mobileNumber, String countryCode) {
        return Retro.getService(LoginService.class).loginUser(mobileNumber, countryCode);
    }

    //Validate OTP - New User
    public Single<OtpResponse> validateOtp(ValidateOtpRequest otpRequest) {
        return Retro.getService(RegisterService.class).validateOtp(otpRequest);
    }

    //Validate OTP - Returning User
    public Single<OtpResponse> validateOtpForLogin(LoginRequest loginRequest) {
        return Retro.getService(LoginService.class).validateOtpForLogin(loginRequest);
    }

}

package com.example.trac.api;

import com.example.trac.model.DeviceStatusResponse;
import com.example.trac.model.DeviceUnlinkRequest;
import com.example.trac.model.DeviceUnlinkResponse;
import com.example.trac.model.LoginRequest;
import com.example.trac.model.LoginUserResponse;
import com.example.trac.model.OtpResponse;
import com.example.trac.model.RegisterUserRequest;
import com.example.trac.model.SOSRequest;
import com.example.trac.model.SOSResponse;
import com.example.trac.model.ValidateOtpRequest;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ApiService {

    //First-Time User
    @POST("authentication/signup")
    Single<LoginUserResponse> sendLoginData(@Body RegisterUserRequest registerUserRequest);

    //Returning User
    @GET("authentication/otp")
    Single<LoginUserResponse> getOtpForLogin(@Header("mobileNumber") String mobileNumber, @Header("countryCode") String countryCode);

    //Verify OTP for First time user
    @POST("authentication/authenticate")
    Single<OtpResponse> validateOtp(@Body ValidateOtpRequest otpRequest);

    //Verify OTP for Returning user
    @POST("authentication/login")
    Single<OtpResponse> validateOtpForLogin(@Body LoginRequest loginRequest);

    //Device Status
    @GET("authentication/device")
    Single<DeviceStatusResponse> getDeviceStatus(@Header("deviceId") String deviceId);

    //Unlink Device
    @POST("wearable/device")
    Single<DeviceUnlinkResponse> unlinkDevice(@Header("Authorization") String authorization, DeviceUnlinkRequest deviceUnlinkRequest);

    //Send SOS List
    @POST("wearable/sos")
    Single<SOSResponse> submitSOSContacts(@Header("Authorization") String  authorization, @Body SOSRequest sosRequest);

    //Get SOS List
    @GET("wearable/sos")
    Single<SOSResponse> getSOSList(@Header("AAuthorization") String authorization);

    //Panic Mode

}

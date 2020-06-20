package com.example.trac.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.trac.api.ApiService;
import com.example.trac.api.Retro;
import com.example.trac.model.LoginRequest;
import com.example.trac.model.LoginUserResponse;
import com.example.trac.model.OtpResponse;
import com.example.trac.model.RegisterUserRequest;
import com.example.trac.model.ValidateOtpRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class LoginRepository {

    private static LoginRepository instance;
    private static ApiService apiService;

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    private static ApiService getApiService() {
        if (apiService == null) {
            apiService = Retro.getApiService();
        }
        return apiService;
    }

    //New User
    public MutableLiveData<LoginUserResponse> registerNewUser(RegisterUserRequest registerUserRequest) {
        MutableLiveData<LoginUserResponse> loginUserResponseMutableLiveData = new MutableLiveData<>();

        getApiService().registerNewUser(registerUserRequest).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<LoginUserResponse>() {
                    @Override
                    public void onSuccess(@NonNull LoginUserResponse loginUserResponse) {
                        loginUserResponseMutableLiveData.setValue(loginUserResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loginUserResponseMutableLiveData.setValue(null);
                    }
                });
        return loginUserResponseMutableLiveData;
    }

    //Returning User
    public MutableLiveData<LoginUserResponse> loginUser(String mobileNumber, String countryCode) {
        MutableLiveData<LoginUserResponse> loginUserResponseOtp = new MutableLiveData<>();

        getApiService().loginUser(mobileNumber, countryCode).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<LoginUserResponse>() {
                    @Override
                    public void onSuccess(LoginUserResponse loginUserResponse) {
                        loginUserResponseOtp.setValue(loginUserResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginUserResponseOtp.setValue(null);
                    }
                });
        return loginUserResponseOtp;
    }

    //Validate OTP - New User
    public MutableLiveData<OtpResponse> validateOtp(ValidateOtpRequest otpRequest) {
        MutableLiveData<OtpResponse> otpResponseMutableLiveData = new MutableLiveData<>();

        getApiService().validateOtp(otpRequest).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<OtpResponse>() {
                    @Override
                    public void onSuccess(OtpResponse otpResponse) {
                        otpResponseMutableLiveData.setValue(otpResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        otpResponseMutableLiveData.setValue(null);
                    }
                });
        return otpResponseMutableLiveData;
    }

    //Validate OTP - Returning User
    public MutableLiveData<OtpResponse> validateOtpForLogin(LoginRequest loginRequest) {
        MutableLiveData<OtpResponse> loginOtpResponse = new MutableLiveData<>();

        getApiService().validateOtpForLogin(loginRequest).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<OtpResponse>() {
                    @Override
                    public void onSuccess(OtpResponse otpResponse) {
                        loginOtpResponse.setValue(otpResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginOtpResponse.setValue(null);
                    }
                });
        return loginOtpResponse;
    }

}

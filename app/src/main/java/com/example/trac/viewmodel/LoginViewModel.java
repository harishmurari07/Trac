package com.example.trac.viewmodel;

import android.util.Log;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trac.api.RetrofitUtil;
import com.example.trac.api.ServiceError;
import com.example.trac.model.LoginRequest;
import com.example.trac.model.LoginUserResponse;
import com.example.trac.model.OtpResponse;
import com.example.trac.model.RegisterUserRequest;
import com.example.trac.model.ValidateOtpRequest;
import com.example.trac.repository.LoginRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginUserResponse> loginUserResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<OtpResponse> otpResponseMutableLiveData = new MutableLiveData<>();
    private boolean isExistingUser;
    private LoginRepository loginRepository;
    private String sharedSecret;

    public LoginViewModel() {
        loginRepository = new LoginRepository();
    }

    public void registerUser(RegisterUserRequest registerUserRequest) {
        loginRepository.registerNewUser(registerUserRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<LoginUserResponse>() {
                    @Override
                    public void onSuccess(LoginUserResponse loginUserResponse) {
                        loginUserResponseMutableLiveData.setValue(loginUserResponse);
                        Log.d("--------", loginUserResponse.getSharedSecret());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ServiceError serviceError = RetrofitUtil.parseError(e);
                        Log.d("---------", serviceError.getMessage());
                        loginUserResponseMutableLiveData.setValue(null);
                    }
                });
    }

    public void validateNewUserOtp(@NonNull ValidateOtpRequest validateOtpRequest) {
        loginRepository.validateOtp(validateOtpRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<OtpResponse>() {
                    @Override
                    public void onSuccess(OtpResponse otpResponse) {
                        otpResponseMutableLiveData.setValue(otpResponse);
                        Log.d("--------", otpResponse.getToken());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ServiceError serviceError = RetrofitUtil.parseError(e);
                        Log.d("---------", serviceError.getMessage());
                        otpResponseMutableLiveData.setValue(null);
                    }
                });
    }

    public void validateExistingUser(@NonNull String mobileNumber, @NonNull String code) {
        loginRepository.loginUser(mobileNumber, code).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<LoginUserResponse>() {
                    @Override
                    public void onSuccess(LoginUserResponse loginUserResponse) {
                        loginUserResponseMutableLiveData.setValue(loginUserResponse);
                        Log.d("--------", loginUserResponse.getSharedSecret());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ServiceError serviceError = RetrofitUtil.parseError(e);
                        Log.d("---------", serviceError.getMessage());
                        loginUserResponseMutableLiveData.setValue(null);
                    }
                });
    }

    public void validateExistingUserOtp(@NonNull LoginRequest loginRequest) {
        loginRepository.validateOtpForLogin(loginRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<OtpResponse>() {
                    @Override
                    public void onSuccess(OtpResponse otpResponse) {
                        otpResponseMutableLiveData.setValue(otpResponse);
                        Log.d("---------", otpResponse.getToken());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ServiceError serviceError = RetrofitUtil.parseError(e);
                        Log.d("---------", serviceError.getMessage());
                        otpResponseMutableLiveData.setValue(null);
                    }
                });
    }

    public void setIsExistingUser(boolean existingUser) {
        isExistingUser = existingUser;
    }

    public boolean isExistingUser() {
        return isExistingUser;
    }

    public LiveData<OtpResponse> getOtpSuccess() {
        return otpResponseMutableLiveData;
    }

    public LiveData<LoginUserResponse> getLoginSuccess() {
        return loginUserResponseMutableLiveData;
    }

    public void setSharedSecret(String sharedKey) {
        sharedSecret = sharedKey;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public boolean isValidDetails(String name, String phone, String email) {
        return name.length() > 5 && Patterns.PHONE.matcher(phone).matches()
                && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}

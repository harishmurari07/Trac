package com.example.trac.viewmodel;

import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trac.model.LoginRequest;
import com.example.trac.model.LoginUserResponse;
import com.example.trac.model.OtpResponse;
import com.example.trac.model.RegisterUserRequest;
import com.example.trac.model.UserDetails;
import com.example.trac.model.ValidateOtpRequest;
import com.example.trac.repository.LoginRepository;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<UserDetails> userDetailsMutableLiveData;
    private MutableLiveData<LoginUserResponse> loginUserResponseMutableLiveData;
    private MutableLiveData<OtpResponse> otpResponseMutableLiveData;
    private boolean isExistingUser;
    private LoginRepository loginRepository;

    public void registerUser(RegisterUserRequest registerUserRequest) {
        loginUserResponseMutableLiveData = getLoginRepository().registerNewUser(registerUserRequest);
    }

    public void validateNewUserOtp(@NonNull ValidateOtpRequest validateOtpRequest) {
        otpResponseMutableLiveData = getLoginRepository().validateOtp(validateOtpRequest);
    }

    public void validateExistingUser(@NonNull String mobileNumber, @NonNull String code) {
        loginUserResponseMutableLiveData = getLoginRepository().loginUser(mobileNumber, code);
    }

    public void validateExistingUserOtp(@NonNull LoginRequest loginRequest) {
        otpResponseMutableLiveData = getLoginRepository().validateOtpForLogin(loginRequest);
    }

    private LoginRepository getLoginRepository() {
        if (loginRepository == null) {
            loginRepository = LoginRepository.getInstance();
        }
        return loginRepository;
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

    public void saveUserDetails(UserDetails userDetails) {
        userDetailsMutableLiveData = new MutableLiveData<>();
        userDetailsMutableLiveData.setValue(userDetails);
    }

    public LiveData<UserDetails> getUserDetails() {
        return userDetailsMutableLiveData;
    }

    public boolean isValidDetails(String name, String phone, String email) {
        return name.length() > 5 && Patterns.PHONE.matcher(phone).matches()
                && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}

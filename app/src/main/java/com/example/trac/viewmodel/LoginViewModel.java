package com.example.trac.viewmodel;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trac.model.RegisterUser;
import com.example.trac.model.LoginUserResponse;
import com.example.trac.repository.LoginRepository;

public class LoginViewModel extends ViewModel {

    //    public MutableLiveData<RegisterUser> loginUserDetails = new MutableLiveData<>();
    private MutableLiveData<LoginUserResponse> loginUserResponseMutableLiveData;
    private LoginRepository loginRepository;

    public void registerUser(RegisterUser registerUser) {
        loginRepository = LoginRepository.getInstance();
        loginUserResponseMutableLiveData = loginRepository.checkLoginData(registerUser);
    }

    public LiveData<LoginUserResponse> getLoginSuccess() {
        return loginUserResponseMutableLiveData;
    }

//    public LiveData<RegisterUser> getLoginUserDetails() {
//        return loginUserDetails;
//    }

    public boolean isValidDetails(String name, String phone, String email) {
        return name.length() > 5 && Patterns.PHONE.matcher(phone).matches()
                && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}

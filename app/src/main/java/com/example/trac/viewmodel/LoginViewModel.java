package com.example.trac.viewmodel;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trac.model.LoginUser;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<LoginUser> loginUserDetails = new MutableLiveData<>();

    public void registerUser(LoginUser loginUser) {
        loginUserDetails.setValue(loginUser);
    }

    public LiveData<LoginUser> getLoginUserDetails() {
        return loginUserDetails;
    }

    public boolean isValidDetails(String name, String phone, String email) {
        return name.length() > 5 && Patterns.PHONE.matcher(phone).matches()
                && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}

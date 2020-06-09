package com.example.trac.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.trac.api.ApiService;
import com.example.trac.api.Retro;
import com.example.trac.model.RegisterUser;
import com.example.trac.model.LoginUserResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class LoginRepository {

    private static LoginRepository instance;
    private ApiService apiService;

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    public MutableLiveData<LoginUserResponse> checkLoginData(RegisterUser registerUser) {
        MutableLiveData<LoginUserResponse> loginUserResponseMutableLiveData = new MutableLiveData<>();
        apiService = Retro.getApiService();

        apiService.sendLoginData(registerUser).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<LoginUserResponse>() {
                    @Override
                    public void onSuccess(@NonNull LoginUserResponse loginUserResponse) {
                        loginUserResponseMutableLiveData.postValue(loginUserResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loginUserResponseMutableLiveData.postValue(null);
                    }
                });
        return loginUserResponseMutableLiveData;
    }

}

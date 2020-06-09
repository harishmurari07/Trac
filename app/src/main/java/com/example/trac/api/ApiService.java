package com.example.trac.api;

import com.example.trac.model.RegisterUser;
import com.example.trac.model.LoginUserResponse;


import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {

    @POST("authentication/signup")
    Single<LoginUserResponse> sendLoginData(@Body RegisterUser registerUser);
//    Observable<LoginUserResponse> sendLoginData(@Body RegisterUser loginUser);

}

package com.example.trac.repository;

import androidx.annotation.NonNull;

import com.example.trac.api.PanicService;
import com.example.trac.api.Retro;
import com.example.trac.model.PanicRequest;

import io.reactivex.Single;
import retrofit2.Response;

public class PanicRepository {

    public Single<Response<Void>> sendPanicRequest(@NonNull String token, @NonNull PanicRequest panicRequest) {
        return Retro.getService(PanicService.class).sendPanicMode(token, panicRequest);
    }

}

package com.example.trac.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.trac.api.ApiService;
import com.example.trac.api.Retro;
import com.example.trac.model.PanicRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class PanicRepository {

    private static PanicRepository instance;
    private static ApiService apiService;

    public static PanicRepository getInstance() {
        if (instance == null) {
            instance = new PanicRepository();
        }
        return instance;
    }

    private static ApiService getApiService() {
        if (apiService == null) {
            apiService = Retro.getApiService();
        }
        return apiService;
    }

    public MutableLiveData<Boolean> sendPanicRequest(@NonNull String token, @NonNull PanicRequest panicRequest) {
        MutableLiveData<Boolean> panicResponse = new MutableLiveData<>();

        getApiService().sendPanicMode(token, panicRequest).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Response<Void>>() {
                    @Override
                    public void onSuccess(Response<Void> voidResponse) {
                        panicResponse.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        panicResponse.setValue(false);
                    }
                });
        return panicResponse;
    }

}

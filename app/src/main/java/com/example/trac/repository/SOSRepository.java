package com.example.trac.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.trac.api.ApiService;
import com.example.trac.api.Retro;
import com.example.trac.model.SOSRequest;
import com.example.trac.model.SOSResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SOSRepository {

    private static SOSRepository instance;
    private ApiService apiService;

    public static SOSRepository getInstance() {
        if (instance == null) {
            instance = new SOSRepository();
        }
        return instance;
    }

    public MutableLiveData<SOSResponse> updateSOSList(String token, SOSRequest sosRequest) {
        MutableLiveData<SOSResponse> sosResponseMutableLiveData = new MutableLiveData<>();
        apiService = Retro.getApiService();

        apiService.submitSOSContacts(token, sosRequest).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<SOSResponse>() {
                    @Override
                    public void onSuccess(SOSResponse sosResponse) {
                        sosResponseMutableLiveData.setValue(sosResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        sosResponseMutableLiveData.setValue(null);
                    }
                });
        return sosResponseMutableLiveData;
    }

    public MutableLiveData<SOSResponse> getSOSList(String token) {
        MutableLiveData<SOSResponse> sosResponseMutableLiveData = new MutableLiveData<>();
        apiService = Retro.getApiService();

        apiService.getSOSList(token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<SOSResponse>() {
                    @Override
                    public void onSuccess(SOSResponse sosResponse) {
                        sosResponseMutableLiveData.setValue(sosResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        sosResponseMutableLiveData.setValue(null);
                    }
                });
        return sosResponseMutableLiveData;
    }

}

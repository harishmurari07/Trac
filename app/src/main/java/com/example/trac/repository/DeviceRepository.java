package com.example.trac.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.trac.api.ApiService;
import com.example.trac.api.Retro;
import com.example.trac.model.DeviceStatusResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DeviceRepository {

    private static DeviceRepository instance;
    private static ApiService apiService;

    public static DeviceRepository getInstance() {
        if (instance == null) {
            instance = new DeviceRepository();
        }
        return instance;
    }

    private static ApiService getApiService() {
        if (apiService == null) {
            apiService = Retro.getApiService();
        }
        return apiService;
    }

    public MutableLiveData<DeviceStatusResponse> getDeviceStatus(String deviceId) {
        MutableLiveData<DeviceStatusResponse> deviceStatusResponseMutableLiveData = new MutableLiveData<>();

        getApiService().getDeviceStatus(deviceId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<DeviceStatusResponse>() {
                    @Override
                    public void onSuccess(DeviceStatusResponse deviceStatusResponse) {
                        deviceStatusResponseMutableLiveData.setValue(deviceStatusResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        deviceStatusResponseMutableLiveData.setValue(null);
                    }
                });
        return deviceStatusResponseMutableLiveData;
    }

}

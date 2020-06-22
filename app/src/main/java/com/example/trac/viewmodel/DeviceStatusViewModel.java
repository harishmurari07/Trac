package com.example.trac.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trac.model.DeviceStatusResponse;
import com.example.trac.repository.DeviceRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DeviceStatusViewModel extends ViewModel {

    private MutableLiveData<DeviceStatusResponse> deviceStatusResponseMutableLiveData = new MutableLiveData<>();
    private DeviceRepository deviceRepository;

    public DeviceStatusViewModel() {
        deviceRepository = new DeviceRepository();
    }

    public void checkDeviceStatus(String deviceId) {
        deviceRepository.getDeviceStatus(deviceId).observeOn(Schedulers.io())
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
    }

    public LiveData<DeviceStatusResponse> getDeviceStatus() {
        return deviceStatusResponseMutableLiveData;
    }

}

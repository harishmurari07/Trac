package com.example.trac.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trac.model.DeviceStatusResponse;
import com.example.trac.repository.DeviceRepository;

public class DeviceStatusViewModel extends ViewModel {

    private MutableLiveData<DeviceStatusResponse> deviceStatusResponseMutableLiveData;
    private DeviceRepository deviceRepository;

    public void checkDeviceStatus(String deviceId) {
        deviceRepository = DeviceRepository.getInstance();
        deviceStatusResponseMutableLiveData = deviceRepository.getDeviceStatus(deviceId);
    }

    public LiveData<DeviceStatusResponse> getDeviceStatus() {
        return deviceStatusResponseMutableLiveData;
    }

}

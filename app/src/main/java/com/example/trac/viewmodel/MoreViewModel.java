package com.example.trac.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trac.model.DeviceUnlinkRequest;
import com.example.trac.model.DeviceUnlinkResponse;
import com.example.trac.preferences.PreferenceManager;
import com.example.trac.repository.MoreRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MoreViewModel extends ViewModel {

    private MutableLiveData<DeviceUnlinkResponse> deviceUnlinkResponseMutableLiveData = new MutableLiveData<>();
    private MoreRepository moreRepository;

    public MoreViewModel() {
        moreRepository = new MoreRepository();
    }

    public void addOnsClicked() {

    }

    public void changeMobileNumber() {

    }

    public void logoutClicked() {

    }

    public void faqClicked() {

    }

    public void reportClicked() {

    }

    public void unLinkClicked() {
        DeviceUnlinkRequest deviceUnlinkRequest = new DeviceUnlinkRequest("UNLINK", "WEAR001");

        moreRepository.unlink(PreferenceManager.token(), deviceUnlinkRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<DeviceUnlinkResponse>() {
                    @Override
                    public void onSuccess(DeviceUnlinkResponse deviceUnlinkResponse) {
                        deviceUnlinkResponseMutableLiveData.setValue(deviceUnlinkResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        deviceUnlinkResponseMutableLiveData.setValue(null);
                    }
                });
    }

    public void bluetoothClicked() {

    }

    public void inviteClicked() {

    }

    public LiveData<DeviceUnlinkResponse> getDeviceUnlinkStatus() {
        return deviceUnlinkResponseMutableLiveData;
    }

}

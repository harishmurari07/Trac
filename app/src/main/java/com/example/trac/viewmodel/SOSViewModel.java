package com.example.trac.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trac.model.SOSRequest;
import com.example.trac.model.SOSResponse;
import com.example.trac.repository.SOSRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SOSViewModel extends ViewModel {

    private MutableLiveData<SOSResponse> sosResponseMutableLiveData = new MutableLiveData<>();
    private SOSRepository sosRepository;

    public SOSViewModel() {
        sosRepository = new SOSRepository();
    }

    public void updateSOSList(SOSRequest sosRequest) {
        sosRepository.updateSOSList("", sosRequest).subscribeOn(Schedulers.io())
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
    }

    public void getSOSList() {
        sosRepository.getSOSList("").subscribeOn(Schedulers.io())
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
    }

    public LiveData<SOSResponse> getListResponse() {
        return sosResponseMutableLiveData;
    }

}

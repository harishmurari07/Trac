package com.example.trac.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trac.model.SOSRequest;
import com.example.trac.model.SOSResponse;
import com.example.trac.repository.SOSRepository;

public class SOSViewModel extends ViewModel {

    private MutableLiveData<SOSResponse> sosResponseMutableLiveData;
    private SOSRepository sosRepository;

    public void updateSOSList(SOSRequest sosRequest) {
        sosRepository = SOSRepository.getInstance();
        sosResponseMutableLiveData = sosRepository.updateSOSList("", sosRequest);
    }

    public void getSOSList() {
        sosRepository = SOSRepository.getInstance();
        sosResponseMutableLiveData = sosRepository.getSOSList("");
    }

    public LiveData<SOSResponse> getListResponse() {
        return sosResponseMutableLiveData;
    }

}

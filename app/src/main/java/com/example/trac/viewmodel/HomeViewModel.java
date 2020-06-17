package com.example.trac.viewmodel;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private CountDownTimer timer;
    private boolean timerRunning;
    private MutableLiveData<Long> leftOverTime = new MutableLiveData<>();
    private MutableLiveData<String> timerFinished = new MutableLiveData<>();
    private MutableLiveData<String> timerCancelled = new MutableLiveData<>();

    public void startPanic() {
        if (!timerRunning) {
            timer = new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    leftOverTime.setValue(millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    timerFinished.setValue("Notified");
                    timerRunning = false;
                }
            }.start();
            timerRunning = true;
        }
    }

    public void stopPanic() {
        if (timer != null) {
            timer.cancel();
            timerRunning = false;
            timerCancelled.setValue("Cancelled");
        }
    }

    public LiveData<String> getTimerCancelled() {
        return timerCancelled;
    }

    public LiveData<String> getTimerFinished() {
        return timerFinished;
    }

    public LiveData<Long> getTimerValue() {
        return leftOverTime;
    }

}

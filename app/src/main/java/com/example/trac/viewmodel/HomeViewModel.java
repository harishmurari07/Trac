package com.example.trac.viewmodel;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trac.model.PanicRequest;
import com.example.trac.preferences.PreferenceManager;
import com.example.trac.repository.PanicRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private static final String PANIC_MODE = "TRACK";

    private CountDownTimer timer;
    private boolean timerRunning;
    private MutableLiveData<Long> leftOverTime = new MutableLiveData<>();
    private MutableLiveData<String> timerFinished = new MutableLiveData<>();
    private MutableLiveData<String> timerCancelled = new MutableLiveData<>();
    private MutableLiveData<Boolean> panicResponse = new MutableLiveData<>();
    private PanicRepository panicRepository;

    public HomeViewModel() {
        panicRepository = new PanicRepository();
    }

    public void startPanic() {
        if (!timerRunning) {
            timer = new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    leftOverTime.setValue(millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    sendPanicRequest();
                    timerFinished.setValue("Notified");
                    timerRunning = false;
                }
            }.start();
            timerRunning = true;
        }
    }

    private void sendPanicRequest() {
        PanicRequest panicRequest = new PanicRequest(PANIC_MODE, "", "");

        panicRepository.sendPanicRequest(PreferenceManager.token(), panicRequest).subscribeOn(Schedulers.io())
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
    }

    public LiveData<Boolean> getPanicStatus() {
        return panicResponse;
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

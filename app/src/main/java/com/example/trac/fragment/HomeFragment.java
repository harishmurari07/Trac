package com.example.trac.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.trac.R;
import com.example.trac.databinding.HomeFragmentBinding;
import com.example.trac.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment {

    private HomeFragmentBinding homeFragmentBinding;
    private HomeViewModel homeViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeFragmentBinding.sos.setOnLongClickListener(v -> {
            homeViewModel.startPanic();
            return true;
        });
        homeFragmentBinding.cancel.setOnClickListener(v -> homeViewModel.stopPanic());

        subscribeOnTimerStarted();
        subscribeOnTimerFinished();
        subscribeOnTimerCancelled();

        return homeFragmentBinding.getRoot();
    }

    private void subscribeOnTimerCancelled() {
        homeViewModel.getTimerCancelled().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                homeFragmentBinding.timerInfo.setVisibility(View.GONE);
                homeFragmentBinding.cancel.setVisibility(View.GONE);
                homeFragmentBinding.homeInfo.setVisibility(View.VISIBLE);
                homeFragmentBinding.sos.setText(getResources().getString(R.string.sos));
            }
        });
    }

    private void subscribeOnTimerFinished() {
        homeViewModel.getTimerFinished().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                homeFragmentBinding.timerInfo.setText(s);
                homeFragmentBinding.cancel.setVisibility(View.GONE);
            }
        });
    }

    private void subscribeOnTimerStarted() {
        homeViewModel.getTimerValue().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long value) {
                homeFragmentBinding.homeInfo.setVisibility(View.GONE);
                homeFragmentBinding.timerInfo.setVisibility(View.VISIBLE);
                homeFragmentBinding.cancel.setVisibility(View.VISIBLE);
                homeFragmentBinding.sos.setText(String.valueOf(value));
                homeFragmentBinding.timerInfo.setText(String.format(getResources().getString(R.string.timer_display), value));
            }
        });
    }

}

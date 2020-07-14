package com.example.trac.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trac.R;
import com.example.trac.activity.HomeActivity;
import com.example.trac.android.TextWatcherAdapter;
import com.example.trac.android.Util;
import com.example.trac.databinding.OtpFragmentBinding;
import com.example.trac.model.LoginRequest;
import com.example.trac.model.UserDetails;
import com.example.trac.model.ValidateOtpRequest;
import com.example.trac.preferences.PreferenceManager;
import com.example.trac.viewmodel.LoginViewModel;

public class OtpFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private OtpFragmentBinding otpFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        otpFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.otp_fragment, container, false);
        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        subscribeForResult();

        otpFragmentBinding.otpView.getEditText().addTextChangedListener(new TextWatcher());
//        otpFragmentBinding.resend.setOnClickListener(v -> );
        otpFragmentBinding.submit.setOnClickListener(v -> {
            String otp = otpFragmentBinding.otpView.getEditText().getText().toString();

            String sharedSecret = loginViewModel.getSharedSecret();
            String[] splitKey = sharedSecret.split(" ");

            if (loginViewModel.isExistingUser()) {
                loginViewModel.validateExistingUserOtp(new LoginRequest(PreferenceManager.getPhone(), otp, splitKey[1]));
            } else {
                UserDetails userDetails = PreferenceManager.getUserDetails();
                loginViewModel.validateNewUserOtp(new ValidateOtpRequest(userDetails.getEmailId(), userDetails.getMobileNumber(), otp, splitKey[1]));
            }
        });
        return otpFragmentBinding.getRoot();
    }

    private class TextWatcher extends TextWatcherAdapter {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            super.onTextChanged(s, start, before, count);
            otpFragmentBinding.submit.setEnabled(otpFragmentBinding.otpView.getEditText().getText().length() > 5);
        }
    }

    private void subscribeForResult() {
        loginViewModel.getOtpSuccess().observe(getViewLifecycleOwner(), otpResponse -> {
            if (otpResponse != null && otpResponse.getToken() != null) {
                PreferenceManager.getInstance().setKey(otpResponse.getToken());
                navigateToMainActivity();
            } else {
                Util.showToast(getContext(), "Please enter valid OTP");
            }
        });
    }

    private void navigateToMainActivity() {
        startActivity(new Intent(getActivity(), HomeActivity.class));
    }

}

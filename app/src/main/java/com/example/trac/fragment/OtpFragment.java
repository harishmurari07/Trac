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
import com.example.trac.databinding.OtpFragmentBinding;
import com.example.trac.model.LoginRequest;
import com.example.trac.model.ValidateOtpRequest;
import com.example.trac.viewmodel.LoginViewModel;

public class OtpFragment extends Fragment {

    private LoginViewModel loginViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        OtpFragmentBinding otpFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.otp_fragment, container, false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        otpFragmentBinding.submit.setOnClickListener(v -> {
            String otp = otpFragmentBinding.otpView.getEditText().getText().toString();
            if (loginViewModel.isExistingUser()) {
                loginViewModel.validateExistingUserOtp(new LoginRequest("9884991818", "136943", "MTM2OTQz"));
            } else {
                loginViewModel.validateNewUserOtp(new ValidateOtpRequest("vasanth4.demo@gmail.com", "9884991818", "136943", "MTM2OTQz"));
            }
            subscribeForResult();
        });
        return otpFragmentBinding.getRoot();
    }

    private void subscribeForResult() {
        loginViewModel.getOtpSuccess().observe(this, otpResponse -> navigateToMainActivity());
    }

    private void navigateToMainActivity() {
        startActivity(new Intent(getActivity(), HomeActivity.class));
    }

}

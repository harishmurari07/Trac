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

import com.example.trac.R;
import com.example.trac.activity.HomeActivity;
import com.example.trac.databinding.OtpFragmentBinding;

public class OtpFragment extends Fragment {

    private OtpFragmentBinding otpFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        otpFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.otp_fragment, container, false);
        otpFragmentBinding.submit.setOnClickListener(v -> navigateToMainActivity());
        return otpFragmentBinding.getRoot();
    }

    private void navigateToMainActivity() {
        startActivity(new Intent(getActivity(), HomeActivity.class));
    }

}

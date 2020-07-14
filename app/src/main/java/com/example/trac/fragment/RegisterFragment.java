package com.example.trac.fragment;

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
import com.example.trac.activity.RegisterActivity;
import com.example.trac.android.TextWatcherAdapter;
import com.example.trac.android.Util;
import com.example.trac.databinding.RegisterFragmentBinding;
import com.example.trac.model.RegisterUserRequest;
import com.example.trac.preferences.PreferenceManager;
import com.example.trac.viewmodel.LoginViewModel;

public class RegisterFragment extends Fragment {

    private RegisterFragmentBinding registerFragmentBinding;
    private LoginViewModel loginViewModel;
    private String name, phone, email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false);
        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        subscribeForResult();

        if (loginViewModel.isExistingUser()) {
            registerFragmentBinding.nameView.setVisibility(View.GONE);
            registerFragmentBinding.emailView.setVisibility(View.GONE);
            registerFragmentBinding.register.setText(getResources().getString(R.string.login));
        } else {
            registerFragmentBinding.nameView.getEditText().addTextChangedListener(new TextWatcher());
            registerFragmentBinding.emailView.getEditText().addTextChangedListener(new TextWatcher());
        }

        registerFragmentBinding.phoneView.getEditText().addTextChangedListener(new TextWatcher());

        registerFragmentBinding.register.setOnClickListener(v -> {
            name = registerFragmentBinding.nameView.getEditText().getText().toString();
            phone = registerFragmentBinding.phoneView.getEditText().getText().toString();
            email = registerFragmentBinding.emailView.getEditText().getText().toString();
            Util.hideKeyboard(getActivity());
            if (loginViewModel.isExistingUser()) {
                //Login User
                loginViewModel.validateExistingUser(phone, "91");
            } else {
                //Register User
                if (loginViewModel.isValidDetails(name, phone, email)) {
                    loginViewModel.registerUser(new RegisterUserRequest(name, email, phone, "91", "WEAR001"));
                } else {
                    Util.showToast(getContext(), "Please enter valid details...");
                }
            }
        });
        return registerFragmentBinding.getRoot();
    }

    private class TextWatcher extends TextWatcherAdapter {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            super.onTextChanged(s, start, before, count);
            if (loginViewModel.isExistingUser()) {
                registerFragmentBinding.register.setEnabled(registerFragmentBinding.phoneView.getEditText().getText().length() == 10);
            } else {
                registerFragmentBinding.register.setEnabled(registerFragmentBinding.nameView.getEditText().getText().length() > 3 &&
                        registerFragmentBinding.phoneView.getEditText().getText().length() == 10 &&
                        registerFragmentBinding.emailView.getEditText().getText().length() > 8);
            }
        }
    }

    private void subscribeForResult() {
        loginViewModel.getLoginSuccess().observe(getViewLifecycleOwner(), loginUserResponse -> {
            if (loginUserResponse != null && loginUserResponse.getSharedSecret() != null) {
                if (!loginViewModel.isExistingUser()) {
                    PreferenceManager.getInstance().setUserDetails(name, phone, email);
                } else {
                    PreferenceManager.getInstance().setPhone(phone);
                }
                loginViewModel.setSharedSecret(loginUserResponse.getSharedSecret());
                ((RegisterActivity) getActivity()).attachFragment(new OtpFragment());
            } else {
                Util.showToast(getContext(), "Login Failed");
            }
        });
    }

}

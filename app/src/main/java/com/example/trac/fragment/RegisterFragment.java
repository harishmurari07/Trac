package com.example.trac.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.trac.R;
import com.example.trac.activity.RegisterActivity;
import com.example.trac.databinding.RegisterFragmentBinding;
import com.example.trac.model.LoginUserResponse;
import com.example.trac.model.RegisterUserRequest;
import com.example.trac.model.UserDetails;
import com.example.trac.viewmodel.LoginViewModel;

public class RegisterFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private String name, phone, email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RegisterFragmentBinding registerFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false);
        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);

        if (loginViewModel.isExistingUser()) {
            registerFragmentBinding.register.setText(getResources().getString(R.string.login));
        }

        registerFragmentBinding.register.setOnClickListener(v -> {
            name = registerFragmentBinding.nameView.getEditText().getText().toString();
            phone = registerFragmentBinding.phoneView.getEditText().getText().toString();
            email = registerFragmentBinding.emailView.getEditText().getText().toString();

            if (loginViewModel.isExistingUser()) {
                //Login User
                loginViewModel.validateExistingUser("9884991818", "91");
                subscribeForResult();
            } else {
                //Register User
                if (loginViewModel.isValidDetails(name, phone, email)) {
                    loginViewModel.registerUser(new RegisterUserRequest(name, email, phone, "91", "wear0"));
                    subscribeForResult();
                } else {
                    Toast.makeText(getContext(), "Please enter valid details...", Toast.LENGTH_LONG).show();
                }
            }
        });
        return registerFragmentBinding.getRoot();
    }

    private void subscribeForResult() {
        loginViewModel.getLoginSuccess().observe(getViewLifecycleOwner(), new Observer<LoginUserResponse>() {
            @Override
            public void onChanged(LoginUserResponse loginUserResponse) {
                if (loginUserResponse != null && loginUserResponse.getSharedSecret() != null) {
                    loginViewModel.saveUserDetails(new UserDetails(name, email, phone));
                    ((RegisterActivity) getActivity()).attachFragment(new OtpFragment());
                } else {
                    ((RegisterActivity) getActivity()).attachFragment(new OtpFragment());
                    Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}

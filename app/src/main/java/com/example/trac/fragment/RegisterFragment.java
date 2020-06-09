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
import com.example.trac.model.RegisterUser;
import com.example.trac.model.LoginUserResponse;
import com.example.trac.viewmodel.LoginViewModel;

public class RegisterFragment extends Fragment {

    private RegisterFragmentBinding registerFragmentBinding;
    private LoginViewModel loginViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false);
//        loginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);

        registerFragmentBinding.register.setOnClickListener(v -> {
            String name = registerFragmentBinding.nameView.getEditText().getText().toString();
            String phone = registerFragmentBinding.phoneView.getEditText().getText().toString();
            String email = registerFragmentBinding.emailView.getEditText().getText().toString();
            if (loginViewModel.isValidDetails(name, phone, email)) {
                loginViewModel.registerUser(new RegisterUser(name, email, phone, "91", "wear0"));
                subscribeForResult();
            } else {
                Toast.makeText(getContext(), "Please enter valid details...", Toast.LENGTH_LONG).show();
            }
        });
        return registerFragmentBinding.getRoot();
    }

    private void subscribeForResult() {
        loginViewModel.getLoginSuccess().observe(this, new Observer<LoginUserResponse>() {
            @Override
            public void onChanged(LoginUserResponse loginUserResponse) {
                if (loginUserResponse != null && loginUserResponse.status == 200) {
                    ((RegisterActivity) getActivity()).attachFragment(new OtpFragment());
                } else {
                    Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}

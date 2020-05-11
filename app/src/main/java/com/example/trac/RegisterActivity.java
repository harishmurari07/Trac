package com.example.trac;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trac.databinding.RegisterScreenBinding;

public class RegisterActivity extends AppCompatActivity implements RegisterFragment.RegisterListener {

    private RegisterScreenBinding registerScreenBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerScreenBinding = DataBindingUtil.setContentView(this, R.layout.register_screen);

        attachRegisterFragment(new RegisterFragment());

    }

    private void attachRegisterFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void registerClicked() {
        attachRegisterFragment(new OtpFragment());
    }
}

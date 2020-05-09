package com.example.trac;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.trac.databinding.RegisterScreenBinding;

public class RegisterActivity extends AppCompatActivity {

    private RegisterScreenBinding registerScreenBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerScreenBinding = DataBindingUtil.setContentView(this, R.layout.register_screen);


    }
}

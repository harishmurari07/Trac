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
import com.example.trac.databinding.MoreFragmentBinding;
import com.example.trac.viewmodel.MoreViewModel;

public class MoreFragment extends Fragment {

    private MoreFragmentBinding moreFragmentBinding;
    private MoreViewModel moreViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        moreFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.more_fragment, container, false);
        moreViewModel = new ViewModelProvider(this).get(MoreViewModel.class);
        initViews();
        attachListeners();
        return moreFragmentBinding.getRoot();
    }

    private void attachListeners() {
        moreFragmentBinding.bluetoothRow.layout.setOnClickListener(v -> moreViewModel.bluetoothClicked());
        moreFragmentBinding.inviteRow.layout.setOnClickListener(v -> moreViewModel.inviteClicked());
        moreFragmentBinding.reportRow.layout.setOnClickListener(v -> moreViewModel.reportClicked());
        moreFragmentBinding.faqRow.layout.setOnClickListener(v -> moreViewModel.faqClicked());
        moreFragmentBinding.unlinkRow.layout.setOnClickListener(v -> moreViewModel.unLinkClicked());
        moreFragmentBinding.logoutRow.layout.setOnClickListener(v -> moreViewModel.logoutClicked());
    }

    private void initViews() {
        moreFragmentBinding.bluetoothRow.helpText.setText("Bluetooth Device Data");
        moreFragmentBinding.inviteRow.helpText.setText("Invite Friends");
        moreFragmentBinding.reportRow.helpText.setText("Report a bug");
        moreFragmentBinding.faqRow.helpText.setText("FAQ's");
        moreFragmentBinding.unlinkRow.helpText.setText("Unlink");
        moreFragmentBinding.logoutRow.helpText.setText("Logout");
    }

}

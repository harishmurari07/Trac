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
        moreFragmentBinding.addOns.layout.setOnClickListener(v -> moreViewModel.addOnsClicked());
        moreFragmentBinding.faq.layout.setOnClickListener(v -> moreViewModel.faqClicked());
        moreFragmentBinding.unlinkRow.layout.setOnClickListener(v -> {
            moreViewModel.unLinkClicked();
            moreViewModel.getDeviceUnlinkStatus().observe(getViewLifecycleOwner(), deviceUnlinkResponse -> {
                //TODO:
            });
        });

        moreFragmentBinding.changeMobile.layout.setOnClickListener(v -> moreViewModel.changeMobileNumber());
        moreFragmentBinding.bluetoothDevice.layout.setOnClickListener(v -> moreViewModel.bluetoothClicked());
        moreFragmentBinding.logoutRow.layout.setOnClickListener(v -> moreViewModel.logoutClicked());

        moreFragmentBinding.inviteRow.layout.setOnClickListener(v -> moreViewModel.inviteClicked());
        moreFragmentBinding.reportRow.layout.setOnClickListener(v -> moreViewModel.reportClicked());

    }

    private void initViews() {
        moreFragmentBinding.addOns.helpText.setText(getResources().getString(R.string.add_ons));
        moreFragmentBinding.addOns.helpImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_ons, null));
        moreFragmentBinding.faq.helpText.setText(getResources().getString(R.string.faq));
        moreFragmentBinding.unlinkRow.helpText.setText(getResources().getString(R.string.unlink));

        moreFragmentBinding.changeMobile.helpText.setText(getResources().getString(R.string.change_mobile_number));
        moreFragmentBinding.changeMobile.helpImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_phone, null));
        moreFragmentBinding.bluetoothDevice.helpText.setText(getResources().getString(R.string.bluetooth_device));
        moreFragmentBinding.bluetoothDevice.helpImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_bluetooth, null));
        moreFragmentBinding.logoutRow.helpText.setText(getResources().getString(R.string.logout));

        moreFragmentBinding.inviteRow.helpText.setText(getResources().getString(R.string.invite_friends));
        moreFragmentBinding.inviteRow.helpImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_invite_friends, null));
        moreFragmentBinding.reportRow.helpText.setText(getResources().getString(R.string.report));
        moreFragmentBinding.reportRow.helpImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_send, null));
    }

}

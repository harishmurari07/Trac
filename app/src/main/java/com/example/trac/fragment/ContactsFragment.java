package com.example.trac.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trac.R;
import com.example.trac.adapter.ContactsAdapter;
import com.example.trac.data.ContactsData;
import com.example.trac.databinding.ContactsFragmentBinding;
import com.example.trac.viewmodel.ContactsDataViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private ContactsFragmentBinding contactsFragmentBinding;
    private BottomSheetBehavior bottomSheetBehavior;
    private ContactsDataViewModel contactsDataViewModel;
    private List<ContactsData> contactsDataList;
    private ContactsAdapter contactsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contactsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.contacts_fragment, container, false);
        contactsDataViewModel = ViewModelProviders.of(this).get(ContactsDataViewModel.class);

        contactsFragmentBinding.addContacts.setOnClickListener(v -> requestAndAccessContacts());
        return contactsFragmentBinding.getRoot();
    }

    private void requestAndAccessContacts() {
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            //Permission not granted
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            //Permission already granted
            openContactsView();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openContactsView();
            } else {
                Toast.makeText(getContext(), "You have disabled contacts permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void openContactsView() {
        bottomSheetBehavior = BottomSheetBehavior.from(contactsFragmentBinding.bottomSheet);
//        contactsDataViewModel = ViewModelProviders.of(this).get(ContactsDataViewModel.class);
        contactsDataViewModel = new ViewModelProvider(this).get(ContactsDataViewModel.class);
        subscribeUI();

        RecyclerView recyclerView = contactsFragmentBinding.bottomSheet.findViewById(R.id.recycler_view);
        contactsDataList = new ArrayList<>();
        contactsAdapter = new ContactsAdapter(contactsDataList);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(contactsAdapter);
        contactsDataViewModel.loadContacts();
        openBottomView();
    }

    private void openBottomView() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void subscribeUI() {
        contactsDataViewModel.getContactsList().observe(getActivity(), new Observer<List<ContactsData>>() {
            @Override
            public void onChanged(List<ContactsData> contactsList) {
                if (contactsList != null) {
                    contactsAdapter.updateContacts(contactsList);
                } else {
                    Toast.makeText(getContext(), "You don't have any contacts", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}

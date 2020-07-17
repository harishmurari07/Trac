package com.example.trac.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trac.R;
import com.example.trac.adapter.ContactsAdapter;
import com.example.trac.adapter.EmergencyAdapter;
import com.example.trac.android.Util;
import com.example.trac.data.ContactsData;
import com.example.trac.databinding.ContactsFragmentBinding;
import com.example.trac.model.SOSContact;
import com.example.trac.viewmodel.ContactsDataViewModel;
import com.example.trac.viewmodel.SOSViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ContactsFragment extends Fragment implements ContactsAdapter.OnRowClickListener {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    private BottomSheetBehavior bottomSheetBehavior;
    private ContactsDataViewModel contactsDataViewModel;
    private SOSViewModel sosViewModel;
    private ContactsAdapter contactsAdapter;
    private EmergencyAdapter emergencyAdapter;
    private RecyclerView recyclerView;
    private RecyclerView emergencyRecyclerView;
    List<ContactsData> selectedList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ContactsFragmentBinding contactsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.contacts_fragment, container, false);
        sosViewModel = new ViewModelProvider(this).get(SOSViewModel.class);
        contactsDataViewModel = new ViewModelProvider(this).get(ContactsDataViewModel.class);
        sosViewModel.getSOSList();
        subscribeContactsUI();
        subscribeUI();

        bottomSheetBehavior = BottomSheetBehavior.from(contactsFragmentBinding.bottomSheet);
        recyclerView = contactsFragmentBinding.bottomSheet.findViewById(R.id.recycler_view);
        TextView doneButton = contactsFragmentBinding.bottomSheet.findViewById(R.id.done);
        doneButton.setOnClickListener(v -> submitContacts());
        emergencyRecyclerView = contactsFragmentBinding.emergencyContactsList;

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
                Util.showToast(getContext(), "You have disabled contacts permission");
            }
        }
    }

    private void openContactsView() {
        contactsAdapter = new ContactsAdapter();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(contactsAdapter);

        contactsDataViewModel.getContacts();
        openBottomView();
    }

    private void openBottomView() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void subscribeUI() {
        contactsDataViewModel.getContactsList().observe(getViewLifecycleOwner(), contactsList -> {
            if (contactsList != null) {
                resetContactsView(contactsList);
            }
        });
    }

    private void subscribeContactsUI() {
        sosViewModel.getListResponse().observe(getViewLifecycleOwner(), sosResponse -> {
            if (sosResponse != null && sosResponse.getSosContacts() != null && sosResponse.getSosContacts().size() > 0) {
                updateEmergencyContacts(sosResponse.getSosContacts());
            }
        });
    }

    private void updateEmergencyContacts(@NonNull List<SOSContact> contacts) {
        List<ContactsData> emergencyData = new ArrayList<>();

        for (SOSContact sosContact : contacts) {
            String number = (sosContact.getSosCountry() != null) ? "+"+sosContact.getSosCountry()+ " " + sosContact.getSosMobile() : sosContact.getSosMobile();
            String name = sosContact.getSosName();

            ContactsData contactsData = new ContactsData();
            contactsData.setNumber(number);
            contactsData.setName(name);

            emergencyData.add(contactsData);
        }

        emergencyAdapter = new EmergencyAdapter(emergencyData);
        emergencyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        emergencyRecyclerView.setLayoutManager(linearLayoutManager);
        emergencyRecyclerView.setAdapter(emergencyAdapter);

    }

    private void resetContactsView(@NonNull List<ContactsData> contactsList) {
        contactsAdapter.updateContacts(contactsList);
        contactsAdapter.onRowClicked(this);
    }

    private void submitContacts() {

    }

    @Override
    public void onRowClicked(ContactsData contact) {
        if (selectedList.contains(contact)) {
            selectedList.remove(contact);
        } else {
            if (selectedList.size() < 3) {
                selectedList.add(contact);
            } else {
                Util.showToast(getContext(), "Cannot select more than 3 contacts..");
            }
        }
        Log.d(TAG, "onRowClicked: --" + contact.getNumber());
    }
}

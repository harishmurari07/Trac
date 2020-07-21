package com.example.trac.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.graphics.Paint;
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
import com.example.trac.model.SOSRequest;
import com.example.trac.viewmodel.ContactsDataViewModel;
import com.example.trac.viewmodel.SOSViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ContactsFragment extends Fragment implements ContactsAdapter.OnRowClickListener, EmergencyAdapter.EmergencyListener {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    private BottomSheetBehavior bottomSheetBehavior;
    private ContactsDataViewModel contactsDataViewModel;
    private SOSViewModel sosViewModel;
    private ContactsAdapter contactsAdapter;
    private EmergencyAdapter emergencyAdapter;
    private RecyclerView recyclerView;
    private RecyclerView emergencyRecyclerView;
    private List<ContactsData> selectedList = new ArrayList<>();
    private TextView emergencyText;

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
        contactsFragmentBinding.emergencyContactsText.setPaintFlags(contactsFragmentBinding.emergencyContactsText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        contactsFragmentBinding.emergencyMessageText.setPaintFlags(contactsFragmentBinding.emergencyMessageText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        emergencyText = contactsFragmentBinding.emergencyNotes;

        contactsFragmentBinding.addContacts.setOnClickListener(v -> requestAndAccessContacts());
        contactsFragmentBinding.editIcon.setOnClickListener(v -> openEmergencyMessageView());
        return contactsFragmentBinding.getRoot();
    }

    private void openEmergencyMessageView() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.emergency_message_popup, null);
        builder.setView(dialogView);

        TextInputEditText edit = dialogView.findViewById(R.id.emergency_message);
        edit.setText(emergencyText.getText().toString());
        edit.setSelection(emergencyText.getText().length());
        builder.setTitle(R.string.emergency_text_title)
//                .setMessage()
                .setCancelable(false)
                .setPositiveButton(R.string.save, (dialog, which) -> {
                    edit.getEditableText().toString();
                }).setNegativeButton(R.string.cancel, null);
        builder.create();
        builder.show();
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
                updateEmergencyText(sosResponse.getSosMessage());
            }
        });
    }

    private void updateEmergencyText(String message) {
        emergencyText.setText(message);
    }

    private void updateEmergencyContacts(@NonNull List<SOSContact> contacts) {
        List<ContactsData> emergencyData = new ArrayList<>();

        for (SOSContact sosContact : contacts) {
            String number = (sosContact.getSosCountry() != null) ? sosContact.getSosCountry() + " " + sosContact.getSosMobile() : sosContact.getSosMobile();
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
        emergencyAdapter.onEmergencyListener(this);
        emergencyAdapter.notifyDataSetChanged();
        selectedList = emergencyData;
    }

    private void resetContactsView(@NonNull List<ContactsData> contactsList) {
        contactsAdapter.updateContacts(contactsList);
        contactsAdapter.onRowClicked(this);
    }

    private void submitContacts() {
//        bottomSheetBehavior.getState();
        List<SOSContact> updateEmergencyContacts = new ArrayList<>();

        for (ContactsData contactsData : selectedList) {
            String name = contactsData.getName();
            String number = contactsData.getNumber();

            updateEmergencyContacts.add(new SOSContact(name, "+91", number));
        }
        sosViewModel.updateSOSList(new SOSRequest(emergencyText.getText().toString(), updateEmergencyContacts));
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

    @Override
    public void call(int position) {
        Util.showToast(getContext(), "Calling" + selectedList.get(position).getName());
    }

    @Override
    public void message(int position) {
        Util.showToast(getContext(), "Message Sent" + selectedList.get(position).getName());
    }

    @Override
    public void delete(int position) {
        emergencyAdapter.deleteContact(position);
    }

}

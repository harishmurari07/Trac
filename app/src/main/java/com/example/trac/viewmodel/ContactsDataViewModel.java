package com.example.trac.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trac.data.ContactsData;
import com.example.trac.repository.ContactsRepository;

import java.util.ArrayList;
import java.util.List;

public class ContactsDataViewModel extends AndroidViewModel {

    private MutableLiveData<List<ContactsData>> contacts = new MutableLiveData<>(null);
    private List<ContactsData> contactsDataList = new ArrayList<>();
    private ContactsRepository contactsRepository;

    public ContactsDataViewModel(@NonNull Application application) {
        super(application);
        contactsRepository = new ContactsRepository();
    }

    private void setContacts(List<ContactsData> contactsList) {
        contacts.setValue(contactsList);
    }

    public LiveData<List<ContactsData>> getContactsList() {
        return contacts;
    }

    public void getContacts() {
        if (contacts.getValue() == null) {
            contactsDataList = contactsRepository.loadContacts();
            setContacts(contactsDataList);
        } else {
            setContacts(contactsDataList);
        }
    }

}

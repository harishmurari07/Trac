package com.example.trac.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trac.data.ContactsData;
import com.example.trac.repository.ContactsRepository;

import java.util.List;

public class ContactsDataViewModel extends ViewModel {

    private MutableLiveData<List<ContactsData>> contacts = new MutableLiveData<>();
    private ContactsRepository contactsRepository;

    public ContactsDataViewModel() {
        contactsRepository = new ContactsRepository();
    }

    private void setContacts(List<ContactsData> contactsList) {
        contacts.setValue(contactsList);
    }

    public LiveData<List<ContactsData>> getContactsList() {
        return contacts;
    }

    public void getContacts() {
        setContacts(contactsRepository.loadContacts());
    }
}

package com.example.trac.viewmodel;


import android.app.Application;
import android.database.Cursor;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trac.android.Util;
import com.example.trac.data.ContactsData;

import java.util.ArrayList;
import java.util.List;

public class ContactsDataViewModel extends AndroidViewModel {

    private final MutableLiveData<List<ContactsData>> contacts = new MutableLiveData<>();
    private final Application application;

    public ContactsDataViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public void setContacts(List<ContactsData> contactsList) {
        contacts.setValue(contactsList);
    }

    public LiveData<List<ContactsData>> getContactsList() {
        return contacts;
    }

    public void loadContacts() {
        List<ContactsData> contactsDataList = null;
        //Pass all the contacts to cursor
        Cursor cursor = application.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);

        if ((cursor != null ? cursor.getCount() : 0) > 0) {
            contactsDataList = new ArrayList<>();
            while (cursor.moveToNext()) {

                String name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                String number = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));

                ContactsData contactsData = new ContactsData();
                contactsData.setNumber(number);
                contactsData.setName(name);

                contactsDataList.add(contactsData);
            }
            Util.showToast(application, "Contacts Loaded");
            setContacts(contactsDataList);
        } else {
            setContacts(contactsDataList);
            Util.showToast(application, "You don't have any contacts");
        }
    }

}

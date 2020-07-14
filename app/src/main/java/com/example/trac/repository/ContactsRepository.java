package com.example.trac.repository;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.trac.android.TracApplication;
import com.example.trac.data.ContactsData;

import java.util.ArrayList;
import java.util.List;

public class ContactsRepository {

    public List<ContactsData> loadContacts() {
        List<ContactsData> contactsDataList = null;
        //Pass all the contacts to cursor
        Cursor cursor = TracApplication.getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.DISPLAY_NAME_PRIMARY);

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
            return contactsDataList;
        } else {
            return contactsDataList;
        }
    }

}

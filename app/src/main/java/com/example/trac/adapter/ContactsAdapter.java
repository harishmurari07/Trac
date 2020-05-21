package com.example.trac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trac.R;
import com.example.trac.data.ContactsData;
import com.example.trac.databinding.ContactRowBinding;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private ContactRowBinding contactRowBinding;
    private List<ContactsData> contactsDataList;

    public ContactsAdapter(List<ContactsData> contactsData) {
        contactsDataList = contactsData;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        contactRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.contact_row, parent, false);
        return new ContactsViewHolder(contactRowBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.setContacts(contactsDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return contactsDataList.size();
    }

    public void updateContacts(List<ContactsData> newContactsDataList) {
        this.contactsDataList.clear();
        this.contactsDataList.addAll(newContactsDataList);
        notifyDataSetChanged();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void setContacts(ContactsData contact) {
            contactRowBinding.contactName.setText(contact.getName());
            contactRowBinding.contactNumber.setText(contact.getNumber());
        }
    }

}

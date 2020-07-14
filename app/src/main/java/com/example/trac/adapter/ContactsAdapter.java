package com.example.trac.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trac.R;
import com.example.trac.data.ContactsData;
import com.example.trac.databinding.ContactRowBinding;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private List<ContactsData> contactsDataList;

    public ContactsAdapter(List<ContactsData> contactsData) {
        contactsDataList = contactsData;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactRowBinding contactRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.contact_row, parent, false);
        return new ContactsViewHolder(contactRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.name.setText(contactsDataList.get(position).getName());
        holder.number.setText(contactsDataList.get(position).getNumber());
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

        public TextView name, number;

        public ContactsViewHolder(@NonNull ContactRowBinding itemView) {
            super(itemView.getRoot());
            name = itemView.contactName;
            number = itemView.contactNumber;
        }

    }

}

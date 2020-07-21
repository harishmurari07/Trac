package com.example.trac.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trac.R;
import com.example.trac.data.ContactsData;
import com.example.trac.databinding.EmergencyContactsRowBinding;

import java.util.List;

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.EmergencyViewHolder> {

    private List<ContactsData> emergencyContacts;
    private EmergencyListener listener;

    public EmergencyAdapter(List<ContactsData> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    @NonNull
    @Override
    public EmergencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EmergencyContactsRowBinding emergencyContactsRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.emergency_contacts_row, parent, false);
        return new EmergencyViewHolder(emergencyContactsRowBinding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergencyViewHolder holder, int position) {
        holder.name.setText(emergencyContacts.get(position).getName());
        holder.number.setText(emergencyContacts.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return emergencyContacts.size();
    }

    public class EmergencyViewHolder extends RecyclerView.ViewHolder {

        private TextView name, number;

        public EmergencyViewHolder(@NonNull EmergencyContactsRowBinding itemView, EmergencyListener listener) {
            super(itemView.getRoot());
            name = itemView.emergencyContactName;
            number = itemView.emergencyContactNumber;

            itemView.emergencyCallIcon.setOnClickListener(v -> {
                if (listener != null) {
                    listener.call(getBindingAdapterPosition());
                }
            });
            itemView.emergencyMessageIcon.setOnClickListener(v -> {
                if (listener != null) {
                    listener.message(getBindingAdapterPosition());
                }
            });

            itemView.emergencyDeleteIcon.setOnClickListener(v -> {
                if (listener != null) {
                    listener.delete(getBindingAdapterPosition());
                }
            });
        }
    }

    public void onEmergencyListener(EmergencyListener emergencyListener) {
        listener = emergencyListener;
    }

    public void deleteContact(int position) {
        emergencyContacts.remove(position);
        notifyDataSetChanged();
    }

    public interface EmergencyListener {
        void call(int position);

        void message(int position);

        void delete(int position);
    }

}

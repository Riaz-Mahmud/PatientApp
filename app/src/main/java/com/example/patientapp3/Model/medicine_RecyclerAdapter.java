package com.example.patientapp3.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientapp3.R;

import java.util.List;


public class medicine_RecyclerAdapter extends RecyclerView.Adapter<medicine_RecyclerAdapter.MyViewHolder> {
    private List<medicine_Contact> contacts;
    private Context mContext;
    private static final int REQUEST_CALL = 1;

    public medicine_RecyclerAdapter(List<medicine_Contact> contacts, Context context) {
        this.contacts = contacts;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_medicine_list, parent, false);
        return new MyViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.medicineName.setText(contacts.get(position).getName());
        holder.medicineCause.setText("Cause: " + contacts.get(position).getCause());

    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView medicineName,medicineCause;
        LinearLayout parentLayout;

        MyViewHolder(View itemView) {
            super(itemView);
            medicineName = itemView.findViewById(R.id.medicineName);
            medicineCause = itemView.findViewById(R.id.medicineCause);
            parentLayout = itemView.findViewById(R.id.parentLayoutForMedicineList);
        }
    }


}


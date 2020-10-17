package com.example.patientapp3.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientapp3.HospitalList_Activity;
import com.example.patientapp3.R;

import java.util.List;


public class Donor_RecyclerAdapter extends RecyclerView.Adapter<Donor_RecyclerAdapter.MyViewHolder> {
    private List<Donor_Contact> contacts;
    private Context mContext;
    private static final int REQUEST_CALL = 1;

    public Donor_RecyclerAdapter(List<Donor_Contact> contacts, Context context) {
        this.contacts = contacts;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_donar_list, parent, false);
        return new MyViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.DonorName.setText(contacts.get(position).getName());
        holder.bloodGroup.setText("Blood Group: " + contacts.get(position).getBloodGroup());
        holder.Number.setText("Number: " + contacts.get(position).getNumber());

    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView DonorName,bloodGroup,Number;
        LinearLayout parentLayout;

        MyViewHolder(View itemView) {
            super(itemView);
            DonorName = itemView.findViewById(R.id.DonorName);
            bloodGroup = itemView.findViewById(R.id.bloodGroup);
            Number = itemView.findViewById(R.id.Number);
            parentLayout = itemView.findViewById(R.id.parentLayoutForDonorList);
        }
    }


}


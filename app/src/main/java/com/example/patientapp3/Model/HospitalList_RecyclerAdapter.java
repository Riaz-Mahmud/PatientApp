package com.example.patientapp3.Model;

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


public class HospitalList_RecyclerAdapter extends RecyclerView.Adapter<HospitalList_RecyclerAdapter.MyViewHolder> {
    private List<HospitalList_Contact> contacts;
    private Context mContext;
    private static final int REQUEST_CALL = 1;

    public HospitalList_RecyclerAdapter(List<HospitalList_Contact> contacts, Context context) {
        this.contacts = contacts;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_hospital_list, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.hospitalName.setText("Name: " + contacts.get(position).getName());
        holder.hospitalLoc.setText("Location: " + contacts.get(position).getLoc());

    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView hospitalName,hospitalLoc;
        LinearLayout parentLayout;

        MyViewHolder(View itemView) {
            super(itemView);
            hospitalName = itemView.findViewById(R.id.hospitalName);
            hospitalLoc = itemView.findViewById(R.id.hospitalLoc);
            parentLayout = itemView.findViewById(R.id.parentLayoutForHospitalList);
        }
    }


}


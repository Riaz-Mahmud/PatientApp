package com.example.patientapp3.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientapp3.HospitalList_Activity;
import com.example.patientapp3.R;

import java.util.List;


public class Doctor_RecyclerAdapter extends RecyclerView.Adapter<Doctor_RecyclerAdapter.MyViewHolder> {
    private List<Doctor_Contact> contacts;
    private Context mContext;
    private static final int REQUEST_CALL = 1;

    public Doctor_RecyclerAdapter(List<Doctor_Contact> contacts, Context context) {
        this.contacts = contacts;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_doctor_list, parent, false);
        return new MyViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.DoctorName.setText(contacts.get(position).getName());
        holder.hospitalLoc.setText("Hospital: " + contacts.get(position).getHospital());
        holder.expert.setText("Expert: "+ contacts.get(position).getExpert());
        holder.day.setText("Days: " + contacts.get(position).getDay());
        holder.shift.setText("Time: " + contacts.get(position).getShift());

        holder.callDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+contacts.get(position).getNumber()));
                mContext.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView DoctorName,hospitalLoc,expert,day,shift;
        Button callDoctor;
        LinearLayout parentLayout;

        MyViewHolder(View itemView) {
            super(itemView);
            DoctorName = itemView.findViewById(R.id.DoctorName);
            hospitalLoc = itemView.findViewById(R.id.hospitalLoc);
            expert = itemView.findViewById(R.id.expert);
            day = itemView.findViewById(R.id.day);
            shift = itemView.findViewById(R.id.shift);
            callDoctor = itemView.findViewById(R.id.callDoctor);
            parentLayout = itemView.findViewById(R.id.parentLayoutForDoctorList);
        }
    }


}


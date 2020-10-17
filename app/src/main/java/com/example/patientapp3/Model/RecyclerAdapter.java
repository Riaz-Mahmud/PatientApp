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


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<Contact> contacts;
    private Context mContext;
    private static final int REQUEST_CALL = 1;

    public RecyclerAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.locName.setText(contacts.get(position).getName());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HospitalList_Activity.class);
                intent.putExtra("hospitalLoc", contacts.get(position).getName());
                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView locName;
        LinearLayout parentLayout;

        MyViewHolder(View itemView) {
            super(itemView);
            locName = itemView.findViewById(R.id.locName);
            parentLayout = itemView.findViewById(R.id.parentLayoutForHospitalLoc);
        }
    }


}


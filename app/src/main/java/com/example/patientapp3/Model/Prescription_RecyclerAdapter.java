package com.example.patientapp3.Model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientapp3.HospitalList_Activity;
import com.example.patientapp3.ImageFullScreen_Activity;
import com.example.patientapp3.R;
import com.example.patientapp3.network.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Prescription_RecyclerAdapter extends RecyclerView.Adapter<Prescription_RecyclerAdapter.MyViewHolder> {
    private List<Prescription_Contact> contacts;
    private Context mContext;

    public Prescription_RecyclerAdapter(List<Prescription_Contact> contacts, Context context) {
        this.contacts = contacts;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_prescription, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final String imageURl = contacts.get(position).getImage();
        final String base_url = ApiClient.BASE_URL;
        Picasso.get().load(base_url + imageURl).resize(100, 100).centerCrop().into(holder.ImageShow);

        holder.ImageShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( mContext, ImageFullScreen_Activity.class);
                intent.setData(Uri.parse(base_url+imageURl));
                intent.putExtra("imageId", contacts.get(position).getId());
                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        if (contacts==null){
            return 0;
        }else {
            return contacts.size();
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ImageShow;
        LinearLayout parentLayout;

        MyViewHolder(View itemView) {
            super(itemView);
            ImageShow = itemView.findViewById(R.id.prescriptionShow);
            parentLayout = itemView.findViewById(R.id.parentLayoutForPrescription);
        }
    }


}


package com.example.patientapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.patientapp3.Model.Doctor_Contact;
import com.example.patientapp3.Model.Doctor_RecyclerAdapter;
import com.example.patientapp3.Model.HospitalList_Contact;
import com.example.patientapp3.Model.HospitalList_RecyclerAdapter;
import com.example.patientapp3.network.ApiClient;
import com.example.patientapp3.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doctor extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Doctor_RecyclerAdapter adapter;

    private List<Doctor_Contact> contacts;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);


        recyclerView = findViewById(R.id.recycleViewDoctorList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Doctor.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        ShowAllDoctorList();


    }

    private void ShowAllDoctorList() {
        if (checkConnection()) {

            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            final Call<List<Doctor_Contact>> call = apiInterface.getDoctorList("none");

            call.enqueue(new Callback<List<Doctor_Contact>>() {
                @Override
                public void onResponse(Call<List<Doctor_Contact>> call, Response<List<Doctor_Contact>> response) {
                    if (response.isSuccessful()) {
                        setDoctorListRecyclerview(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Doctor_Contact>> call, Throwable t) {

                }
            });
        }
    }

    private void setDoctorListRecyclerview(List<Doctor_Contact> body) {
        contacts = body;
        adapter = new Doctor_RecyclerAdapter(contacts, Doctor.this);
        recyclerView.setAdapter(adapter);
    }

    public boolean checkConnection() {
        ConnectivityManager manager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if (activeNetwork == null) {
            MainActivity.perfConfig.displayToast("No Internet Connection");
            return false;

        } else {
            return true;
        }
    }


}
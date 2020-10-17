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
import com.example.patientapp3.Model.Donor_Contact;
import com.example.patientapp3.Model.Donor_RecyclerAdapter;
import com.example.patientapp3.network.ApiClient;
import com.example.patientapp3.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bloodbank extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Donor_RecyclerAdapter adapter;

    private List<Donor_Contact> contacts;
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodbank);


        recyclerView = findViewById(R.id.recycleViewDonorList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Bloodbank.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ShowAllDonorList();

    }

    private void ShowAllDonorList() {
        if (checkConnection()) {

            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            final Call<List<Donor_Contact>> call = apiInterface.getDonorList("none");

            call.enqueue(new Callback<List<Donor_Contact>>() {
                @Override
                public void onResponse(Call<List<Donor_Contact>> call, Response<List<Donor_Contact>> response) {
                    if (response.isSuccessful()) {
                        setDonorListRecyclerview(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Donor_Contact>> call, Throwable t) {
                    MainActivity.perfConfig.displayToast("Somethings Wrong!");
                }
            });
        }
    }

    private void setDonorListRecyclerview(List<Donor_Contact> body) {
        contacts = body;
        adapter = new Donor_RecyclerAdapter(contacts, Bloodbank.this);
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
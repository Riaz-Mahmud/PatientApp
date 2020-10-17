package com.example.patientapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.example.patientapp3.Model.Contact;
import com.example.patientapp3.Model.RecyclerAdapter;
import com.example.patientapp3.network.ApiClient;
import com.example.patientapp3.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hospital extends AppCompatActivity {

    private RecyclerView recycleViewHospital;
    private RecyclerAdapter adapter;

    private List<Contact> contacts;
    private ApiInterface apiInterface;

    ArrayList<String> hospitalNameArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        recycleViewHospital = findViewById(R.id.recycleViewHospital);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Hospital.this);
        recycleViewHospital.setLayoutManager(layoutManager);
        recycleViewHospital.setHasFixedSize(true);

        ShowAllHospitalLoc();

    }

    private void ShowAllHospitalLoc() {
        if (checkConnection()) {

            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            final Call<List<Contact>> call = apiInterface.getHospitalLoc("none");

            call.enqueue(new Callback<List<Contact>>() {
                @Override
                public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                    if (response.isSuccessful()) {
                        setHospitalLocRecyclerview(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Contact>> call, Throwable t) {
                    Log.d("ShowGroupMember", String.valueOf(t));
                    MainActivity.perfConfig.displayToast("Error:" + t);
                    //progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    private void setHospitalLocRecyclerview(List<Contact> body) {
        contacts = body;
        adapter = new RecyclerAdapter(contacts, Hospital.this);
        recycleViewHospital.setAdapter(adapter);
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
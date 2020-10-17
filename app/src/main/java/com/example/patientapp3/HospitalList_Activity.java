package com.example.patientapp3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientapp3.Model.HospitalList_Contact;
import com.example.patientapp3.Model.HospitalList_RecyclerAdapter;
import com.example.patientapp3.network.ApiClient;
import com.example.patientapp3.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalList_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HospitalList_RecyclerAdapter adapter;

    private List<HospitalList_Contact> contacts;
    private ApiInterface apiInterface;
    String hospitalLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list_);

        if (getIntent().hasExtra("hospitalLoc")) {
            hospitalLoc = getIntent().getStringExtra("hospitalLoc");
        } else {
            MainActivity.perfConfig.displayToast("SomeThing Wrong!!");
        }

        recyclerView = findViewById(R.id.recycleViewHospitalList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HospitalList_Activity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ShowAllHospitalList(hospitalLoc);
    }

    private void ShowAllHospitalList(String Location) {
        if (checkConnection()) {

            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            final Call<List<HospitalList_Contact>> call = apiInterface.getHospitalList(Location);

            call.enqueue(new Callback<List<HospitalList_Contact>>() {
                @Override
                public void onResponse(Call<List<HospitalList_Contact>> call, Response<List<HospitalList_Contact>> response) {
                    if (response.isSuccessful()) {
                        setHospitalListRecyclerview(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<HospitalList_Contact>> call, Throwable t) {

                }
            });
        }
    }

    private void setHospitalListRecyclerview(List<HospitalList_Contact> body) {
        contacts = body;
        adapter = new HospitalList_RecyclerAdapter(contacts, HospitalList_Activity.this);
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
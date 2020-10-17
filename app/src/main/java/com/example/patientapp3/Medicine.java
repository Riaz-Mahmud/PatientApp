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
import com.example.patientapp3.Model.medicine_Contact;
import com.example.patientapp3.Model.medicine_RecyclerAdapter;
import com.example.patientapp3.network.ApiClient;
import com.example.patientapp3.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Medicine extends AppCompatActivity {

    private RecyclerView recyclerView;
    private medicine_RecyclerAdapter adapter;

    private List<medicine_Contact> contacts;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);


        recyclerView = findViewById(R.id.recycleViewMedicineList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Medicine.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ShowAllMedicineList();
    }


    private void ShowAllMedicineList() {
        if (checkConnection()) {

            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            final Call<List<medicine_Contact>> call = apiInterface.getMedicineList("none");

            call.enqueue(new Callback<List<medicine_Contact>>() {
                @Override
                public void onResponse(Call<List<medicine_Contact>> call, Response<List<medicine_Contact>> response) {
                    if (response.isSuccessful()) {
                        setMedicineListRecyclerview(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<medicine_Contact>> call, Throwable t) {
                    MainActivity.perfConfig.displayToast("Somethings wrong!");
                }
            });
        }
    }

    private void setMedicineListRecyclerview(List<medicine_Contact> body) {
        contacts = body;
        adapter = new medicine_RecyclerAdapter(contacts, Medicine.this);
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
package com.example.patientapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.patientapp3.network.ApiClient;
import com.example.patientapp3.network.ApiInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.patientapp3.UserDashBoard_Activity.perfConfig;

public class ImageFullScreen_Activity extends AppCompatActivity {

    ImageView imageShow;
    Uri ImageURL;
    String ImageId;

    Button ImageDeleteBtn;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_screen_);

        imageShow = findViewById(R.id.imageShow);
        ImageDeleteBtn = findViewById(R.id.ImageDeleteBtn);

        Intent callingActivity = getIntent();
        if (callingActivity != null) {
            ImageURL = callingActivity.getData();
            ImageId = getIntent().getStringExtra("imageId");

            if (ImageURL != null) {
                ProgressDialog loadingBar = new ProgressDialog(this);
                loadingBar.setMessage("Loading...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                try {

                    Picasso.get().load(ImageURL).into(imageShow);
                } catch (Exception e) {
                    UserDashBoard_Activity.perfConfig.displayToast("Error" + e);
                }
                loadingBar.cancel();
            } else {
                UserDashBoard_Activity.perfConfig.displayToast("Something Wrong!!");
            }

            ImageDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkConnection()) {
                        deleteImage(ImageId);
                    }
                }
            });

        } else {
            UserDashBoard_Activity.perfConfig.displayToast("Something Wrong!!");
        }

    }

    private void deleteImage(String imageId) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.getDeleteUserImageResponse(imageId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.body().equals("1")) {
                        perfConfig.displayToast("Delete Successfully!");

                        Intent intent = new Intent(ImageFullScreen_Activity.this, Prescription_Activity.class);
                        finish();
                        startActivity(intent);

                    } else if (response.body().equals("2")) {
                        perfConfig.displayToast("Something wrong!");
                    } else {
                        perfConfig.displayToast("Something wrong!");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                perfConfig.displayToast("Server Max time reach! Please Try again!");
            }
        });

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
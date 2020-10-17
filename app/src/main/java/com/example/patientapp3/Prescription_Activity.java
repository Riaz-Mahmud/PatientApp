package com.example.patientapp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.patientapp3.Model.Contact;
import com.example.patientapp3.Model.Doctor_Contact;
import com.example.patientapp3.Model.Doctor_RecyclerAdapter;
import com.example.patientapp3.Model.Prescription_Contact;
import com.example.patientapp3.Model.Prescription_RecyclerAdapter;
import com.example.patientapp3.Model.RecyclerAdapter;
import com.example.patientapp3.network.ApiClient;
import com.example.patientapp3.network.ApiInterface;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.patientapp3.UserDashBoard_Activity.perfConfig;

public class Prescription_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    Button uploadPicture;
    private Prescription_RecyclerAdapter adapter;

    private List<Prescription_Contact> contacts;
    private ApiInterface apiInterface;

    private static final int IMG_REQUEST = 777;
    static int PrefCode = 1;
    private Bitmap bitmap, bitmapThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_);

        uploadPicture = findViewById(R.id.uploadPicture);

        recyclerView = findViewById(R.id.recycleViewPrescription);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Prescription_Activity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        uploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAndroidVersion();
            }
        });

        ShowAllPrescription();

    }

    private void ShowAllPrescription() {
        if (checkConnection()) {

            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            final Call<List<Prescription_Contact>> call = apiInterface.getPrescriptionList(perfConfig.readUsername());

            call.enqueue(new Callback<List<Prescription_Contact>>() {
                @Override
                public void onResponse(Call<List<Prescription_Contact>> call, Response<List<Prescription_Contact>> response) {
                    if (response.isSuccessful()) {
                        setDoctorListRecyclerview(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Prescription_Contact>> call, Throwable t) {

                }
            });
        }
    }

    private void setDoctorListRecyclerview(List<Prescription_Contact> body) {
        contacts = body;
        adapter = new Prescription_RecyclerAdapter(contacts, Prescription_Activity.this);
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

    //New Select Image Function Start
    private void CheckAndroidVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(Prescription_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(Prescription_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(Prescription_Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 555);
            } else {
                pickImageDirect();
            }
        } else {
            pickImageDirect();
        }
    }

    public void pickImageDirect() {
        CropImage.startPickImageActivity(this);
    }

    public void cropRequest(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);
            Log.d("SelectImage", "NewImageSelectOption: 1");
            cropRequest(imageUri);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getUri());
                    Log.d("SelectImage", "NewImageSelectOption: 2");

                    uploadUserImage();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                perfConfig.displayToast("Something Wrong! Please try again!");
            }
        }

    }

    private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 555 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImageDirect();
        } else {
            perfConfig.displayToast("You need to allow Permission");
        }
    }

    private void uploadUserImage() {

        String Image = imageToString();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.getUploadUserImageResponse(Image, perfConfig.readUsername());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.body().equals("1")) {
                        perfConfig.displayToast("Image Uploaded");

                        ShowAllPrescription();

                    } else if (response.body().equals("0")) {
                        perfConfig.displayToast("Server Error!");
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



}
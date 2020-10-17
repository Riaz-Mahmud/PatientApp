package com.example.patientapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class userProfile_Activity extends AppCompatActivity {

    TextView usernameProfile,nameProfile,numberProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        usernameProfile = findViewById(R.id.usernameProfile);
        nameProfile = findViewById(R.id.nameProfile);
        numberProfile = findViewById(R.id.numberProfile);

        usernameProfile.setText(UserDashBoard_Activity.perfConfig.readUsername());
        nameProfile.setText(UserDashBoard_Activity.perfConfig.readName());
        numberProfile.setText(UserDashBoard_Activity.perfConfig.readNumber());
    }
}
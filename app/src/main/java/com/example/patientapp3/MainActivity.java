package com.example.patientapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.content.Intent;
import android.view.View;

import android.os.Bundle;

import com.example.patientapp3.Model.PerfConfig;

public class MainActivity extends AppCompatActivity {
    public RelativeLayout relativeLayout;

    @SuppressLint("StaticFieldLeak")
    public static PerfConfig perfConfig;

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        perfConfig = new PerfConfig(this);

        if (perfConfig.readLoginStatus()) {
            Intent intent = new Intent(MainActivity.this, UserDashBoard_Activity.class);
            finish();
            startActivity(intent);
        } else {
            relativeLayout = (RelativeLayout) findViewById(R.id.hospital);
            loginButton = findViewById(R.id.loginButton);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
            });


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Hospital.class);
                    startActivity(intent);

                }
            });
            relativeLayout = (RelativeLayout) findViewById(R.id.doctor);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Doctor.class);
                    startActivity(intent);

                }
            });
            relativeLayout = (RelativeLayout) findViewById(R.id.medicine);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Medicine.class);
                    startActivity(intent);

                }
            });
            relativeLayout = (RelativeLayout) findViewById(R.id.health);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Healthtips.class);
                    startActivity(intent);

                }
            });
            relativeLayout = (RelativeLayout) findViewById(R.id.bloodbank);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Bloodbank.class);
                    startActivity(intent);

                }
            });
            relativeLayout = (RelativeLayout) findViewById(R.id.chart);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Chart.class);
                    startActivity(intent);

                }
            });
        }

    }
}
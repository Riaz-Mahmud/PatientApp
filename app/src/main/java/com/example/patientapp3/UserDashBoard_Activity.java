package com.example.patientapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.patientapp3.Model.PerfConfig;

public class UserDashBoard_Activity extends AppCompatActivity {

    TextView nameOfUser;
    ImageView logOutButton;
    public RelativeLayout relativeLayout;

    public static PerfConfig perfConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash_board_);
        perfConfig = new PerfConfig(this);
        if (perfConfig.readLoginStatus()) {

            nameOfUser = findViewById(R.id.nameOfUser);
            logOutButton = findViewById(R.id.logOutButton);

            nameOfUser.setText(perfConfig.readName());
            logOutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setLogOut();
                }
            });

            relativeLayout = (RelativeLayout) findViewById(R.id.profileRelLayout);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserDashBoard_Activity.this, userProfile_Activity.class);
                    startActivity(intent);

                }
            });

            relativeLayout = (RelativeLayout) findViewById(R.id.precRelLayout);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserDashBoard_Activity.this, Prescription_Activity.class);
                    startActivity(intent);

                }
            });

            relativeLayout = (RelativeLayout) findViewById(R.id.hospital);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserDashBoard_Activity.this, Hospital.class);
                    startActivity(intent);

                }
            });
            relativeLayout = (RelativeLayout) findViewById(R.id.doctor);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserDashBoard_Activity.this, Doctor.class);
                    startActivity(intent);

                }
            });
            relativeLayout = (RelativeLayout) findViewById(R.id.medicine);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserDashBoard_Activity.this, Medicine.class);
                    startActivity(intent);

                }
            });
            relativeLayout = (RelativeLayout) findViewById(R.id.health);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserDashBoard_Activity.this, Healthtips.class);
                    startActivity(intent);

                }
            });
            relativeLayout = (RelativeLayout) findViewById(R.id.bloodbank);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserDashBoard_Activity.this, Bloodbank.class);
                    startActivity(intent);

                }
            });


            relativeLayout = (RelativeLayout) findViewById(R.id.chart);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserDashBoard_Activity.this, Chart.class);
                    startActivity(intent);

                }
            });

            relativeLayout = (RelativeLayout) findViewById(R.id.SymptomsChecker);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserDashBoard_Activity.this, SymptomsChecker_Activity.class);
                    startActivity(intent);

                }
            });

        }

    }

    private void setLogOut() {
        perfConfig.writeLoginStatus(false);
        perfConfig.writeNumber("number");
        perfConfig.writeName("name");
        perfConfig.writeUsername("username");

        Intent intent = new Intent(UserDashBoard_Activity.this, MainActivity.class);
        finish();
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
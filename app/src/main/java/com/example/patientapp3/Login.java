package com.example.patientapp3;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.patientapp3.Model.PerfConfig;
import com.example.patientapp3.Model.UserInformation;
import com.example.patientapp3.network.ApiClient;
import com.example.patientapp3.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    TextView signUpTxt, errorTxtShow;
    EditText usernameEditTxt, passwordEditTxt;
    Button LoginButton;
    private ApiInterface apiInterface;
    private ProgressDialog LoadingBar;
    public static PerfConfig perfConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        perfConfig = new PerfConfig(this);

        signUpTxt = findViewById(R.id.signUpTxt);
        usernameEditTxt = findViewById(R.id.usernameEditTxt);
        passwordEditTxt = findViewById(R.id.passwordEditTxt);
        errorTxtShow = findViewById(R.id.errorTxtShow);
        LoginButton = findViewById(R.id.LoginButton);

        signUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserInfo();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkUserInfo() {

        String userName = usernameEditTxt.getText().toString().trim().toLowerCase();
        String password = passwordEditTxt.getText().toString().trim();

        if (userName.isEmpty() || password.isEmpty()) {
            if (userName.isEmpty()) {
                errorTxtShow.setText("Username Empty!");
            } else if (password.isEmpty()) {
                errorTxtShow.setText("Password Empty!");
            }
        } else {
            checkDatabaseLoginInfo(userName, password);
        }
    }

    private void checkDatabaseLoginInfo(final String username, String password) {
        LoginButton.setEnabled(false);
        errorTxtShow.setText(null);

        if (checkConnection()){
            LoadingBar = new ProgressDialog(Login.this);
            LoadingBar.setMessage("Loading...");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();

            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<UserInformation> call = apiInterface.getLoginResponse(username,password);

            call.enqueue(new Callback<UserInformation>() {
                @Override
                public void onResponse(Call<UserInformation> call, Response<UserInformation> response) {
                    if (response.isSuccessful()){
                        if (response.body().getResponse().equals("ok")){
                            perfConfig.writeLoginStatus(true);
                            perfConfig.writeUsername(username);
                            perfConfig.writeName(response.body().getName());
                            perfConfig.writeNumber(response.body().getNumber());
                            usernameEditTxt.setText("");
                            passwordEditTxt.setText("");
                            LoginButton.setEnabled(true);
                            perfConfig.displayToast("Welcome " + response.body().getName());

                            Intent intent = new Intent(Login.this, UserDashBoard_Activity.class);
                            finish();
                            startActivity(intent);
                        }else {
                            perfConfig.displayToast("Authentication Failed");
                            LoginButton.setEnabled(true);
                        }
                    }
                    LoadingBar.cancel();
                }

                @Override
                public void onFailure(Call<UserInformation> call, Throwable t) {
                    perfConfig.displayToast("Somethings Wrong!");
                }
            });

        }


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
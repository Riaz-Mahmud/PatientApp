package com.example.patientapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.patientapp3.Model.PerfConfig;
import com.example.patientapp3.Model.UserInformation;
import com.example.patientapp3.network.ApiClient;
import com.example.patientapp3.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp_Activity extends AppCompatActivity {

    Button signUpButton;
    EditText nameSignUp,numberSignUp,usernameSignUp,passwordSignUp;
    TextView errorTxt;

    private ProgressDialog LoadingBar;
    public static PerfConfig perfConfig;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        perfConfig = new PerfConfig(this);

        signUpButton = findViewById(R.id.signUpButton);
        nameSignUp = findViewById(R.id.nameSignUp);
        numberSignUp = findViewById(R.id.numberSignUp);
        usernameSignUp = findViewById(R.id.usernameSignUp);
        passwordSignUp = findViewById(R.id.passwordSignUp);
        errorTxt = findViewById(R.id.errorTxtShowSignUp);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInfo();
            }
        });

    }

    private void checkInfo() {

        String name = nameSignUp.getText().toString().trim();
        String number = numberSignUp.getText().toString().trim();
        String username = usernameSignUp.getText().toString().trim().toLowerCase();
        String password = passwordSignUp.getText().toString().trim();

        if (name.isEmpty() || number.isEmpty() || username.isEmpty() || password.isEmpty()){
            if (name.isEmpty()){
                errorTxt.setText("Name Empty!");
            }
            else if (number.isEmpty()){
                errorTxt.setText("Number Empty!");
            }
            else if (username.isEmpty()){
                errorTxt.setText("Username Empty!");
            }
            else if (password.isEmpty()){
                errorTxt.setText("Password Empty!");
            }
        }else {
            submitDatabase(name, number, username, password);
        }

    }

    private void submitDatabase(String name, String number, String username, String password) {
        errorTxt.setText("");

        if (checkConnection()){
            LoadingBar = new ProgressDialog(this);
            LoadingBar.setMessage("Loading...\nIt Take some times Maybe!");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();
            signUpButton.setEnabled(false);

            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<String> call = apiInterface.getUserSignUpResponse(username,name,number,password);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()){
                        String result;
                        switch (response.body()){
                            case "2":
                                result = "account created successfully!";
                                perfConfig.displayToast(result);
                                nameSignUp.setText("");
                                passwordSignUp.setText("");
                                usernameSignUp.setText("");
                                numberSignUp.setText("");
                                signUpButton.setEnabled(true);
                                break;
                            case "1":
                                result = "Username Already taken!\nTry different username";
                                perfConfig.displayToast(result);
                                signUpButton.setEnabled(true);
                                break;
                            case "3":
                                result = "Connection Error!";
                                perfConfig.displayToast(result);
                                signUpButton.setEnabled(true);
                                break;
                            case "0":
                                result = "Can't Insert into Database! Try again!";
                                perfConfig.displayToast(result);
                                signUpButton.setEnabled(true);
                                break;
                            default:
                                result = "Error! Please try again";
                                perfConfig.displayToast(result);
                                signUpButton.setEnabled(true);
                                break;
                        }
                    }
                    LoadingBar.cancel();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    perfConfig.displayToast("Server Time out! Try again!");
                    signUpButton.setEnabled(true);
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
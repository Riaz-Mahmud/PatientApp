package com.example.patientapp3.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.patientapp3.R;


public class PerfConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PerfConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
    }

    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), status);
        editor.apply();
    }

    public boolean readLoginStatus() {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);
    }

    public void writeUsername(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_username), username);
        editor.apply();
    }

    public String readUsername() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_username), "Username");
    }

    public void writeName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_name), name);
        editor.apply();
    }

    public String readName() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_name), "Name");
    }

    public void writeNumber(String number) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_number), number);
        editor.apply();
    }

    public String readNumber() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_number), "Number");
    }


    public void displayToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }



}

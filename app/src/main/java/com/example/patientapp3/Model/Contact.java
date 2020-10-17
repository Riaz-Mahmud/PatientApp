package com.example.patientapp3.Model;

import com.google.gson.annotations.SerializedName;

public class Contact {


    @SerializedName("name")
    private String Name;

    public String getName() {
        return Name;
    }
}

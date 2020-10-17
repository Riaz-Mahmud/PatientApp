package com.example.patientapp3.Model;

import com.google.gson.annotations.SerializedName;

public class medicine_Contact {


    @SerializedName("name")
    private String Name;

    @SerializedName("cause")
    private String Cause;

    public String getName() {
        return Name;
    }

    public String getCause() {
        return Cause;
    }

}

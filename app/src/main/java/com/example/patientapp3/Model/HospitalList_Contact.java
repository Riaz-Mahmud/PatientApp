package com.example.patientapp3.Model;

import com.google.gson.annotations.SerializedName;

public class HospitalList_Contact {


    @SerializedName("name")
    private String Name;

    @SerializedName("loc")
    private String Loc;

    public String getName() {
        return Name;
    }

    public String getLoc() {
        return Loc;
    }
}

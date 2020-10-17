package com.example.patientapp3.Model;

import com.google.gson.annotations.SerializedName;

public class Donor_Contact {


    @SerializedName("name")
    private String Name;

    @SerializedName("bloodgroup")
    private String BloodGroup;

    @SerializedName("number")
    private String Number;

    public String getName() {
        return Name;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public String getNumber() {
        return Number;
    }
}

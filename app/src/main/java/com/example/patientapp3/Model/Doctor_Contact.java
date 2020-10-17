package com.example.patientapp3.Model;

import com.google.gson.annotations.SerializedName;

public class Doctor_Contact {


    @SerializedName("name")
    private String Name;

    @SerializedName("hospital")
    private String Hospital;

    @SerializedName("expert")
    private String Expert;

    @SerializedName("shift")
    private String Shift;

    @SerializedName("day")
    private String Day;

    @SerializedName("number")
    private String Number;

    public String getName() {
        return Name;
    }

    public String getHospital() {
        return Hospital;
    }

    public String getExpert() {
        return Expert;
    }

    public String getShift() {
        return Shift;
    }

    public String getDay() {
        return Day;
    }

    public String getNumber() {
        return Number;
    }
}

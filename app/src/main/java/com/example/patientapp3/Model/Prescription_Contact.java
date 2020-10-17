package com.example.patientapp3.Model;

import com.google.gson.annotations.SerializedName;

public class Prescription_Contact {


    @SerializedName("image")
    private String Image;

    @SerializedName("id")
    private String Id;

    public String getImage() {
        return Image;
    }

    public String getId() {
        return Id;
    }
}

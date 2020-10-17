package com.example.patientapp3.Model;

import com.google.gson.annotations.SerializedName;

public class UserInformation {

    @SerializedName("response")
    private String Response;

    @SerializedName("name")
    private String Name;

    @SerializedName("number")
    private String Number;

    public String getResponse() {
        return Response;
    }

    public String getName() {
        return Name;
    }

    public String getNumber() {
        return Number;
    }
}

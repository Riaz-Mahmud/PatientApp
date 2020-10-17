package com.example.patientapp3.network;


import com.example.patientapp3.Model.Contact;
import com.example.patientapp3.Model.Doctor_Contact;
import com.example.patientapp3.Model.Donor_Contact;
import com.example.patientapp3.Model.HospitalList_Contact;
import com.example.patientapp3.Model.Prescription_Contact;
import com.example.patientapp3.Model.UserInformation;
import com.example.patientapp3.Model.medicine_Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("p7770j3ct/patientapp/hospitalLoc.php")
    Call<List<Contact>> getHospitalLoc(
            @Field("gcode") String Gcode
    );

    @FormUrlEncoded
    @POST("p7770j3ct/patientapp/hospitalList.php")
    Call<List<HospitalList_Contact>> getHospitalList(
            @Field("location") String Location
    );

    @FormUrlEncoded
    @POST("p7770j3ct/patientapp/doctorList.php")
    Call<List<Doctor_Contact>> getDoctorList(
            @Field("location") String Location
    );

    @FormUrlEncoded
    @POST("p7770j3ct/patientapp/medicineList.php")
    Call<List<medicine_Contact>> getMedicineList(
            @Field("location") String Location
    );

    @FormUrlEncoded
    @POST("p7770j3ct/patientapp/donarList.php")
    Call<List<Donor_Contact>> getDonorList(
            @Field("location") String Location
    );

    @FormUrlEncoded
    @POST("p7770j3ct/patientapp/login.php")
    Call<UserInformation> getLoginResponse(
            @Field("username") String Username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("p7770j3ct/patientapp/usersignup.php")
    Call<String> getUserSignUpResponse(
            @Field("username") String Username,
            @Field("name") String Name,
            @Field("number") String Number,
            @Field("password") String Password
    );

    @FormUrlEncoded
    @POST("p7770j3ct/patientapp/prescriptionList.php")
    Call<List<Prescription_Contact>> getPrescriptionList(
            @Field("username") String Username
    );

    @FormUrlEncoded
    @POST("p7770j3ct/patientapp/upload_user_image.php")
    Call<String> getUploadUserImageResponse(
            @Field("image") String image,
            @Field("username") String userName
    );

    @FormUrlEncoded
    @POST("p7770j3ct/patientapp/PresImageDelete.php")
    Call<String> getDeleteUserImageResponse(
            @Field("id") String Id
    );


}

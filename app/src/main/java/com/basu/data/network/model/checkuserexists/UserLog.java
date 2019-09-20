package com.basu.data.network.model.checkuserexists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLog {

    @Expose
    @SerializedName("log_table_id")
    private int log_table_id;

    @Expose
    @SerializedName("user_id")
    private int user_id;

    @Expose
    @SerializedName("device_id")
    private String device_id;

    @Expose
    @SerializedName("device_type")
    private String device_type;

    @Expose
    @SerializedName("user_log_status")
    private int user_log_status;

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("country_code")
    private String country_code;

    @Expose
    @SerializedName("phone")
    private String phone;

    @Expose
    @SerializedName("member_id")
    private String member_id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("photo")
    private String photo;

    public int getLog_table_id() {
        return log_table_id;
    }

    public void setLog_table_id(int log_table_id) {
        this.log_table_id = log_table_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public int getUser_log_status() {
        return user_log_status;
    }

    public void setUser_log_status(int user_log_status) {
        this.user_log_status = user_log_status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

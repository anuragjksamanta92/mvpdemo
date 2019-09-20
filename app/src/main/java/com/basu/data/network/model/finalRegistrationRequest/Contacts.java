package com.basu.data.network.model.finalRegistrationRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contacts {
    @Expose
    @SerializedName("contact_name")
    private String contact_name;
    @Expose
    @SerializedName("contact_country_code")
    private String contact_country_code;
    @Expose
    @SerializedName("contact_phone")
    private String contact_phone;

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_country_code() {
        return contact_country_code;
    }

    public void setContact_country_code(String contact_country_code) {
        this.contact_country_code = contact_country_code;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }
}

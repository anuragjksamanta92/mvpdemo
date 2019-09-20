package com.basu.data.network.model.finalRegistrationRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Car {

    @Expose
    @SerializedName("make")
    private String make;
    @Expose
    @SerializedName("model")
    private String model;
    @Expose
    @SerializedName("licence_number")
    private String licence_number;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicence_number() {
        return licence_number;
    }

    public void setLicence_number(String licence_number) {
        this.licence_number = licence_number;
    }
}

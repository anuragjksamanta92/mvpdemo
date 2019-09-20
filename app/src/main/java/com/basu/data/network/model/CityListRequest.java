package com.basu.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityListRequest {

    @Expose
    @SerializedName("state_id")
    private String state_id;

    public CityListRequest(String stateCode) {
        this.state_id = stateCode;
    }
}

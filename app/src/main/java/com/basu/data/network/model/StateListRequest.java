package com.basu.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateListRequest {

    @Expose
    @SerializedName("country_id")
    private String country_id;

    public StateListRequest(String country_id) {
        this.country_id = country_id;
    }

}

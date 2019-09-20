package com.basu.data.network.model.alarmRequests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartAlarmRequest {

    @Expose
    @SerializedName("user_id")
    private String user_id;

    @Expose
    @SerializedName("alert_response")
    private String alert_response;

    @Expose
    @SerializedName("current_location")
    private String current_location;

    @Expose
    @SerializedName("latitude")
    private String latitude;

    @Expose
    @SerializedName("longitude")
    private String longitude;

    public StartAlarmRequest(String user_id, String alert_response, String current_location, double latitude, double longitude) {
        this.user_id = user_id;
        this.alert_response = alert_response;
        this.current_location = current_location;
        this.latitude = String.valueOf(latitude);
        this.longitude = String.valueOf(longitude);
    }
}

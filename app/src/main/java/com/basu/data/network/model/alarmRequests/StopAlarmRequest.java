package com.basu.data.network.model.alarmRequests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StopAlarmRequest {

    @Expose
    @SerializedName("user_id")
    private String user_id;
    @Expose
    @SerializedName("pin")
    private String pin;
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
    @Expose
    @SerializedName("alert_location_tableId")
    private int alert_location_tableId;
    @Expose
    @SerializedName("alert_id")
    private int alert_id;

    public StopAlarmRequest(String user_id, String otpOne, String otpTwo, String otpThree, String otpFour, double latitude, double longitude, String current_location, int alertLocationTableId, int alertId) {
        this.user_id = user_id;
        this.pin = String.format("%s", otpOne + otpTwo + otpThree + otpFour);
        this.latitude = String.valueOf(latitude);
        this.longitude = String.valueOf(longitude);
        this.current_location = current_location;
        this.alert_location_tableId = alertLocationTableId;
        this.alert_id = alertId;
    }
}

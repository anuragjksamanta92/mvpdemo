package com.basu.data.network.model.alarmRequests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartResponse {
    @Expose
    @SerializedName("alert_location_tableId")
    private int alert_location_tableId;
    @Expose
    @SerializedName("location_start")
    private String location_start;
    @Expose
    @SerializedName("lat_start")
    private String lat_start;
    @Expose
    @SerializedName("lng_start")
    private String lng_start;
    @Expose
    @SerializedName("alert_id")
    private int alert_id;

    public int getAlert_location_tableId() {
        return alert_location_tableId;
    }

    public void setAlert_location_tableId(int alert_location_tableId) {
        this.alert_location_tableId = alert_location_tableId;
    }

    public String getLocation_start() {
        return location_start;
    }

    public void setLocation_start(String location_start) {
        this.location_start = location_start;
    }

    public String getLat_start() {
        return lat_start;
    }

    public void setLat_start(String lat_start) {
        this.lat_start = lat_start;
    }

    public String getLng_start() {
        return lng_start;
    }

    public void setLng_start(String lng_start) {
        this.lng_start = lng_start;
    }

    public int getAlert_id() {
        return alert_id;
    }

    public void setAlert_id(int alert_id) {
        this.alert_id = alert_id;
    }
}

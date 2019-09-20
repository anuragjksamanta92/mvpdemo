package com.basu.data.network.model.alarmRequests;

import com.basu.data.network.model.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartAlarmResponse extends BaseResponse {

    @Expose
    @SerializedName("response")
    private StartResponse startResponse;

    public StartResponse getStartResponse() {
        return startResponse;
    }

    public void setStartResponse(StartResponse startResponse) {
        this.startResponse = startResponse;
    }
}

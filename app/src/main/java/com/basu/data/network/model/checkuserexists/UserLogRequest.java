package com.basu.data.network.model.checkuserexists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogRequest {
    public UserLogRequest() {
    }

    public static class ServerUserLogRequest {
        @Expose
        @SerializedName("device_id")
        private String device_id;

        public ServerUserLogRequest(String device_id) {
            this.device_id = device_id;
        }

    }
}

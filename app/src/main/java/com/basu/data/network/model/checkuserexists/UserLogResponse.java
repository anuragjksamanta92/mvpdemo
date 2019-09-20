package com.basu.data.network.model.checkuserexists;

import com.basu.data.network.model.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogResponse extends BaseResponse {
    @Expose
    @SerializedName("response")
    private UserLog userLog;

    @Expose
    @SerializedName("_jwtToken")
    private String _jwtToken;

    public UserLog getUserLog() {
        return userLog;
    }

    public void setUserLog(UserLog userLog) {
        this.userLog = userLog;
    }

    public String get_jwtToken() {
        return _jwtToken;
    }

    public void set_jwtToken(String _jwtToken) {
        this._jwtToken = _jwtToken;
    }
}

package com.basu.data.network.model.fetchmemberid;

import com.basu.data.network.model.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMemberIdResponse extends BaseResponse {

    @Expose
    @SerializedName("response")
    private MemberIdResponse memberIdResponse;

    @Expose
    @SerializedName("_jwtToken")
    private String _jwtToken;

    public MemberIdResponse getMemberIdResponse() {
        return memberIdResponse;
    }

    public void setMemberIdResponse(MemberIdResponse memberIdResponse) {
        this.memberIdResponse = memberIdResponse;
    }

    public String get_jwtToken() {
        return _jwtToken;
    }

    public void set_jwtToken(String _jwtToken) {
        this._jwtToken = _jwtToken;
    }
}

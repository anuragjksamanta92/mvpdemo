package com.basu.data.network.model.finalRegistrationRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberId {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("member_id")
    private String member_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
}

package com.basu.data.network.model.fetchmemberid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberIdResponse {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("member_id")
    private String memberId;

    @Expose
    @SerializedName("created_at")
    private String createdAt;

    @Expose
    @SerializedName("deleted")
    private String deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}

package com.basu.data.network.model.finalRegistrationRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResitrationRequest {

    @Expose
    @SerializedName("member_id")
    private MemberId memberId;

    @Expose
    @SerializedName("user")
    private UserMember userMember;

    @Expose
    @SerializedName("pin")
    private String pin;

    @Expose
    @SerializedName("contacts")
    private List<Contacts> contactsList;

    @Expose
    @SerializedName("car")
    private Car car;

    @Expose
    @SerializedName("billings")
    private Billings billings;

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId memberId) {
        this.memberId = memberId;
    }

    public UserMember getUserMember() {
        return userMember;
    }

    public void setUserMember(UserMember userMember) {
        this.userMember = userMember;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<Contacts> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<Contacts> contactsList) {
        this.contactsList = contactsList;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Billings getBillings() {
        return billings;
    }

    public void setBillings(Billings billings) {
        this.billings = billings;
    }
}

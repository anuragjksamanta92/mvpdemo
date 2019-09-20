package com.basu.data.network.model.finalRegistrationRequest;

import com.basu.data.network.model.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationSuccessResponse extends BaseResponse {

    @Expose
    @SerializedName("response")
    private SuccessResponse successResponse;

    public SuccessResponse getSuccessResponse() {
        return successResponse;
    }

    public void setSuccessResponse(SuccessResponse successResponse) {
        this.successResponse = successResponse;
    }
}

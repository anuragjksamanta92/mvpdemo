package com.basu.ui.profile;

import com.basu.data.network.model.finalRegistrationRequest.RegistrationSuccessResponse;
import com.basu.ui.base.MvpInteractor;

import org.json.JSONObject;

import io.reactivex.Observable;

/**
 * Created by root on 4/3/19.
 */

public interface ProfileMvpInteractor extends MvpInteractor {



    int getCurrentUserLoggedInMode();

    boolean saveToDb(String encodedImage, String car_make, String car_model, String license_plate_number,
                  String currentUserId);

    JSONObject completeRegistration(ProfileActivity profileActivity, String encodedImage, String car_make, String car_model, String license_plate_number, String simSerial);

    Observable<RegistrationSuccessResponse> doServerCompleteRegistrationApiCall(JSONObject request);

    String getUser();
}

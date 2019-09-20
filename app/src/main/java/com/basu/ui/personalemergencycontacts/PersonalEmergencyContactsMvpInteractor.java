package com.basu.ui.personalemergencycontacts;

import com.basu.data.network.model.CountryResponse;
import com.basu.ui.base.MvpInteractor;

import io.reactivex.Observable;

/**
 * Created by root on 4/3/19.
 */

public interface PersonalEmergencyContactsMvpInteractor extends MvpInteractor {



    int getCurrentUserLoggedInMode();

    String getJwtToken();

    void savePersonalContact(String contact_name, String country_code, String phone_number);

    Observable<CountryResponse> doServerGetCountryCodesApiCall();
}

package com.basu.ui.registration;

import com.basu.data.network.model.CityListRequest;
import com.basu.data.network.model.CityResponse;
import com.basu.data.network.model.CountryResponse;
import com.basu.data.network.model.StateListRequest;
import com.basu.data.network.model.StateResponse;
import com.basu.ui.base.MvpInteractor;

import io.reactivex.Observable;

/**
 * Created by root on 4/3/19.
 */

public interface RegistrationMvpInteractor extends MvpInteractor {



    int getCurrentUserLoggedInMode();

    void saveDetailsToDb(String memberId, String name, String email, String countryCode, String phone_no, String address_one, String country, String state, String city, String zip);

    Observable<CountryResponse> doServerGetCountryCodesApiCall();

    Observable<StateResponse> doServerGetStatesApiCall(StateListRequest request);

    Observable<CityResponse> doServerGetCitiesApiCall(CityListRequest cityListRequest);
}

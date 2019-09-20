package com.basu.ui.registration;

import android.content.Context;

import com.basu.data.db.model.User;
import com.basu.data.db.repository.UserRepository;
import com.basu.data.network.ApiHelper;
import com.basu.data.network.model.CityListRequest;
import com.basu.data.network.model.CityResponse;
import com.basu.data.network.model.CountryResponse;
import com.basu.data.network.model.StateListRequest;
import com.basu.data.network.model.StateResponse;
import com.basu.data.prefs.PreferencesHelper;
import com.basu.di.ApplicationContext;
import com.basu.ui.base.BaseInteractor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by root on 4/3/19.
 */

public class RegistrationInteractor extends BaseInteractor
        implements RegistrationMvpInteractor {

    private UserRepository mUserRepository;

    @Inject
    public RegistrationInteractor(@ApplicationContext Context context,
                                  PreferencesHelper preferencesHelper,
                                  ApiHelper apiHelper, UserRepository mUserRepository) {

        super(preferencesHelper, apiHelper);
        this.mUserRepository = mUserRepository;
    }


    @Override
    public int getCurrentUserLoggedInMode() {
        return getPreferencesHelper().getCurrentUserLoggedInMode();
    }


    @Override
    public void saveDetailsToDb(String memberId, String name, String email, String countryCode, String phone_no, String address_one, String country, String state, String city, String zip) {
        /*User mUser = new User();
        mUser.setMemberId(memberId);
        mUser.setName(name);
        mUser.setEmail(email);
        mUser.setPhone_no(phone_no);
        mUser.setAddress_1(address_one);
        mUser.setZip(zip);*/

        if (mUserRepository.checkUserExist(memberId).size() > 0) {
            User mUser = mUserRepository.checkUserExist(memberId).get(0);
            mUser.setMemberId(memberId);
            mUser.setName(name);
            mUser.setEmail(email);
            mUser.setCountry_code(countryCode);
            mUser.setPhone_no(phone_no);
            mUser.setAddress_1(address_one);
            mUser.setCountry(country);
            mUser.setState(state);
            mUser.setCity(city);
            mUser.setZip(zip);
            mUserRepository.updateUser(mUser);
        } else {
            User mUser = new User();
            mUser.setMemberId(memberId);
            mUser.setName(name);
            mUser.setEmail(email);
            mUser.setCountry_code(countryCode);
            mUser.setPhone_no(phone_no);
            mUser.setAddress_1(address_one);
            mUser.setCountry(country);
            mUser.setState(state);
            mUser.setCity(city);
            mUser.setZip(zip);
            mUserRepository.insertUser(mUser);
        }
    }

    @Override
    public Observable<CountryResponse> doServerGetCountryCodesApiCall() {
        return getApiHelper().doServerGetCountryCodesApiCall(getJwtToken());
    }

    @Override
    public Observable<StateResponse> doServerGetStatesApiCall(StateListRequest request) {
        return getApiHelper().doServerGetStatesApiCall(getJwtToken(), request);
    }

    @Override
    public Observable<CityResponse> doServerGetCitiesApiCall(CityListRequest request) {
        return getApiHelper().doServerGetCitiesApiCall(getJwtToken(), request);

    }

    private String getJwtToken() {
        return getPreferencesHelper().getjwttoken();
    }

}

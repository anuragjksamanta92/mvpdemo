package com.basu.ui.personalemergencycontacts;

import android.content.Context;

import com.basu.data.db.model.UserContacts;
import com.basu.data.db.repository.UserRepository;
import com.basu.data.network.ApiHelper;
import com.basu.data.network.model.CountryResponse;
import com.basu.data.prefs.PreferencesHelper;
import com.basu.di.ApplicationContext;
import com.basu.ui.base.BaseInteractor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by root on 4/3/19.
 */

public class PersonalEmergencyContactsInteractor extends BaseInteractor
        implements PersonalEmergencyContactsMvpInteractor {


    private UserRepository mUserRepository;

    @Inject
    public PersonalEmergencyContactsInteractor(@ApplicationContext Context context,
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
    public String getJwtToken() {
        return getPreferencesHelper().getjwttoken();
    }


    @Override
    public void savePersonalContact(String contact_name, String country_code, String phone_number) {
        String memberId = mUserRepository.fetchMemberFromDb().get(0).getMemberId();
            if (mUserRepository.checkContactExist(phone_number).size() > 0) {
                UserContacts mUserContacts = mUserRepository.checkContactExist(phone_number).get(0);
                mUserContacts.setMemberId(memberId);
                mUserContacts.setContactName(contact_name);
                mUserContacts.setContactNo(phone_number);
                mUserContacts.setContactPin(country_code);
                mUserRepository.updateUserContacts(mUserContacts);
            } else {
                UserContacts mUserContacts = new UserContacts();
                mUserContacts.setMemberId(memberId);
                mUserContacts.setContactName(contact_name);
                mUserContacts.setContactNo(phone_number);
                mUserContacts.setContactPin(country_code);
                mUserRepository.insertUserContacts(mUserContacts);
            }
    }

    @Override
    public Observable<CountryResponse> doServerGetCountryCodesApiCall() {
        return getApiHelper().doServerGetCountryCodesApiCall(getJwtToken());
    }
}

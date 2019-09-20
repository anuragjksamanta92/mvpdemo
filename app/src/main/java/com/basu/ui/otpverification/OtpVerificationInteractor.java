package com.basu.ui.otpverification;

import android.content.Context;

import com.basu.data.db.model.User;
import com.basu.data.db.repository.UserRepository;
import com.basu.data.network.ApiHelper;
import com.basu.data.prefs.PreferencesHelper;
import com.basu.di.ApplicationContext;
import com.basu.ui.base.BaseInteractor;

import javax.inject.Inject;

/**
 * Created by root on 4/3/19.
 */

public class OtpVerificationInteractor extends BaseInteractor
        implements OtpVerificationMvpInteractor {


    private UserRepository mUserRepository;

    @Inject
    public OtpVerificationInteractor(@ApplicationContext Context context,
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
    public void savePinToDb(String pin) {
        String memberId = mUserRepository.fetchMemberFromDb().get(0).getMemberId();
        if (mUserRepository.checkUserExist(memberId).size() > 0) {
            User mUser = mUserRepository.checkUserExist(memberId).get(0);
            mUser.setPin(pin);
            mUserRepository.updateUser(mUser);
        }
    }

    @Override
    public String getUser() {
        return mUserRepository.fetchMemberFromDb().get(0).getName();
    }
}

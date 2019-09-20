package com.basu.ui.otpverification;

import com.basu.ui.base.MvpInteractor;

/**
 * Created by root on 4/3/19.
 */

public interface OtpVerificationMvpInteractor extends MvpInteractor {


    int getCurrentUserLoggedInMode();

    void savePinToDb(String pin);

    String getUser();
}

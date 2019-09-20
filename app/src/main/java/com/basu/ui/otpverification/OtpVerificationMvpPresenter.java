package com.basu.ui.otpverification;

import com.basu.ui.base.MvpPresenter;

/**
 * Created by root on 4/3/19.
 */

public interface OtpVerificationMvpPresenter<V extends OtpVerificationMvpView,
        I extends OtpVerificationMvpInteractor> extends MvpPresenter<V, I> {


    void showSplash();

    boolean checkFieldValidation(OtpVerificationActivity otpVerificationActivity, String otp_number_one, String otp_number_two, String otp_number_three, String otp_number_four);
}
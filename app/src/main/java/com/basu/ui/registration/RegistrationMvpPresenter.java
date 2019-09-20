package com.basu.ui.registration;

import com.basu.ui.base.MvpPresenter;

/**
 * Created by root on 4/3/19.
 */

public interface RegistrationMvpPresenter  <V extends RegistrationMvpView,
        I extends RegistrationMvpInteractor> extends MvpPresenter<V, I> {


    void showSplash();

    boolean checkFieldValidation(RegistrationActivity registrationActivity, String memberId, String name, String email, String countryCode, String phone_no, String address_one, String country, String state, String city, String zip);
}
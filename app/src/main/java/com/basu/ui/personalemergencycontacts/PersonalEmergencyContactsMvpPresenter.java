package com.basu.ui.personalemergencycontacts;

import com.basu.ui.base.MvpPresenter;

/**
 * Created by root on 4/3/19.
 */

public interface PersonalEmergencyContactsMvpPresenter<V extends PersonalEmergencyContactsMvpView,
        I extends PersonalEmergencyContactsMvpInteractor> extends MvpPresenter<V, I> {


    void showSplash();

    boolean checkFieldValidation(PersonalEmergencyContactsActivity personalEmergencyContactsActivity,
                                 String first_contact_name, String country_code1, String first_contact_phone_number,
                                 String second_contact_name, String country_code2, String second_contact_phone_number,
                                 String third_contact_name, String country_code3, String third_contact_phone_number,
                                 String fourth_contact_name, String country_code4, String fourth_contact_phone_number,
                                 String fifth_contact_name, String country_code5, String fifth_contact_phone_number);

    void fetchCountryCodeJson(PersonalEmergencyContactsActivity personalEmergencyContactsActivity);
}
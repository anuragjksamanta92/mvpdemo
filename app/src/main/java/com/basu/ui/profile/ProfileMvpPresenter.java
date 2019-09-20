package com.basu.ui.profile;

import com.basu.ui.base.MvpPresenter;

/**
 * Created by root on 4/3/19.
 */

public interface ProfileMvpPresenter<V extends ProfileMvpView,
        I extends ProfileMvpInteractor> extends MvpPresenter<V, I> {


    void showSplash();

    boolean saveToDb(ProfileActivity profileActivity, String encodedImage, String car_make, String car_model, String license_plate_number);

    void completeRegistration(ProfileActivity profileActivity, String encodedImage, String car_make, String car_model, String license_plate_number, String simSerial);
}
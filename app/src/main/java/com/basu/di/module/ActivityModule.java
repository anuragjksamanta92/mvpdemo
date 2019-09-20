/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.basu.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.basu.di.ActivityContext;
import com.basu.di.PerActivity;
import com.basu.ui.confirmemergencyalert.ConfirmEmergencyAlertInteractor;
import com.basu.ui.confirmemergencyalert.ConfirmEmergencyAlertMvpInteractor;
import com.basu.ui.confirmemergencyalert.ConfirmEmergencyAlertMvpPresenter;
import com.basu.ui.confirmemergencyalert.ConfirmEmergencyAlertMvpView;
import com.basu.ui.confirmemergencyalert.ConfirmEmergencyAlertPresenter;
import com.basu.ui.emptyview.EmptyInteractor;
import com.basu.ui.emptyview.EmptyMvpInteractor;
import com.basu.ui.emptyview.EmptyMvpPresenter;
import com.basu.ui.emptyview.EmptyMvpView;
import com.basu.ui.emptyview.EmptyPresenter;
import com.basu.ui.helpontheway.HelpOnTheWayInteractor;
import com.basu.ui.helpontheway.HelpOnTheWayMvpInteractor;
import com.basu.ui.helpontheway.HelpOnTheWayMvpPresenter;
import com.basu.ui.helpontheway.HelpOnTheWayMvpView;
import com.basu.ui.helpontheway.HelpOnTheWayPresenter;
import com.basu.ui.login.LoginInteractor;
import com.basu.ui.login.LoginMvpInteractor;
import com.basu.ui.login.LoginMvpPresenter;
import com.basu.ui.login.LoginMvpView;
import com.basu.ui.login.LoginPresenter;
import com.basu.ui.otpverification.OtpVerificationInteractor;
import com.basu.ui.otpverification.OtpVerificationMvpInteractor;
import com.basu.ui.otpverification.OtpVerificationMvpPresenter;
import com.basu.ui.otpverification.OtpVerificationMvpView;
import com.basu.ui.otpverification.OtpVerificationPresenter;
import com.basu.ui.personalemergencycontacts.PersonalEmergencyContactsInteractor;
import com.basu.ui.personalemergencycontacts.PersonalEmergencyContactsMvpInteractor;
import com.basu.ui.personalemergencycontacts.PersonalEmergencyContactsMvpPresenter;
import com.basu.ui.personalemergencycontacts.PersonalEmergencyContactsMvpView;
import com.basu.ui.personalemergencycontacts.PersonalEmergencyContactsPresenter;
import com.basu.ui.pressandhold.PressAndHoldInteractor;
import com.basu.ui.pressandhold.PressAndHoldMvpInteractor;
import com.basu.ui.pressandhold.PressAndHoldMvpPresenter;
import com.basu.ui.pressandhold.PressAndHoldMvpView;
import com.basu.ui.pressandhold.PressAndHoldPresenter;
import com.basu.ui.profile.ProfileInteractor;
import com.basu.ui.profile.ProfileMvpInteractor;
import com.basu.ui.profile.ProfileMvpPresenter;
import com.basu.ui.profile.ProfileMvpView;
import com.basu.ui.profile.ProfilePresenter;
import com.basu.ui.registration.RegistrationInteractor;
import com.basu.ui.registration.RegistrationMvpInteractor;
import com.basu.ui.registration.RegistrationMvpPresenter;
import com.basu.ui.registration.RegistrationMvpView;
import com.basu.ui.registration.RegistrationPresenter;
import com.basu.ui.splash.SplashInteractor;
import com.basu.ui.splash.SplashMvpInteractor;
import com.basu.ui.splash.SplashMvpPresenter;
import com.basu.ui.splash.SplashMvpView;
import com.basu.ui.splash.SplashPresenter;
import com.basu.ui.thankyou.ThankYouInteractor;
import com.basu.ui.thankyou.ThankYouMvpInteractor;
import com.basu.ui.thankyou.ThankYouMvpPresenter;
import com.basu.ui.thankyou.ThankYouMvpView;
import com.basu.ui.thankyou.ThankYouPresenter;
import com.basu.utils.rx.AppSchedulerProvider;
import com.basu.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by suhrit on 16/11/18.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView, SplashMvpInteractor> provideSplashPresenter(
            SplashPresenter<SplashMvpView, SplashMvpInteractor> presenter) {
        return presenter;
    }


/*    empty activity*/

    @Provides
    @PerActivity
    EmptyMvpInteractor provideEmptyMvpInteractor(EmptyInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    EmptyMvpPresenter<EmptyMvpView, EmptyMvpInteractor> provideEmptyPresenter(
            EmptyPresenter<EmptyMvpView, EmptyMvpInteractor> presenter) {
        return presenter;
    }
    /*  end  empty activity*/

    @Provides
    @PerActivity
    SplashMvpInteractor provideSplashMvpInteractor(SplashInteractor interactor) {
        return interactor;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    @PerActivity
    LoginMvpInteractor provideLoginMvpInteractor(LoginInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView, LoginMvpInteractor> provideLoginPresenter(
            LoginPresenter<LoginMvpView, LoginMvpInteractor> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    RegistrationMvpInteractor provideRegistrationMvpInteractor(RegistrationInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    RegistrationMvpPresenter<RegistrationMvpView, RegistrationMvpInteractor> provideRegistrationPresenter(
            RegistrationPresenter<RegistrationMvpView, RegistrationMvpInteractor> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    OtpVerificationMvpInteractor provideOtpVerificationMvpInteractor(OtpVerificationInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    OtpVerificationMvpPresenter<OtpVerificationMvpView, OtpVerificationMvpInteractor> provideOtpVerificationPresenter(
            OtpVerificationPresenter<OtpVerificationMvpView, OtpVerificationMvpInteractor> presenter) {
        return presenter;
    }



    @Provides
    @PerActivity
    ProfileMvpInteractor provideProfileMvpInteractor(ProfileInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    ProfileMvpPresenter<ProfileMvpView, ProfileMvpInteractor> provideProfilePresenter(
            ProfilePresenter<ProfileMvpView, ProfileMvpInteractor> presenter) {
        return presenter;
    }



    @Provides
    @PerActivity
    PressAndHoldMvpInteractor providePressAndHoldMvpInteractor(PressAndHoldInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    PressAndHoldMvpPresenter<PressAndHoldMvpView, PressAndHoldMvpInteractor> providePressAndHoldePresenter(
            PressAndHoldPresenter<PressAndHoldMvpView, PressAndHoldMvpInteractor> presenter) {
        return presenter;
    }



    @Provides
    @PerActivity
    ConfirmEmergencyAlertMvpInteractor provideConfirmEmergencyAlertMvpInteractor(ConfirmEmergencyAlertInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    ConfirmEmergencyAlertMvpPresenter<ConfirmEmergencyAlertMvpView, ConfirmEmergencyAlertMvpInteractor> provideConfirmEmergencyAlertPresenter(
            ConfirmEmergencyAlertPresenter<ConfirmEmergencyAlertMvpView, ConfirmEmergencyAlertMvpInteractor> presenter) {
        return presenter;
    }






    @Provides
    @PerActivity
    ThankYouMvpInteractor provideThankYouMvpInteractor( ThankYouInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    ThankYouMvpPresenter<ThankYouMvpView, ThankYouMvpInteractor> provideThankYouPresenter(
            ThankYouPresenter< ThankYouMvpView,  ThankYouMvpInteractor> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    HelpOnTheWayMvpInteractor provideHelpOnTheWayMvpInteractor(HelpOnTheWayInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    HelpOnTheWayMvpPresenter<HelpOnTheWayMvpView, HelpOnTheWayMvpInteractor> provideHelpOnTheWayPresenter(
            HelpOnTheWayPresenter< HelpOnTheWayMvpView,  HelpOnTheWayMvpInteractor> presenter) {
        return presenter;
    }



    @Provides
    @PerActivity
    PersonalEmergencyContactsMvpInteractor providePersonalEmergencyContactsMvpInteractor(PersonalEmergencyContactsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    PersonalEmergencyContactsMvpPresenter<PersonalEmergencyContactsMvpView, PersonalEmergencyContactsMvpInteractor> providePersonalEmergencyContactsPresenter(
            PersonalEmergencyContactsPresenter< PersonalEmergencyContactsMvpView,  PersonalEmergencyContactsMvpInteractor> presenter) {
        return presenter;
    }


}

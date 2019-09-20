package com.basu.ui.registration;

import android.content.Context;
import android.text.TextUtils;

import com.basu.R;
import com.basu.ui.base.BasePresenter;
import com.basu.utils.CommonUtils;
import com.basu.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by root on 4/3/19.
 */

public class RegistrationPresenter<V extends RegistrationMvpView, I extends RegistrationMvpInteractor>
        extends BasePresenter<V, I> implements RegistrationMvpPresenter<V, I> {


    Context mContext;

    @Inject
    public RegistrationPresenter(I mvpInteractor,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);


    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);


    }


    @Override
    public void showSplash() {
        /*Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(this::onSplashTimeoutCheck);*/
    }


    @Override
    public boolean checkFieldValidation(RegistrationActivity registrationActivity, String memberId, String name, String email, String countryCode, String phone_no, String address_one, String country, String state, String city, String zip) {
        if (!TextUtils.isEmpty(name)) {
            if (CommonUtils.isEmailValid(email)) {
                if (!TextUtils.isEmpty(countryCode)) {
                    if (!TextUtils.isEmpty(phone_no)) {
                        if (!TextUtils.isEmpty(address_one)) {
                            if (!TextUtils.isEmpty(country)) {
                                if (!TextUtils.isEmpty(state)) {
                                    if (!TextUtils.isEmpty(city)) {
                                        if (!TextUtils.isEmpty(zip)) {
                                            getInteractor().saveDetailsToDb(memberId, name, email, countryCode, phone_no, address_one, country, state, city, zip);
                                            return true;
                                        } else {
                                            getMvpView().showMessage(registrationActivity.getString(R.string.valid_zip));
                                        }
                                    } else {
                                        getMvpView().showMessage(registrationActivity.getString(R.string.valid_city_name));
                                    }
                                } else {
                                    getMvpView().showMessage(registrationActivity.getString(R.string.valid_state_name));
                                }
                            } else {
                                getMvpView().showMessage(registrationActivity.getString(R.string.valid_contact_name));
                            }
                        } else {
                            getMvpView().showMessage(registrationActivity.getString(R.string.valid_address));
                        }
                    } else {
                        getMvpView().showMessage(registrationActivity.getString(R.string.valid_phone));
                    }
                } else {
                    getMvpView().showMessage(registrationActivity.getString(R.string.valid_country_code));
                }
            } else {
                getMvpView().showMessage(registrationActivity.getString(R.string.valid_emai));
            }
        } else {
            getMvpView().showMessage(registrationActivity.getString(R.string.valid_fullname));
        }
        return false;
    }

}



package com.basu.ui.personalemergencycontacts;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.basu.R;
import com.basu.data.network.model.CountryResponse;
import com.basu.ui.base.BasePresenter;
import com.basu.utils.NetworkUtils;
import com.basu.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by root on 4/3/19.
 */

public class PersonalEmergencyContactsPresenter<V extends PersonalEmergencyContactsMvpView, I extends PersonalEmergencyContactsMvpInteractor>
        extends BasePresenter<V, I> implements PersonalEmergencyContactsMvpPresenter<V, I> {


    Context mContext;

    @Inject
    public PersonalEmergencyContactsPresenter(I mvpInteractor,
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
    public boolean checkFieldValidation(PersonalEmergencyContactsActivity personalEmergencyContactsActivity,
                                        String first_contact_name, String country_code1, String first_contact_phone_number,
                                        String second_contact_name, String country_code2, String second_contact_phone_number,
                                        String third_contact_name, String country_code3, String third_contact_phone_number,
                                        String fourth_contact_name, String country_code4, String fourth_contact_phone_number,
                                        String fifth_contact_name, String country_code5, String fifth_contact_phone_number) {

        boolean saveFirstContact, saveSecondContact, saveThirdContact, saveFourthContact, saveFifthContact;

        saveFirstContact = !TextUtils.isEmpty(first_contact_name);
        saveSecondContact = !TextUtils.isEmpty(second_contact_name);
        saveThirdContact = !TextUtils.isEmpty(third_contact_name);
        saveFourthContact = !TextUtils.isEmpty(fourth_contact_name);
        saveFifthContact = !TextUtils.isEmpty(fifth_contact_name);

        if (saveFirstContact)
            getInteractor().savePersonalContact(first_contact_name, country_code1, first_contact_phone_number);
        if (saveSecondContact)
            getInteractor().savePersonalContact(second_contact_name, country_code2, second_contact_phone_number);
        if (saveThirdContact)
            getInteractor().savePersonalContact(third_contact_name, country_code3, third_contact_phone_number);
        if (saveFourthContact)
            getInteractor().savePersonalContact(fourth_contact_name, country_code4, fourth_contact_phone_number);
        if (saveFifthContact)
            getInteractor().savePersonalContact(fifth_contact_name, country_code5, fifth_contact_phone_number);
        return true;

        /*if (!TextUtils.isEmpty(first_contact_name)) {
            if (!TextUtils.isEmpty(country_code1)) {
                if (!TextUtils.isEmpty(first_contact_phone_number)) {
                    getInteractor().savePersonalContact(first_contact_name, country_code1, first_contact_phone_number);
                } else {
                    getMvpView().showMessage(personalEmergencyContactsActivity.getString(R.string.valid_phone));
                }
            } else {
                getMvpView().showMessage(personalEmergencyContactsActivity.getString(R.string.valid_country_code));
            }
        } else {
            getMvpView().showMessage(personalEmergencyContactsActivity.getString(R.string.valid_contact_name));
        }

        if (!TextUtils.isEmpty(second_contact_name)) {
            if (!TextUtils.isEmpty(country_code2)) {
                if (!TextUtils.isEmpty(second_contact_phone_number)) {
                    getInteractor().savePersonalContact(second_contact_name, country_code2, second_contact_phone_number);
                } else {
                    getMvpView().showMessage(personalEmergencyContactsActivity.getString(R.string.valid_phone));
                }
            } else {
                getMvpView().showMessage(personalEmergencyContactsActivity.getString(R.string.valid_country_code));
            }
        }

        if (!TextUtils.isEmpty(third_contact_name)) {
            if (!TextUtils.isEmpty(country_code3)) {
                if (!TextUtils.isEmpty(third_contact_phone_number)) {
                    getInteractor().savePersonalContact(third_contact_name, country_code3, third_contact_phone_number);
                } else {
                    getMvpView().showMessage(personalEmergencyContactsActivity.getString(R.string.valid_phone));
                }
            } else {
                getMvpView().showMessage(personalEmergencyContactsActivity.getString(R.string.valid_country_code));
            }
        }

        if (!TextUtils.isEmpty(fifth_contact_name)) {
            if (!TextUtils.isEmpty(country_code4)) {
                if (!TextUtils.isEmpty(fourth_contact_phone_number)) {
                    getInteractor().savePersonalContact(fourth_contact_name, country_code4, fourth_contact_phone_number);
                } else {
                    getMvpView().showMessage(personalEmergencyContactsActivity.getString(R.string.valid_phone));
                }
            } else {
                getMvpView().showMessage(personalEmergencyContactsActivity.getString(R.string.valid_country_code));
            }
        }

        if (!TextUtils.isEmpty(fifth_contact_name)) {
            if (!TextUtils.isEmpty(country_code5)) {
                if (!TextUtils.isEmpty(fifth_contact_phone_number)) {
                    getInteractor().savePersonalContact(fifth_contact_name, country_code5, fifth_contact_phone_number);
                } else {
                    getMvpView().showMessage(personalEmergencyContactsActivity.getString(R.string.valid_phone));
                }
            } else {
                getMvpView().showMessage(personalEmergencyContactsActivity.getString(R.string.valid_country_code));
            }
        }
*/
    }

    @Override
    public void fetchCountryCodeJson(PersonalEmergencyContactsActivity personalEmergencyContactsActivity) {
        if (NetworkUtils.isNetworkConnected(personalEmergencyContactsActivity)) {
            getMvpView().showLoading();

            getCompositeDisposable().add(getInteractor()
                    .doServerGetCountryCodesApiCall()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<CountryResponse>() {
                        @Override
                        public void accept(CountryResponse response) {
                            Log.e("OK response ", "" + response.getCountryList());


                            if (!isViewAttached()) {
                                return;
                            }

                            getMvpView().hideLoading();
                            if (response.getStatus()) {
//                                getMvpView().setCountryJson(response.getCountryList());
                            }

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) {

                            if (!isViewAttached()) {
                                return;
                            }

                            getMvpView().hideLoading();

                            // handle the login error here
                            if (throwable instanceof ANError) {
                                ANError anError = (ANError) throwable;
                                handleApiError(anError);
                            }
                        }
                    }));
        } else {
            getMvpView().showMessage(personalEmergencyContactsActivity.getString(R.string.connection_error));
        }

    }


}


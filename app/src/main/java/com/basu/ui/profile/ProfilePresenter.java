package com.basu.ui.profile;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.basu.R;
import com.basu.ui.base.BasePresenter;
import com.basu.utils.NetworkUtils;
import com.basu.utils.rx.SchedulerProvider;

import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by root on 4/3/19.
 */

public class ProfilePresenter<V extends ProfileMvpView, I extends ProfileMvpInteractor>
        extends BasePresenter<V, I> implements ProfileMvpPresenter<V, I> {


    Context mContext;

    @Inject
    public ProfilePresenter(I mvpInteractor,
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
    public boolean saveToDb(ProfileActivity profileActivity, String encodedImage, String car_make, String car_model, String license_plate_number) {
        return getInteractor().saveToDb(encodedImage, car_make, car_model, license_plate_number, getInteractor().getPreferencesHelper().getCurrentMemberId());

    }

    @Override
    public void completeRegistration(ProfileActivity profileActivity, String encodedImage, String car_make, String car_model, String license_plate_number, String simSerial) {
        JSONObject requestJson = getInteractor().completeRegistration(profileActivity, encodedImage, car_make, car_model, license_plate_number, simSerial);

        if (NetworkUtils.isNetworkConnected(profileActivity)) {
            getMvpView().showLoading();

            getCompositeDisposable().add(getInteractor()
                    .doServerCompleteRegistrationApiCall(requestJson)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(response -> {
                        Log.e("OK response ", "" + response.getStatus());
                        if (!ProfilePresenter.this.isViewAttached()) {
                            return;
                        }
                        if (response.getStatus()) {
                            ProfilePresenter.this.getMvpView().hideLoading();
                            ProfilePresenter.this.getInteractor().getPreferencesHelper().setCurrentUserId(response.getSuccessResponse().getUser_id());
                            ProfilePresenter.this.getMvpView().showMessage(response.getStatus_msg());
                            ProfilePresenter.this.getMvpView().openDashboard();
                        } else {
                            ProfilePresenter.this.getMvpView().hideLoading();
                            ProfilePresenter.this.getMvpView().showMessage(response.getStatus_msg());
                        }

                    }, throwable -> {

                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }));
        } else {
            getMvpView().showMessage(profileActivity.getString(R.string.connection_error));
        }
    }
}



package com.basu.ui.helpontheway;

import android.text.TextUtils;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.basu.R;
import com.basu.data.network.model.BaseResponse;
import com.basu.data.network.model.alarmRequests.StartAlarmRequest;
import com.basu.data.network.model.alarmRequests.StartAlarmResponse;
import com.basu.data.network.model.alarmRequests.StopAlarmRequest;
import com.basu.ui.base.BasePresenter;
import com.basu.utils.NetworkUtils;
import com.basu.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by root on 4/3/19.
 */

public class HelpOnTheWayPresenter<V extends HelpOnTheWayMvpView, I extends HelpOnTheWayMvpInteractor>
        extends BasePresenter<V, I> implements HelpOnTheWayMvpPresenter<V, I> {

    @Inject
    public HelpOnTheWayPresenter(I mvpInteractor,
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
    }

    @Override
    public void startAlarm(HelpOnTheWayActivity helpOnTheWayActivity, double latitude, double longitude, String alert_response, String current_location) {
        if (NetworkUtils.isNetworkConnected(helpOnTheWayActivity)) {
            getMvpView().showLoading();

            getCompositeDisposable().add(getInteractor()
                    .doServerStartAlarmApiCall(new StartAlarmRequest(
                            getInteractor().getPreferencesHelper().getCurrentUserId(),
                            alert_response, current_location, latitude, longitude))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<StartAlarmResponse>() {
                        @Override
                        public void accept(StartAlarmResponse response) {
                            Log.e("OK response ", "" + response.getStatus());
                            if (!isViewAttached()) {
                                return;
                            }

                            if (response.getStatus()) {
                                getMvpView().hideLoading();
                                getInteractor().getPreferencesHelper().setAlertLocationTableId(response.getStartResponse().getAlert_location_tableId());
                                getInteractor().getPreferencesHelper().setAlertId(response.getStartResponse().getAlert_id());
                                getMvpView().startService();
                            } else {
                                getMvpView().hideLoading();
                                getMvpView().showMessage(response.getStatus_msg());
                            }

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) {

                            if (!isViewAttached()) return;

                            getMvpView().hideLoading();

                            // handle the error here
                            if (throwable instanceof ANError) {
                                ANError anError = (ANError) throwable;
                                handleApiError(anError);
                            }
                        }
                    }));
        } else {
            getMvpView().showMessage(helpOnTheWayActivity.getString(R.string.connection_error));
        }
    }

    @Override
    public void stopAlarm(HelpOnTheWayActivity helpOnTheWayActivity, String otpOne, String otpTwo, String otpThree, String otpFour, double latitude, double longitude, String current_location) {
        if (!TextUtils.isEmpty(otpOne)) {
            if (!TextUtils.isEmpty(otpTwo)) {
                if (!TextUtils.isEmpty(otpThree)) {
                    if (!TextUtils.isEmpty(otpFour)) {
                        if (NetworkUtils.isNetworkConnected(helpOnTheWayActivity)) {
                            getMvpView().showLoading();
                            getCompositeDisposable().add(getInteractor()
                                    .doServerStopAlarmApiCall(new StopAlarmRequest(
                                            getInteractor().getPreferencesHelper().getCurrentUserId(),
                                            otpOne, otpTwo, otpThree, otpFour, latitude, longitude, current_location,
                                            getInteractor().getPreferencesHelper().getAlertLocationTableId(),
                                            getInteractor().getPreferencesHelper().getAlertId()
                                    ))
                                    .subscribeOn(getSchedulerProvider().io())
                                    .observeOn(getSchedulerProvider().ui())
                                    .subscribe(new Consumer<BaseResponse>() {
                                        @Override
                                        public void accept(BaseResponse response) {
                                            Log.e("OK response ", "" + response.getStatus());
                                            if (!isViewAttached()) {
                                                return;
                                            }

                                            if (response.getStatus()) {
                                                getMvpView().hideLoading();
                                                getMvpView().stopBackgroundService();
                                                getMvpView().openThankYouPage();
                                            } else {
                                                getMvpView().hideLoading();
                                                getMvpView().showMessage(response.getStatus_msg());
                                            }

                                        }
                                    }, new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) {

                                            if (!isViewAttached()) return;

                                            getMvpView().hideLoading();

                                            // handle the error here
                                            if (throwable instanceof ANError) {
                                                ANError anError = (ANError) throwable;
                                                handleApiError(anError);
                                            }
                                        }
                                    }));
                        } else {
                            getMvpView().showMessage(helpOnTheWayActivity.getString(R.string.connection_error));
                        }
                    } else getMvpView().showMessage(R.string.valid_pin);
                } else getMvpView().showMessage(R.string.valid_pin);
            } else getMvpView().showMessage(R.string.valid_pin);
        } else getMvpView().showMessage(R.string.valid_pin);
    }
}
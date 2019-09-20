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

package com.basu.ui.splash;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.basu.R;
import com.basu.data.network.model.checkuserexists.UserLogRequest;
import com.basu.data.network.model.checkuserexists.UserLogResponse;
import com.basu.ui.base.BasePresenter;
import com.basu.utils.CommonUtils;
import com.basu.utils.NetworkUtils;
import com.basu.utils.rx.SchedulerProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by suhrit on 16/11/18.
 */

public class SplashPresenter<V extends SplashMvpView, I extends SplashMvpInteractor>
        extends BasePresenter<V, I> implements SplashMvpPresenter<V, I> {


    Context mContext;

    @Inject
    public SplashPresenter(I mvpInteractor,
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
        Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(this::onSplashTimeoutCheck);
    }

    @Override
    public void checkIfUserExists(SplashActivity splashActivity) {
        Log.e("DEVICE ID = ", CommonUtils.getDeviceId(splashActivity));
        if (NetworkUtils.isNetworkConnected(splashActivity)) {
            getMvpView().showLoading();

            getCompositeDisposable().add(getInteractor()
                    .doServerGetUserLogApiCall(new UserLogRequest.ServerUserLogRequest(CommonUtils.getDeviceId(splashActivity)))
//                    .doServerGetUserLogApiCall(new UserLogRequest.ServerUserLogRequest("G2AGA1V5AV5AVA5"))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<UserLogResponse>() {
                        @Override
                        public void accept(UserLogResponse response) {
                            Log.e("OK response ", "" + response.getStatus());
                            if (!isViewAttached()) {
                                return;
                            }

                            if (response.getStatus()) {
                                getMvpView().hideLoading();
                                getMvpView().openDashboard();
                                getInteractor().getPreferencesHelper().setCurrentUserId(String.valueOf(response.getUserLog().getUser_id()));
                                getInteractor().getPreferencesHelper().setCurrentMemberId(String.valueOf(response.getUserLog().getMember_id()));
                                getInteractor().getPreferencesHelper().setjwttoken(response.get_jwtToken());
                            } else {
                                getMvpView().hideLoading();
                                getMvpView().showSplash();
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
            getMvpView().showMessage(splashActivity.getString(R.string.connection_error));
        }
    }

    private void onSplashTimeoutCheck() {
        getMvpView().openActivityOnTokenExpire();
    }


}

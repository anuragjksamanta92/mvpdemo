package com.basu.ui.confirmemergencyalert;

import android.content.Context;

import com.basu.ui.base.BasePresenter;
import com.basu.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by root on 4/3/19.
 */

public class ConfirmEmergencyAlertPresenter<V extends ConfirmEmergencyAlertMvpView, I extends ConfirmEmergencyAlertMvpInteractor>
        extends BasePresenter<V, I> implements ConfirmEmergencyAlertMvpPresenter<V, I> {


    Context mContext;
    @Inject
    public ConfirmEmergencyAlertPresenter(I mvpInteractor,
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


}


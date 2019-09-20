package com.basu.ui.pressandhold;

import android.content.Context;

import com.basu.ui.base.BasePresenter;
import com.basu.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by root on 4/3/19.
 */

public class PressAndHoldPresenter<V extends PressAndHoldMvpView, I extends PressAndHoldMvpInteractor>
        extends BasePresenter<V, I> implements PressAndHoldMvpPresenter<V, I> {


    Context mContext;
    @Inject
    public PressAndHoldPresenter(I mvpInteractor,
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


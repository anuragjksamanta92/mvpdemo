package com.basu.ui.thankyou;

import android.content.Context;

import com.basu.ui.base.BasePresenter;
import com.basu.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by root on 4/3/19.
 */

public class ThankYouPresenter<V extends ThankYouMvpView, I extends ThankYouMvpInteractor>
        extends BasePresenter<V, I> implements ThankYouMvpPresenter<V, I> {


    Context mContext;
    @Inject
    public ThankYouPresenter(I mvpInteractor,
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


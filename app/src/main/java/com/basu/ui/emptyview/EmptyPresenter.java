package com.basu.ui.emptyview;

import com.basu.ui.base.BasePresenter;
import com.basu.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by suhrit on 11/17/2018.
 */

public class EmptyPresenter<V extends EmptyMvpView,I extends EmptyMvpInteractor>
        extends BasePresenter<V,I>implements EmptyMvpPresenter<V,I> {

    @Inject
    public EmptyPresenter(I mvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

}

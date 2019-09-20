package com.basu.ui.thankyou;

import com.basu.ui.base.MvpPresenter;

/**
 * Created by root on 4/3/19.
 */

public interface ThankYouMvpPresenter<V extends ThankYouMvpView,
        I extends ThankYouMvpInteractor> extends MvpPresenter<V, I> {


    void showSplash();
}
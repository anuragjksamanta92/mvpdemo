package com.basu.ui.pressandhold;

import com.basu.ui.base.MvpPresenter;

/**
 * Created by root on 4/3/19.
 */

public interface PressAndHoldMvpPresenter<V extends PressAndHoldMvpView,
        I extends PressAndHoldMvpInteractor> extends MvpPresenter<V, I> {


    void showSplash();
}
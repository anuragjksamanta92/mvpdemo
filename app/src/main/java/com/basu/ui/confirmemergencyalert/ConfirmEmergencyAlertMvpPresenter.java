package com.basu.ui.confirmemergencyalert;

import com.basu.ui.base.MvpPresenter;

/**
 * Created by root on 4/3/19.
 */

public interface ConfirmEmergencyAlertMvpPresenter<V extends ConfirmEmergencyAlertMvpView,
        I extends ConfirmEmergencyAlertMvpInteractor> extends MvpPresenter<V, I> {


    void showSplash();
}
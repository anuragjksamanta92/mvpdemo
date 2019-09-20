package com.basu.ui.helpontheway;

import com.basu.ui.base.MvpPresenter;

/**
 * Created by root on 4/3/19.
 */

public interface HelpOnTheWayMvpPresenter<V extends HelpOnTheWayMvpView,
        I extends HelpOnTheWayMvpInteractor> extends MvpPresenter<V, I> {


    void showSplash();

    void startAlarm(HelpOnTheWayActivity helpOnTheWayActivity, double latitude, double longitude, String alert_response, String current_location);

    void stopAlarm(HelpOnTheWayActivity helpOnTheWayActivity, String one, String otpOne, String otpTwo, String otpThree, double latitude, double longitude, String current_location);
}
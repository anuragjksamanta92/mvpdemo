package com.basu.ui.helpontheway;

import com.basu.ui.base.MvpView;

/**
 * Created by root on 4/3/19.
 */

public interface HelpOnTheWayMvpView extends MvpView {
    void openThankYouPage();

    void stopBackgroundService();

    void startService();
}
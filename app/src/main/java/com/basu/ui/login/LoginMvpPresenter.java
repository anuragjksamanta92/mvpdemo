package com.basu.ui.login;

import com.basu.ui.base.MvpPresenter;
import com.basu.ui.splash.SplashMvpInteractor;
import com.basu.ui.splash.SplashMvpView;

/**
 * Created by root on 4/3/19.
 */

public interface LoginMvpPresenter<V extends LoginMvpView,
        I extends LoginMvpInteractor> extends MvpPresenter<V, I> {


    void showSplash();

    void fetchMemberId(LoginActivity loginActivity);
}

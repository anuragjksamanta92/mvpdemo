package com.basu.ui.emptyview;

import com.basu.di.PerActivity;
import com.basu.ui.base.MvpPresenter;

/**
 * Created by suhrit on 11/17/2018.
 */

@PerActivity
public interface EmptyMvpPresenter<V extends EmptyMvpView,I extends EmptyMvpInteractor>extends MvpPresenter<V,I>{


}

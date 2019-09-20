package com.basu.ui.pressandhold;

import android.content.Context;

import com.basu.data.network.ApiHelper;
import com.basu.data.prefs.PreferencesHelper;
import com.basu.di.ApplicationContext;
import com.basu.ui.base.BaseInteractor;

import javax.inject.Inject;

/**
 * Created by root on 11/3/19.
 */

public class PressAndHoldInteractor extends BaseInteractor
        implements PressAndHoldMvpInteractor {


    @Inject
    public PressAndHoldInteractor(@ApplicationContext Context context,
                             PreferencesHelper preferencesHelper,
                             ApiHelper apiHelper) {

        super(preferencesHelper, apiHelper);
    }





    @Override
    public int getCurrentUserLoggedInMode() {
        return getPreferencesHelper().getCurrentUserLoggedInMode();
    }


}

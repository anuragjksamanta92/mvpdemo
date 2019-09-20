package com.basu.ui.emptyview;

import com.basu.data.network.ApiHelper;
import com.basu.data.prefs.PreferencesHelper;
import com.basu.ui.base.BaseInteractor;

import javax.inject.Inject;

/**
 * Created by suhrit on 11/17/2018.
 */

public class EmptyInteractor extends BaseInteractor implements EmptyMvpInteractor {

    @Inject
    public EmptyInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        super(preferencesHelper, apiHelper);
    }
}

/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.basu.ui.splash;

import android.content.Context;

import com.basu.data.network.ApiHelper;
import com.basu.data.network.model.checkuserexists.UserLogRequest;
import com.basu.data.network.model.checkuserexists.UserLogResponse;
import com.basu.data.prefs.PreferencesHelper;
import com.basu.di.ApplicationContext;
import com.basu.ui.base.BaseInteractor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by suhrit on 16/11/18.
 */

public class SplashInteractor extends BaseInteractor
        implements SplashMvpInteractor {


    @Inject
    public SplashInteractor(@ApplicationContext Context context,
                            PreferencesHelper preferencesHelper,
                            ApiHelper apiHelper) {

        super(preferencesHelper, apiHelper);
    }





    @Override
    public int getCurrentUserLoggedInMode() {
        return getPreferencesHelper().getCurrentUserLoggedInMode();
    }

    @Override
    public Observable<UserLogResponse> doServerGetUserLogApiCall(
            UserLogRequest.ServerUserLogRequest request) {
        return getApiHelper().doServerGetUserLogApiCall(request);
    }

}

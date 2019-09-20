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

package com.basu.ui.base;

import android.text.TextUtils;
import android.util.Log;

import com.basu.data.network.ApiHelper;
import com.basu.data.prefs.PreferencesHelper;

import javax.inject.Inject;

/**
 * Created by suhrit on 16/11/18.
 */

public class BaseInteractor implements MvpInteractor {

    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public BaseInteractor(PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public ApiHelper getApiHelper() {
        return mApiHelper;
    }

    @Override
    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    @Override
    public void setAccessToken(String accessToken) {
        getPreferencesHelper().setAccessToken(accessToken);

        getApiHelper().getApiHeader()
                .getProtectedApiHeader()
                .setAccessToken(accessToken);
    }

    @Override
    public void updateUserInfo(
            String memberId,
            String auth,
            String qr,
            String person) {

        getPreferencesHelper().setCurrentMemberId(memberId);
        getPreferencesHelper().setAuth(auth);
        getPreferencesHelper().setQr(qr);
        getPreferencesHelper().setPerson(person);

        //updateApiHeader(userId, accessToken);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                null,
                null);
    }

    @Override
    public void updateApiHeader(String userId, String accessToken) {
        getApiHelper().getApiHeader()
                .getProtectedApiHeader().setUserId(userId);
        getApiHelper().getApiHeader()
                .getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void saveEventIdToSession(String event_id) {
        getPreferencesHelper().setEvent_id(event_id);
    }

    @Override
    public String getEventIdFromSession() {
        return getPreferencesHelper().getEvent_id();
    }

    @Override
    public String getUserId() {
        return getPreferencesHelper().getCurrentMemberId();
    }

    @Override
    public String getAuth() {
        return getPreferencesHelper().getAuth();
    }

    @Override
    public String getQr() {
        return getPreferencesHelper().getQr();
    }

    @Override
    public String getPerson() {
        return getPreferencesHelper().getPerson();
    }

    @Override
    public boolean checkIfLoggedIn() {
        Log.e("USER ID ", "++++++ " + getPreferencesHelper().getCurrentMemberId());
        return !TextUtils.isEmpty(getPreferencesHelper().getCurrentMemberId());
    }

}

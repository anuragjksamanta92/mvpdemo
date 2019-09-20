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

package com.basu.data.prefs;

import com.basu.utils.AppConstants;

import javax.inject.Singleton;

/**
 * Created by suhrit on 16/11/18.
 */

@Singleton
public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(AppConstants.LoggedInMode mode);

    String getCurrentMemberId();

    void setCurrentMemberId(String memberId);


    String getjwttoken();

    void setjwttoken(String jwttoken);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    String getAccessToken();

    void setAccessToken(String accessToken);

    void setEvent_id(String event_id);

    String getEvent_id();

    void setAuth(String auth);

    void setQr(String qr);

    void setPerson(String person);

    String getAuth();

    String getQr();

    String getPerson();

    void setCurrentUserId(String user_id);

    String getCurrentUserId();

    void setCurrentLatitude(String latitude);

    String getCurrentLatitude();

    void setCurrentLongitude(String longitude);

    String getCurrentLongitude();

    void setAlertLocationTableId(int alert_location_tableId);

    void setAlertId(int alert_id);

    int getAlertLocationTableId();

    int getAlertId();
}

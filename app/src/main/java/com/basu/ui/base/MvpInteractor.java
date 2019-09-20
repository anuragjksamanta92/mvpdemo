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

import com.basu.data.network.ApiHelper;
import com.basu.data.prefs.PreferencesHelper;

/**
 * Created by suhrit on 16/11/18.
 */

public interface MvpInteractor {

    ApiHelper getApiHelper();

    PreferencesHelper getPreferencesHelper();

    void setUserAsLoggedOut();

    void setAccessToken(String accessToken);

    void updateUserInfo(String memberId,
                        String auth,
                        String qr,
                        String person);

    void updateApiHeader(String userId, String accessToken);

    void saveEventIdToSession(String event_id);

    String getEventIdFromSession();
    String getUserId();
    String getAuth();
    String getQr();
    String getPerson();

    boolean checkIfLoggedIn();
}

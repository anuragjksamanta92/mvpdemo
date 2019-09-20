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

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.basu.di.ApplicationContext;
import com.basu.di.PreferenceInfo;
import com.basu.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by suhrit on 16/11/18.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_CURRENT_MEMBER_ID = "PREF_KEY_CURRENT_MEMBER_ID";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";
    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_EVENT_ID = "PREF_KEY_EVENT_ID";
    private static final String PREF_KEY_AUTH = "PREF_KEY_AUTH";
    private static final String PREF_KEY_QR = "PREF_KEY_QR";
    private static final String PREF_KEY_PERSON = "PREF_KEY_PERSON";
    private static final String PREF_KEY_JWT_TOKEN = "PREF_KEY_JWT_TOKEN";
    private static final String PREF_KEY_LATITUDE = "PREF_KEY_LATITUDE";
    private static final String PREF_KEY_LONGITUDE = "PREF_KEY_LONGITUDE";
    private static final String PREF_KEY_ALERT_LOCATION_TABLE_ID = "PREF_KEY_ALERT_LOCATION_TABLEID";
    private static final String PREF_KEY_ALERT_ID = "PREF_KEY_ALERT_ID";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        Log.e("prefFileName ",prefFileName);
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    @Override
    public String getCurrentMemberId() {
        String memberId = mPrefs.getString(PREF_KEY_CURRENT_MEMBER_ID, AppConstants.NULL_INDEX);
        return memberId == AppConstants.NULL_INDEX ? null : memberId;
    }

    @Override
    public void setCurrentMemberId(String memberId) {
        String id = memberId == null ? AppConstants.NULL_INDEX : memberId;
        mPrefs.edit().putString(PREF_KEY_CURRENT_MEMBER_ID, id).apply();
    }


    @Override
    public void setCurrentUserId(String user_id) {
        String id = user_id == null ? AppConstants.NULL_INDEX : user_id;
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_ID, id).apply();
    }

    @Override
    public String getCurrentUserId() {
        String userId = mPrefs.getString(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return userId == AppConstants.NULL_INDEX ? null : userId;
    }

    @Override
    public void setCurrentLatitude(String latitude) {
        String id = latitude == null ? AppConstants.NULL_INDEX : latitude;
        mPrefs.edit().putString(PREF_KEY_LATITUDE, id).apply();
    }

    @Override
    public String getCurrentLatitude() {
        String latitude = mPrefs.getString(PREF_KEY_LATITUDE, AppConstants.NULL_INDEX);
        return latitude == AppConstants.NULL_INDEX ? null : latitude;
    }

    @Override
    public void setCurrentLongitude(String longitude) {
        String id = longitude == null ? AppConstants.NULL_INDEX : longitude;
        mPrefs.edit().putString(PREF_KEY_LONGITUDE, id).apply();
    }

    @Override
    public String getCurrentLongitude() {
        String longitude = mPrefs.getString(PREF_KEY_LONGITUDE, AppConstants.NULL_INDEX);
        return longitude == AppConstants.NULL_INDEX ? null : longitude;
    }

    @Override
    public void setAlertLocationTableId(int alert_location_tableId) {
        int id = alert_location_tableId;
        mPrefs.edit().putInt(PREF_KEY_ALERT_LOCATION_TABLE_ID, id).apply();
    }

    @Override
    public void setAlertId(int alert_id) {
        int id = alert_id;
        mPrefs.edit().putInt(PREF_KEY_ALERT_ID, id).apply();
    }

    @Override
    public int getAlertLocationTableId() {
        int alertTableId = mPrefs.getInt(PREF_KEY_ALERT_LOCATION_TABLE_ID, 0);
        return alertTableId;
    }

    @Override
    public int getAlertId() {
        int alertId = mPrefs.getInt(PREF_KEY_ALERT_ID, 0);
        return alertId;
    }

    @Override
    public String getjwttoken() {
        String jwttoken = mPrefs.getString(PREF_KEY_JWT_TOKEN, AppConstants.NULL_INDEX);
        return jwttoken == AppConstants.NULL_INDEX ? null : jwttoken;
    }

    @Override
    public void setjwttoken(String jwttoken) {
        String id = jwttoken == null ? AppConstants.NULL_INDEX : jwttoken;
        mPrefs.edit().putString(PREF_KEY_JWT_TOKEN, id).apply();
    }

    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public String getCurrentUserEmail() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null);
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null);
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(AppConstants.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public void setEvent_id(String event_id) {
        mPrefs.edit().putString(PREF_KEY_EVENT_ID, event_id).apply();
    }

    @Override
    public String getEvent_id() {
        return mPrefs.getString(PREF_KEY_EVENT_ID, null);
    }

    @Override
    public void setAuth(String auth) {
        mPrefs.edit().putString(PREF_KEY_AUTH, auth).apply();
    }

    @Override
    public void setQr(String qr) {
        mPrefs.edit().putString(PREF_KEY_QR, qr).apply();
    }

    @Override
    public void setPerson(String person) {
        mPrefs.edit().putString(PREF_KEY_PERSON, person).apply();
    }

    @Override
    public String getAuth() {
        return mPrefs.getString(PREF_KEY_AUTH, null);
    }

    @Override
    public String getQr() {
        return mPrefs.getString(PREF_KEY_QR, null);
    }

    @Override
    public String getPerson() {
        return mPrefs.getString(PREF_KEY_PERSON, null);
    }
}

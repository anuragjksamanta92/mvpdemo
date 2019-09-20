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

package com.basu.data.network;

import android.util.Log;

import com.basu.data.network.model.BaseResponse;
import com.basu.data.network.model.CityListRequest;
import com.basu.data.network.model.CityResponse;
import com.basu.data.network.model.CountryResponse;
import com.basu.data.network.model.StateListRequest;
import com.basu.data.network.model.StateResponse;
import com.basu.data.network.model.alarmRequests.StartAlarmResponse;
import com.basu.data.network.model.checkuserexists.UserLogRequest;
import com.basu.data.network.model.checkuserexists.UserLogResponse;
import com.basu.data.network.model.fetchmemberid.UserMemberIdResponse;
import com.basu.data.network.model.finalRegistrationRequest.RegistrationSuccessResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by suhrit on 16/11/18.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    /*@Override
    public Observable<UserLogResponse> doServerLoginApiCall(UserLogRequest.ServerUserLogRequest request) {
        return null;
    }*/

    @Override
    public Observable<UserLogResponse> doServerGetUserLogApiCall(UserLogRequest.ServerUserLogRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FETCH_USER_LOG)
                .addBodyParameter(request)
                .build()
                .getObjectObservable(UserLogResponse.class);
    }

    @Override
    public Observable<UserMemberIdResponse> doServerGetMemberIdApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_FETCH_MEMBER_ID)
                .build()
                .getObjectObservable(UserMemberIdResponse.class);
    }

    @Override
    public Observable<CountryResponse> doServerGetCountryCodesApiCall(String jwtToken) {
        Log.e("jwtToken", "Authorization:Bearer: " + jwtToken);
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_COUNTRY_CODE_RESPONSE)
                .addHeaders("Authorization", "Bearer: " + jwtToken)
                .build()
                .getObjectObservable(CountryResponse.class);
    }

    @Override
    public Observable<StateResponse> doServerGetStatesApiCall(String jwtToken, StateListRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_STATES_CODE_RESPONSE)
                .addHeaders("Authorization", "Bearer: " + jwtToken)
                .addBodyParameter(request)
                .build()
                .getObjectObservable(StateResponse.class);
    }

    @Override
    public Observable<CityResponse> doServerGetCitiesApiCall(String jwtToken, CityListRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_CITY_CODE_RESPONSE)
                .addHeaders("Authorization", "Bearer: " + jwtToken)
                .addBodyParameter(request)
                .build()
                .getObjectObservable(CityResponse.class);
    }

    @Override
    public Observable<RegistrationSuccessResponse> doServerCompleteRegistrationApiCall(String jwtToken, JSONObject request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_REGISTRATION_RESPONSE)
                .addHeaders("Authorization", "Bearer: " + jwtToken)
                .addJSONObjectBody(request)
                .build()
                .getObjectObservable(RegistrationSuccessResponse.class);
    }

    @Override
    public Observable<StartAlarmResponse> doServerStartAlarmApiCall(String jwtToken, JSONObject jsonObject) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_START_ALARM)
                .addHeaders("Authorization", "Bearer: " + jwtToken)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(StartAlarmResponse.class);
    }

    @Override
    public Observable<BaseResponse> doServerStopAlarmApiCall(String jwtToken, JSONObject jsonObject) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_STOP_ALARM)
                .addHeaders("Authorization", "Bearer: " + jwtToken)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(BaseResponse.class);
    }





    /*@Override
    public Observable<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest
                                                                  request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GOOGLE_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest
                                                                    request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FACEBOOK_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest
                                                                  request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<LogoutResponse> doLogoutApiCall() {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGOUT)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectObservable(LogoutResponse.class);
    }

    @Override
    public Observable<BlogResponse> getBlogApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BLOG)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectObservable(BlogResponse.class);
    }

    @Override
    public Observable<OpenSourceResponse> getOpenSourceApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectObservable(OpenSourceResponse.class);
    }


    @Override
    public Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest
                                                                  request, String event_id) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN+"/"+event_id)
                .addBodyParameter(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }*/
}


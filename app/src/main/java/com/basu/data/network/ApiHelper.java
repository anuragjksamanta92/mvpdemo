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

import org.json.JSONObject;

import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by suhrit on 16/11/18.
 */

@Singleton
public interface ApiHelper {

    ApiHeader getApiHeader();

    Observable<UserLogResponse> doServerGetUserLogApiCall(UserLogRequest.ServerUserLogRequest request);

    Observable<UserMemberIdResponse> doServerGetMemberIdApiCall();

    Observable<CountryResponse> doServerGetCountryCodesApiCall(String jwtToken);

    Observable<StateResponse> doServerGetStatesApiCall(String jwtToken, StateListRequest request);

    Observable<CityResponse> doServerGetCitiesApiCall(String jwtToken, CityListRequest request);

    Observable<RegistrationSuccessResponse> doServerCompleteRegistrationApiCall(String jwtToken, JSONObject request);

    Observable<StartAlarmResponse> doServerStartAlarmApiCall(String jwtToken, JSONObject jsonObject);

    Observable<BaseResponse> doServerStopAlarmApiCall(String jwtToken, JSONObject request);

    /*Observable<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request);

    Observable<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request);

    Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Observable<LogoutResponse> doLogoutApiCall();

    Observable<BlogResponse> getBlogApiCall();

    Observable<OpenSourceResponse> getOpenSourceApiCall();*/


}

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


import com.basu.BuildConfig;

/**
 * Created by amitshekhar on 01/02/17.
 */

public final class ApiEndPoint {

    /*public static final String ENDPOINT_GOOGLE_LOGIN = BuildConfig.BASE_URL
            + "/588d14f4100000a9072d2943";

    public static final String ENDPOINT_FACEBOOK_LOGIN = BuildConfig.BASE_URL
            + "/588d15d3100000ae072d2944";

    public static final String ENDPOINT_SERVER_LOGIN = BuildConfig.BASE_URL
            + "/588d15f5100000a8072d2945";

    public static final String ENDPOINT_LOGOUT = BuildConfig.BASE_URL
            + "/588d161c100000a9072d2946";

    public static final String ENDPOINT_BLOG = BuildConfig.BASE_URL
            + "/5926ce9d11000096006ccb30";

    public static final String ENDPOINT_OPEN_SOURCE = BuildConfig.BASE_URL
            + "/5926c34212000035026871cd";*/

    public static final String ENDPOINT_FETCH_USER_LOG = BuildConfig.BASE_URL + BuildConfig.API_VERSION + "shared/fetchUserLog";

    public static final String ENDPOINT_FETCH_MEMBER_ID = BuildConfig.BASE_URL + BuildConfig.API_VERSION + "user/fetchmemberid";

    public static final String ENDPOINT_COUNTRY_CODE_RESPONSE = BuildConfig.BASE_URL + BuildConfig.API_VERSION + "shared/fetchCountrylist";

    public static final String ENDPOINT_STATES_CODE_RESPONSE = BuildConfig.BASE_URL + BuildConfig.API_VERSION + "shared/fetchStatelist";

    public static final String ENDPOINT_CITY_CODE_RESPONSE = BuildConfig.BASE_URL + BuildConfig.API_VERSION + "shared/fetchCitylist";

    public static final String ENDPOINT_REGISTRATION_RESPONSE = BuildConfig.BASE_URL + BuildConfig.API_VERSION + "user/registerUser";

    public static final String ENDPOINT_START_ALARM = BuildConfig.BASE_URL + BuildConfig.API_VERSION + "alarm/start";

    public static final String ENDPOINT_UPDATE_LOCATION = BuildConfig.BASE_URL + BuildConfig.API_VERSION + "alarm/update";

    public static final String ENDPOINT_STOP_ALARM = BuildConfig.BASE_URL + BuildConfig.API_VERSION + "alarm/stop";


    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

}

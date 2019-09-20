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

import com.basu.di.ApiInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by suhrit on 16/11/18.
 */

@Singleton
public class ApiHeader {

    private ProtectedApiHeader mProtectedApiHeader;
    private PublicApiHeader mPublicApiHeader;

    @Inject
    public ApiHeader(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader) {
        mPublicApiHeader = publicApiHeader;
        mProtectedApiHeader = protectedApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }

    public static final class PublicApiHeader {

        @Expose
        @SerializedName("Authorization")
        private String Authorization;

        @Inject
        public PublicApiHeader(@ApiInfo String Authorization) {
            Authorization = Authorization;
        }

        public String getApiKey() {
            return Authorization;
        }

        public void setApiKey(String apiKey) {
            Authorization = Authorization;
        }
    }

    public static final class ProtectedApiHeader {

        @Expose
        @SerializedName("Authorization")
        private String Authorization;

        @Expose
        @SerializedName("user_id")
        private String mUserId;

        @Expose
        @SerializedName("access_token")
        private String mAccessToken;

        public ProtectedApiHeader(String mApiKey, String mUserId, String mAccessToken) {
            this.Authorization = mApiKey;
            this.mUserId = mUserId;
            this.mAccessToken = mAccessToken;
        }

        public String getApiKey() {
            return Authorization;
        }

        public void setApiKey(String apiKey) {
            Authorization = apiKey;
        }

        public String getUserId() {
            return mUserId;
        }

        public void setUserId(String mUserId) {
            this.mUserId = mUserId;
        }

        public String getAccessToken() {
            return mAccessToken;
        }

        public void setAccessToken(String accessToken) {
            mAccessToken = accessToken;
        }
    }
}

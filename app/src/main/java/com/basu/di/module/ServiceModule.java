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

package com.basu.di.module;

import android.app.Service;

import com.basu.service.SyncInteractor;
import com.basu.service.SyncMvpInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by suhrit on 16/11/18.
 */

@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    SyncMvpInteractor provideSyncMvpInteractor(SyncInteractor interactor) {
        return interactor;
    }
}

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

package com.basu.di.component;

import android.app.Application;
import android.content.Context;

import com.basu.BasuApp;
import com.basu.data.db.model.DaoSession;
import com.basu.data.network.ApiHelper;
import com.basu.data.prefs.PreferencesHelper;
import com.basu.di.ApplicationContext;
import com.basu.di.module.ApplicationModule;
import com.basu.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by suhrit on 16/11/18.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BasuApp app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    PreferencesHelper preferencesHelper();

    ApiHelper apiHelper();

    DaoSession daoSession();
}
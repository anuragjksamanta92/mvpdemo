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

package com.basu.di.component

import com.basu.di.PerActivity
import com.basu.di.module.ActivityModule
import com.basu.ui.confirmemergencyalert.ConfirmEmergencyAlertActivity
import com.basu.ui.emptyview.EmptyActivity
import com.basu.ui.helpontheway.HelpOnTheWayActivity
import com.basu.ui.login.LoginActivity
import com.basu.ui.otpverification.OtpVerificationActivity
import com.basu.ui.personalemergencycontacts.PersonalEmergencyContactsActivity
import com.basu.ui.pressandhold.PressAndHoldActivity
import com.basu.ui.profile.ProfileActivity
import com.basu.ui.registration.RegistrationActivity
import com.basu.ui.splash.SplashActivity
import com.basu.ui.thankyou.ThankYouActivity

import dagger.Component

/**
 * Created by suhrit on 16/11/18.
 */

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: SplashActivity)
    fun inject(activity: EmptyActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: RegistrationActivity)
    fun inject(activity: OtpVerificationActivity)
    fun inject(activity: ProfileActivity)
    fun inject(activity: PressAndHoldActivity)
    fun inject(activity: ConfirmEmergencyAlertActivity)
    fun inject(activity: ThankYouActivity)
    fun inject(activity: HelpOnTheWayActivity)
    fun inject(activity: PersonalEmergencyContactsActivity)
}

package com.basu.ui.login

import android.content.Context
import com.basu.data.db.model.User
import com.basu.data.db.repository.UserRepository
import com.basu.data.network.ApiHelper
import com.basu.data.network.model.fetchmemberid.UserMemberIdResponse
import com.basu.data.prefs.PreferencesHelper
import com.basu.di.ApplicationContext
import com.basu.ui.base.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by root on 4/3/19.
 */

class LoginInteractor @Inject
constructor(@ApplicationContext context: Context,
            preferencesHelper: PreferencesHelper,
            apiHelper: ApiHelper,
            private val mUserRepository: UserRepository) : BaseInteractor(preferencesHelper, apiHelper), LoginMvpInteractor {


    override fun getCurrentUserLoggedInMode(): Int {
        return preferencesHelper.currentUserLoggedInMode
    }

    override fun doServerGetMemberIdApiCall(): Observable<UserMemberIdResponse> {
        return apiHelper.doServerGetMemberIdApiCall()
    }

    override fun saveMemberId(loginActivity: LoginActivity, memberId: String, id: Int) {
        val mUser = User()
        mUser.member_table_id = id
        mUser.memberId = memberId
        mUserRepository.insertUser(mUser)
    }

    override fun fetchMemberFromDb(): List<User> {
        return mUserRepository.fetchMemberFromDb()
    }
}

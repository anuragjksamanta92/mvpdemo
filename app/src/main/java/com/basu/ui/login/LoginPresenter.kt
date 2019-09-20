package com.basu.ui.login

import android.content.Context
import android.util.Log

import com.androidnetworking.error.ANError
import com.basu.R
import com.basu.data.network.model.fetchmemberid.UserMemberIdResponse
import com.basu.ui.base.BasePresenter
import com.basu.utils.NetworkUtils
import com.basu.utils.rx.SchedulerProvider

import javax.inject.Inject

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

/**
 * Created by root on 4/3/19.
 */

class LoginPresenter<V : LoginMvpView, I : LoginMvpInteractor> @Inject
constructor(mvpInteractor: I,
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable) : BasePresenter<V, I>(mvpInteractor, schedulerProvider, compositeDisposable), LoginMvpPresenter<V, I> {


    internal var mContext: Context? = null

    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)


    }


    override fun showSplash() {
        /*Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(this::onSplashTimeoutCheck);*/
    }


    override fun fetchMemberId(loginActivity: LoginActivity) {

        if (interactor!!.fetchMemberFromDb().size > 0) {
            mvpView!!.openRegistration(interactor!!.fetchMemberFromDb()[0].memberId)
        } else {
            if (NetworkUtils.isNetworkConnected(loginActivity)) {
                mvpView!!.showLoading()


                compositeDisposable.add(interactor!!
                        .doServerGetMemberIdApiCall()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(Consumer { response ->
                            Log.e("OK response ", "" + response.memberIdResponse.memberId)
                            if (!this@LoginPresenter.isViewAttached) {
                                return@Consumer
                            }

                            this@LoginPresenter.mvpView!!.hideLoading()
                            if (response.status!!) {
                                interactor!!.preferencesHelper.setjwttoken(response._jwtToken)
                                mvpView!!.openRegistration(response.memberIdResponse.memberId)
                                interactor!!.saveMemberId(loginActivity, response.memberIdResponse.memberId,
                                        response.memberIdResponse.id)
                                interactor!!.preferencesHelper.currentMemberId = response.memberIdResponse.memberId
                            }
                        }, { throwable ->

                            if (!isViewAttached) {
                                return@getInteractor ()
                                        .doServerGetMemberIdApiCall()
                                        .subscribeOn(getSchedulerProvider().io())
                                        .observeOn(getSchedulerProvider().ui())
                                        .subscribe
                            }

                            //getMvpView().hideLoading();

                            // handle the login error here
                            if (throwable is ANError) {
                                val anError = throwable as ANError
                                handleApiError(anError)
                            }
                        }))
            } else {
                mvpView!!.showMessage(loginActivity.getString(R.string.connection_error))
            }
        }
    }

}

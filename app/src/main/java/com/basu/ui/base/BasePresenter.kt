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

package com.basu.ui.base

/**
 * Created by suhrit on 16/11/18.
 */

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.androidnetworking.common.ANConstants
import com.androidnetworking.error.ANError
import com.basu.R
import com.basu.data.network.model.ApiError
import com.basu.utils.AppConstants
import com.basu.utils.rx.SchedulerProvider
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * onAttach() and onDetach(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
open class BasePresenter<V : MvpView, I : MvpInteractor> @Inject
constructor(private var mMvpInteractor: I?,
            val schedulerProvider: SchedulerProvider,
            val compositeDisposable: CompositeDisposable) : MvpPresenter<V, I> {

    private var mMvpView: V? = null

    override fun onAttach(mvpView: V) {
        mMvpView = mvpView
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        mMvpView = null
        mMvpInteractor = null
    }

    override fun getMvpView(): V? {
        return mMvpView
    }

    override fun getInteractor(): I? {
        return mMvpInteractor
    }

    override fun isViewAttached(): Boolean {
        return mMvpView != null
    }

    @Throws(MvpViewNotAttachedException::class)
    override fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    override fun isInternetOn(context: Context): Boolean? {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun handleApiError(error: ANError?) {

        if (error == null || error.errorBody == null) {
            mvpView!!.onError("LLLL " + R.string.api_default_error)
            return
        }

        if (error.errorCode == AppConstants.API_STATUS_CODE_LOCAL_ERROR && error.errorDetail == ANConstants.CONNECTION_ERROR) {
            mvpView!!.onError(R.string.connection_error)
            return
        }

        if (error.errorCode == AppConstants.API_STATUS_CODE_LOCAL_ERROR && error.errorDetail == ANConstants.REQUEST_CANCELLED_ERROR) {
            mvpView!!.onError(R.string.api_retry_error)
            return
        }

        val builder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        val gson = builder.create()

        try {
            val apiError = gson.fromJson<ApiError>(error.errorBody, ApiError::class.java!!)

            if (apiError == null || apiError.message == null) {
                mvpView!!.onError(R.string.api_default_error)
                return
            }

            when (error.errorCode) {
                HttpsURLConnection.HTTP_UNAUTHORIZED, HttpsURLConnection.HTTP_FORBIDDEN -> {
                    setUserAsLoggedOut()
                    mvpView!!.openActivityOnTokenExpire()
                    mvpView!!.onError(apiError.message)
                }
                HttpsURLConnection.HTTP_INTERNAL_ERROR, HttpsURLConnection.HTTP_NOT_FOUND -> mvpView!!.onError(apiError.message)
                else -> mvpView!!.onError(apiError.message)
            }
        } catch (e: JsonSyntaxException) {
            Log.e(TAG, "handleApiError", e)
            mvpView!!.onError(R.string.api_default_error)
        } catch (e: NullPointerException) {
            Log.e(TAG, "handleApiError", e)
            mvpView!!.onError(R.string.api_default_error)
        }

    }

    override fun setUserAsLoggedOut() {
        interactor!!.setAccessToken(null)
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.onAttach(MvpView) before" + " requesting data to the Presenter")

    companion object {

        private val TAG = "BasePresenter"
    }
}

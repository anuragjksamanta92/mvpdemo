package com.basu.ui.otpverification;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.basu.R;
import com.basu.ui.base.BasePresenter;
import com.basu.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by root on 4/3/19.
 */

public class OtpVerificationPresenter<V extends OtpVerificationMvpView, I extends OtpVerificationMvpInteractor>
        extends BasePresenter<V, I> implements OtpVerificationMvpPresenter<V, I> {


    Context mContext;

    @Inject
    public OtpVerificationPresenter(I mvpInteractor,
                                    SchedulerProvider schedulerProvider,
                                    CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);


    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);


    }


    @Override
    public void showSplash() {
        /*Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(this::onSplashTimeoutCheck);*/
    }

    @Override
    public boolean checkFieldValidation(OtpVerificationActivity otpVerificationActivity, String otp_number_one, String otp_number_two, String otp_number_three, String otp_number_four) {
        if (!TextUtils.isEmpty(otp_number_one)) {
            if (!TextUtils.isEmpty(otp_number_two)) {
                if (!TextUtils.isEmpty(otp_number_three)) {
                    if (!TextUtils.isEmpty(otp_number_four)) {
                        getInteractor().savePinToDb(otp_number_one + otp_number_two + otp_number_three + otp_number_four);
                        return true;
                    } else {
                        getMvpView().showMessage(otpVerificationActivity.getString(R.string.valid_pin));
                    }
                } else {
                    getMvpView().showMessage(otpVerificationActivity.getString(R.string.valid_pin));
                }
            } else {
                getMvpView().showMessage(otpVerificationActivity.getString(R.string.valid_pin));
            }
        } else {
            getMvpView().showMessage(otpVerificationActivity.getString(R.string.valid_pin));
        }
        return false;
    }


}


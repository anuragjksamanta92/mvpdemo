package com.basu.ui.helpontheway;

import com.basu.data.network.model.BaseResponse;
import com.basu.data.network.model.alarmRequests.StartAlarmRequest;
import com.basu.data.network.model.alarmRequests.StartAlarmResponse;
import com.basu.data.network.model.alarmRequests.StopAlarmRequest;
import com.basu.ui.base.MvpInteractor;

import io.reactivex.Observable;

/**
 * Created by root on 4/3/19.
 */

public interface HelpOnTheWayMvpInteractor extends MvpInteractor {



    int getCurrentUserLoggedInMode();

    Observable<StartAlarmResponse> doServerStartAlarmApiCall(StartAlarmRequest request);

    Observable<BaseResponse> doServerStopAlarmApiCall(StopAlarmRequest stopAlarmRequest);
}

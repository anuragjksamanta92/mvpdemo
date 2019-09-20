package com.basu.ui.helpontheway;

import android.content.Context;
import android.util.Log;

import com.basu.data.network.ApiHelper;
import com.basu.data.network.model.BaseResponse;
import com.basu.data.network.model.alarmRequests.StartAlarmRequest;
import com.basu.data.network.model.alarmRequests.StartAlarmResponse;
import com.basu.data.network.model.alarmRequests.StopAlarmRequest;
import com.basu.data.prefs.PreferencesHelper;
import com.basu.di.ApplicationContext;
import com.basu.ui.base.BaseInteractor;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by root on 4/3/19.
 */

public class HelpOnTheWayInteractor extends BaseInteractor
        implements HelpOnTheWayMvpInteractor {


    @Inject
    public HelpOnTheWayInteractor(@ApplicationContext Context context,
                                  PreferencesHelper preferencesHelper,
                                  ApiHelper apiHelper) {

        super(preferencesHelper, apiHelper);
    }





    @Override
    public int getCurrentUserLoggedInMode() {
        return getPreferencesHelper().getCurrentUserLoggedInMode();
    }

    @Override
    public Observable<StartAlarmResponse> doServerStartAlarmApiCall(StartAlarmRequest startAlarmRequest) {
        Gson gson = new Gson();

        String requestJson = gson.toJson(startAlarmRequest);

        JSONObject request = new JSONObject();
        try {
            request = new JSONObject(requestJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("JSON", requestJson);

        return getApiHelper().doServerStartAlarmApiCall(getJwtToken(), request);
    }

    @Override
    public Observable<BaseResponse> doServerStopAlarmApiCall(StopAlarmRequest stopAlarmRequest) {
        Gson gson = new Gson();

        String requestJson = gson.toJson(stopAlarmRequest);
        JSONObject request = new JSONObject();
        try {
            request = new JSONObject(requestJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("JSON", requestJson);

        return getApiHelper().doServerStopAlarmApiCall(getJwtToken(), request);
    }

    private String getJwtToken() {
        return getPreferencesHelper().getjwttoken();
    }
}

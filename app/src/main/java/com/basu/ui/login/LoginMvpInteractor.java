package com.basu.ui.login;

import com.basu.data.db.model.User;
import com.basu.data.network.model.fetchmemberid.UserMemberIdResponse;
import com.basu.ui.base.MvpInteractor;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by root on 4/3/19.
 */

public interface LoginMvpInteractor  extends MvpInteractor {



    int getCurrentUserLoggedInMode();

    Observable<UserMemberIdResponse> doServerGetMemberIdApiCall();

    void saveMemberId(LoginActivity loginActivity, String memberId, int id);

    public List<User> fetchMemberFromDb();
}

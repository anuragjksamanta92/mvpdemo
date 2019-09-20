package com.basu.ui.confirmemergencyalert;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.basu.R;
import com.basu.ui.base.BaseActivity;
import com.basu.ui.helpontheway.HelpOnTheWayActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 5/3/19.
 */

public class ConfirmEmergencyAlertActivity extends BaseActivity implements ConfirmEmergencyAlertMvpView {

    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.img_no)
    ImageView img_no;
    @BindView(R.id.img_yes)
    ImageView img_yes;

    @Inject
    ConfirmEmergencyAlertMvpPresenter<ConfirmEmergencyAlertMvpView, ConfirmEmergencyAlertMvpInteractor> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_emergency_alert);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(ConfirmEmergencyAlertActivity.this);

        initView();
    }

    private void initView() {
        img_yes.setOnClickListener(view -> startActivity(new Intent(ConfirmEmergencyAlertActivity.this, HelpOnTheWayActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)));

        img_no.setOnClickListener(view -> finish());
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }


}
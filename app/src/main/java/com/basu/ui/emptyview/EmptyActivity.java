package com.basu.ui.emptyview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.basu.R;
import com.basu.ui.base.BaseActivity;
import com.basu.ui.splash.SplashActivity;
import com.thekhaeng.pushdownanim.PushDownAnim;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmptyActivity extends BaseActivity implements EmptyMvpView {

    @Inject
    EmptyMvpPresenter<EmptyMvpView,EmptyMvpInteractor> mPresenter;

    @BindView(R.id.btn_retry)
    Button btn_retry;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, EmptyActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_no_internet_connection);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(EmptyActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        PushDownAnim.setPushDownAnimTo(btn_retry)
                .setScale( PushDownAnim.MODE_STATIC_DP , 5 )
                .setDurationPush( PushDownAnim.DEFAULT_PUSH_DURATION )
                .setDurationRelease( PushDownAnim.DEFAULT_RELEASE_DURATION )
                .setInterpolatorPush( PushDownAnim.DEFAULT_INTERPOLATOR )
                .setInterpolatorRelease( PushDownAnim.DEFAULT_INTERPOLATOR )
                .setOnClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick( View view ){
                        switch (view.getId()){

                            case R.id.btn_retry:
                                startActivity(SplashActivity.getStartIntent(EmptyActivity.this));
                                finish();
                                break;

                        }
                    }

                } );

    }

}

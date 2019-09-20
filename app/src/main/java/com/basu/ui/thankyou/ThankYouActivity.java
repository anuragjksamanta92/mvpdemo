package com.basu.ui.thankyou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.basu.R;
import com.basu.ui.base.BaseActivity;
import com.basu.ui.pressandhold.PressAndHoldActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 4/3/19.
 */

public class ThankYouActivity extends BaseActivity implements ThankYouMvpView {

    @Inject
    ThankYouMvpPresenter<ThankYouMvpView,ThankYouMvpInteractor> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ThankYouActivity.class);
        return intent;
    }

    @BindView(R.id.ll_back)
    LinearLayout ll_back;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        changeToolBarColorActivity(ThankYouActivity.this, R.color.light_blue);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(ThankYouActivity.this);


        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThankYouActivity.this, PressAndHoldActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ThankYouActivity.this, PressAndHoldActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    /**
     * Making the screen wait so that the  branding can be shown
     */


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }
}
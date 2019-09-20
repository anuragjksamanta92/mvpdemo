package com.basu.ui.otpverification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.basu.R;
import com.basu.ui.base.BaseActivity;
import com.basu.ui.personalemergencycontacts.PersonalEmergencyContactsActivity;
import com.basu.ui.registration.RegistrationActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 5/3/19.
 */

public class OtpVerificationActivity extends BaseActivity implements OtpVerificationMvpView {

    private String One, Two, Three, Four, Otp_number;
    private String userName = "";

    @BindView(R.id.edt_otp_number_one)
    EditText edt_otp_number_one;
    @BindView(R.id.edt_otp_number_two)
    EditText edt_otp_number_two;
    @BindView(R.id.edt_otp_number_three)
    EditText edt_otp_number_three;
    @BindView(R.id.edt_otp_number_four)
    EditText edt_otp_number_four;
    @BindView(R.id.ll_click_to_next_registration)
    LinearLayout ll_click_to_next_registration;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;


    @Inject
    OtpVerificationMvpPresenter<OtpVerificationMvpView, OtpVerificationMvpInteractor> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        // changeToolBarColorActivity(PersonalEmergencyContactsActivity.this, R.color.grey_white_1000);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(OtpVerificationActivity.this);

        initView();


    }

    private void initView() {

        userName = mPresenter.getInteractor().getUser();
        if (userName != null && !userName.isEmpty())
            tv_user_name.setText("Hello, " + userName);

        ll_click_to_next_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPresenter.checkFieldValidation(OtpVerificationActivity.this,
                        edt_otp_number_one.getText().toString(),
                        edt_otp_number_two.getText().toString(),
                        edt_otp_number_three.getText().toString(),
                        edt_otp_number_four.getText().toString()
                )) {
                    startActivity(OtpVerificationActivity.this, PersonalEmergencyContactsActivity.class);
                }
            }
        });

        edt_otp_number_one.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().equals("")) {
                    One = edt_otp_number_one.getText().toString().trim();
                    edt_otp_number_two.requestFocus();
                    edt_otp_number_two.setEnabled(true);

                } else {
                    One = "";
                    edt_otp_number_two.clearFocus();
                    edt_otp_number_two.setCursorVisible(false);
                }

                // TODO Auto-generated method stub
            }
        });


        edt_otp_number_two.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    Two = edt_otp_number_two.getText().toString().trim();
                    edt_otp_number_three.requestFocus();
                    edt_otp_number_three.setEnabled(true);

                } else if (s.toString().equals("")) {
                    Two = "";
                    edt_otp_number_one.requestFocus();
                    edt_otp_number_one.setEnabled(true);

                } else {
                    edt_otp_number_three.clearFocus();
                    edt_otp_number_three.setCursorVisible(false);
                }

                // TODO Auto-generated method stub
            }
        });
        edt_otp_number_three.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    Three = edt_otp_number_three.getText().toString().trim();
                    edt_otp_number_four.requestFocus();
                    edt_otp_number_four.setEnabled(true);
                } else if (s.toString().equals("")) {
                    Three = "";
                    edt_otp_number_two.requestFocus();
                    edt_otp_number_two.setEnabled(true);

                } else {
                    edt_otp_number_four.clearFocus();
                    edt_otp_number_four.setCursorVisible(false);
                }

                // TODO Auto-generated method stub
            }
        });
        edt_otp_number_four.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    Four = edt_otp_number_four.getText().toString().trim();
                    Otp_number = One + Two + Three + Four;
                    hideKeyboard();
                    System.out.println("dfhfjghjhj---->" + Otp_number);
                } else if (s.toString().equals("")) {
                    Four = "";
                    edt_otp_number_three.requestFocus();
                    edt_otp_number_three.setEnabled(true);

                } else {

                }


            }
        });


        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


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
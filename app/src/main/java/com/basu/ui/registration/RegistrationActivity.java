package com.basu.ui.registration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.basu.R;
import com.basu.ui.base.BaseActivity;
import com.basu.ui.otpverification.OtpVerificationActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by root on 4/3/19.
 */

public class RegistrationActivity extends BaseActivity implements RegistrationMvpView, View.OnClickListener {

    @Inject
    RegistrationMvpPresenter<RegistrationMvpView, RegistrationMvpInteractor> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        return intent;
    }

    @BindView(R.id.ll_click_to_next_registration)
    LinearLayout ll_click_to_next_registration;
    @BindView(R.id.ll_back)
    ImageView ll_back;
    @BindView(R.id.edt_name)
    EditText edt_name;
    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.tv_country_code)
    TextView tv_country_code;
    @BindView(R.id.edt_phone_no)
    EditText edt_phone_no;
    @BindView(R.id.edt_address_one)
    EditText edt_address_one;
    @BindView(R.id.edt_address_two)
    EditText edt_address_two;
    @BindView(R.id.tv_country)
    TextView tv_country;
    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.edt_city)
    EditText edt_city;
    @BindView(R.id.edt_zip)
    EditText edt_zip;

    @BindView(R.id.tv_member_id)
    TextView tv_member_id;

    private String memberId;
    private String countryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_bkp);
        changeToolBarColorActivity(RegistrationActivity.this, R.color.light_blue);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));

        initView();
    }

    @SuppressLint("SetTextI18n")
    private void initView() {

        mPresenter.onAttach(RegistrationActivity.this);

        memberId = getIntent().getStringExtra("member_id");
        tv_member_id.setText("Member Id - " + memberId);

        tv_country_code.setOnClickListener(this);
        tv_country.setOnClickListener(this);
        tv_state.setOnClickListener(this);

    }

    @OnClick(R.id.ll_click_to_next_registration)
    public void OnClickRegistration() {
        if (mPresenter.checkFieldValidation(RegistrationActivity.this,
                memberId,
                edt_name.getText().toString(),
                edt_email.getText().toString(),
                tv_country_code.getText().toString(),
                edt_phone_no.getText().toString(),
                edt_address_one.getText().toString(),
                tv_country.getText().toString(),
                tv_state.getText().toString(),
                edt_city.getText().toString(),
                edt_zip.getText().toString())) {

            startActivity(RegistrationActivity.this, OtpVerificationActivity.class);
        }
    }

    @OnClick(R.id.ll_back)
    public void OnClickBack() {
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_country_code:
                startActivityForResult(new Intent(RegistrationActivity.this, CountryCodeFilterable.class)
                                .putExtra("jwToken", mPresenter.getInteractor().getPreferencesHelper().getjwttoken())
                                .putExtra("class", RegistrationActivity.class.getSimpleName())
                        , 101);
                break;
            case R.id.tv_country:
                startActivityForResult(new Intent(RegistrationActivity.this, CountryNameFilterable.class)
                                .putExtra("jwToken", mPresenter.getInteractor().getPreferencesHelper().getjwttoken())
                        , 102);
                break;
            case R.id.tv_state:
                if (!tv_country.getText().toString().isEmpty())
                    startActivityForResult(new Intent(RegistrationActivity.this, StateNameFilterable.class)
                                    .putExtra("jwToken", mPresenter.getInteractor().getPreferencesHelper().getjwttoken())
                                    .putExtra("id", countryId)
                            , 103);
                else
                    Toast.makeText(RegistrationActivity.this, "Select country first.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 101:
                    String countryCode = data.getExtras().getString("code");
                    tv_country_code.setText(countryCode);
                    break;
                case 102:
                    countryId = String.valueOf(data.getExtras().getInt("id"));
                    tv_country.setText(data.getExtras().getString("name"));
                    break;
                case 103:
                    tv_state.setText(data.getExtras().getString("name"));
                    break;
            }
        }
    }
}
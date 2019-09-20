package com.basu.ui.personalemergencycontacts;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.basu.R;
import com.basu.ui.base.BaseActivity;
import com.basu.ui.profile.ProfileActivity;
import com.basu.ui.registration.RegistrationActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 5/3/19.
 */

public class PersonalEmergencyContactsActivity extends BaseActivity implements PersonalEmergencyContactsMvpView, View.OnClickListener {

    @BindView(R.id.ll_click_to_next)
    LinearLayout ll_click_to_next;
    @BindView(R.id.imv_back)
    ImageView imv_back;

    @BindView(R.id.ll_contact_one)
    LinearLayout ll_contact_one;
    @BindView(R.id.tv_first_contact_name)
    TextView tv_first_contact_name;
    @BindView(R.id.tv_first_contact_phone_number)
    TextView tv_first_contact_phone_number;
    @BindView(R.id.iv_first_edit)
    ImageView iv_first_edit;

    @BindView(R.id.ll_contact_two)
    LinearLayout ll_contact_two;
    @BindView(R.id.tv_second_contact_name)
    TextView tv_second_contact_name;
    @BindView(R.id.tv_second_contact_phone_number)
    TextView tv_second_contact_phone_number;
    @BindView(R.id.iv_second_edit)
    ImageView iv_second_edit;

    @BindView(R.id.ll_contact_three)
    LinearLayout ll_contact_three;
    @BindView(R.id.tv_third_contact_name)
    TextView tv_third_contact_name;
    @BindView(R.id.tv_third_contact_phone_number)
    TextView tv_third_contact_phone_number;
    @BindView(R.id.iv_third_edit)
    ImageView iv_third_edit;

    @BindView(R.id.ll_contact_four)
    LinearLayout ll_contact_four;
    @BindView(R.id.tv_fourth_contact_name)
    TextView tv_fourth_contact_name;
    @BindView(R.id.tv_fourth_contact_phone_number)
    TextView tv_fourth_contact_phone_number;
    @BindView(R.id.iv_fourth_edit)
    ImageView iv_fourth_edit;

    @BindView(R.id.ll_contact_five)
    LinearLayout ll_contact_five;
    @BindView(R.id.tv_fifth_contact_name)
    TextView tv_fifth_contact_name;
    @BindView(R.id.tv_fifth_contact_phone_number)
    TextView tv_fifth_contact_phone_number;
    @BindView(R.id.iv_fifth_edit)
    ImageView iv_fifth_edit;

    @BindView(R.id.tv_txt_press_and_hold)
    TextView tv_txt_press_and_hold;

    @Inject
    PersonalEmergencyContactsMvpPresenter<PersonalEmergencyContactsMvpView, PersonalEmergencyContactsMvpInteractor> mPresenter;

    private String countryCode1;
    private String countryCode2;
    private String countryCode3;
    private String countryCode4;
    private String countryCode5;

    private int request = 0;

    private static final int REQUEST_PICK_CONTACT = 2;
    private static final int REQUEST_EDIT_CONTACT_ONE = 201;
    private static final int REQUEST_EDIT_CONTACT_TWO = 202;
    private static final int REQUEST_EDIT_CONTACT_THREE = 203;
    private static final int REQUEST_EDIT_CONTACT_FOUR = 204;
    private static final int REQUEST_EDIT_CONTACT_FIVE = 205;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_emergency_contacts_new_bkp);
        changeToolBarColorActivity(PersonalEmergencyContactsActivity.this, R.color.grey_white_1000);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(PersonalEmergencyContactsActivity.this);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {

        String text = getString(R.string.txt_personal_emergency_contact_alert);

        // Initialize a new SpannableStringBuilder instance
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(text);

        // Initialize a new RelativeSizeSpan to display small size text
        RelativeSizeSpan smallSizeText = new RelativeSizeSpan(.5f);

        // Apply the small size text to span
        ssBuilder.setSpan(
                smallSizeText,
                text.indexOf("(Optional)"),
                text.indexOf("(Optional)") + String.valueOf("(Optional)").length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        // Display the spannable text to TextView
        tv_txt_press_and_hold.setText(ssBuilder);

        ll_click_to_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPresenter.checkFieldValidation(PersonalEmergencyContactsActivity.this,
                        tv_first_contact_name.getText().toString(),
                        countryCode1,
                        tv_first_contact_phone_number.getText().toString(),
                        tv_second_contact_name.getText().toString(),
                        countryCode2,
                        tv_second_contact_phone_number.getText().toString(),
                        tv_third_contact_name.getText().toString(),
                        countryCode3,
                        tv_third_contact_phone_number.getText().toString(),
                        tv_fourth_contact_name.getText().toString(),
                        countryCode4,
                        tv_fourth_contact_phone_number.getText().toString(),
                        tv_fifth_contact_name.getText().toString(),
                        countryCode5,
                        tv_fifth_contact_phone_number.getText().toString()
                )) {
                    startActivity(PersonalEmergencyContactsActivity.this, ProfileActivity.class);
                }
            }
        });

        imv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ll_contact_one.setOnClickListener(this);
        ll_contact_two.setOnClickListener(this);
        ll_contact_three.setOnClickListener(this);
        ll_contact_four.setOnClickListener(this);
        ll_contact_five.setOnClickListener(this);

        iv_first_edit.setOnClickListener(this);
        iv_second_edit.setOnClickListener(this);
        iv_third_edit.setOnClickListener(this);
        iv_fourth_edit.setOnClickListener(this);
        iv_fifth_edit.setOnClickListener(this);
    }

    private void setPhoneNumber(String phoneNumber, TextView textView) {
        textView.setText(phoneNumber);
        textView.moveCursorToVisibleOffset();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PICK_CONTACT:
                    ContentResolver cr = getContentResolver();
                    try {
                        // getData() method will have the Content Uri of the selected contact
                        Uri uri = data.getData();
                        if (uri != null) {
                            //Query the content uri
                            Cursor cur = cr.query(uri, null, null, null, null);
                            if (cur != null) {
                                cur.moveToFirst();
                                // column index of the contact ID
                                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                                // column index of the contact name
                                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                                String phone = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                Log.e("Name", name);
                                // column index of the phone number
                                Log.e("Phone", phone);
                                setValues(name, phone);

                                cur.close();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case REQUEST_EDIT_CONTACT_ONE:
                    countryCode1 = data.getExtras().getString("country_code");
                    tv_first_contact_name.setText(data.getExtras().getString("name"));
                    tv_first_contact_phone_number.setText(countryCode1 + " " + data.getExtras().getString("phone"));
                    break;

                case REQUEST_EDIT_CONTACT_TWO:
                    countryCode2 = data.getExtras().getString("country_code");
                    tv_second_contact_name.setText(data.getExtras().getString("name"));
                    tv_second_contact_phone_number.setText(countryCode2 + " " + data.getExtras().getString("phone"));
                    break;

                case REQUEST_EDIT_CONTACT_THREE:
                    countryCode3 = data.getExtras().getString("country_code");
                    tv_third_contact_name.setText(data.getExtras().getString("name"));
                    tv_third_contact_phone_number.setText(countryCode3 + " " + data.getExtras().getString("phone"));
                    break;

                case REQUEST_EDIT_CONTACT_FOUR:
                    countryCode4 = data.getExtras().getString("country_code");
                    tv_fourth_contact_name.setText(data.getExtras().getString("name"));
                    tv_fourth_contact_phone_number.setText(countryCode4 + " " + data.getExtras().getString("phone"));
                    break;

                case REQUEST_EDIT_CONTACT_FIVE:
                    countryCode5 = data.getExtras().getString("country_code");
                    tv_fifth_contact_name.setText(data.getExtras().getString("name"));
                    tv_fifth_contact_phone_number.setText(countryCode5 + " " + data.getExtras().getString("phone"));
                    break;
            }
        }
    }

    private void setValues(String name, String phone) {
        switch (request) {
            case 1:
                tv_first_contact_name.setText(name);
                if (!getCountryCode(phone).equals("0"))
                    tv_first_contact_phone_number.setText(String.format("%s %s", getCountryCode(phone), getPhoneNumber(phone)));
                else
                    tv_first_contact_phone_number.setText(getPhoneNumber(phone));
                iv_first_edit.setVisibility(View.VISIBLE);
                break;
            case 2:
                tv_second_contact_name.setText(name);
                if (!getCountryCode(phone).equals("0"))
                    tv_second_contact_phone_number.setText(String.format("%s %s", getCountryCode(phone), getPhoneNumber(phone)));
                else
                    tv_second_contact_phone_number.setText(getPhoneNumber(phone));
                iv_second_edit.setVisibility(View.VISIBLE);
                break;
            case 3:
                tv_third_contact_name.setText(name);
                if (!getCountryCode(phone).equals("0"))
                    tv_third_contact_phone_number.setText(String.format("%s %s", getCountryCode(phone), getPhoneNumber(phone)));
                else
                    tv_third_contact_phone_number.setText(getPhoneNumber(phone));
                iv_third_edit.setVisibility(View.VISIBLE);
                break;
            case 4:
                tv_fourth_contact_name.setText(name);
                if (!getCountryCode(phone).equals("0"))
                    tv_fourth_contact_phone_number.setText(String.format("%s %s", getCountryCode(phone), getPhoneNumber(phone)));
                else
                    tv_fourth_contact_phone_number.setText(getPhoneNumber(phone));
                iv_fourth_edit.setVisibility(View.VISIBLE);
                break;
            case 5:
                tv_fifth_contact_name.setText(name);
                if (!getCountryCode(phone).equals("0"))
                    tv_fifth_contact_phone_number.setText(String.format("%s %s", getCountryCode(phone), getPhoneNumber(phone)));
                else
                    tv_fifth_contact_phone_number.setText(getPhoneNumber(phone));
                iv_fifth_edit.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_contact_one:
                request = 1;
                showContactPicker();
                break;
            case R.id.ll_contact_two:
                request = 2;
                showContactPicker();
                break;
            case R.id.ll_contact_three:
                request = 3;
                showContactPicker();
                break;
            case R.id.ll_contact_four:
                request = 4;
                showContactPicker();
                break;
            case R.id.ll_contact_five:
                request = 5;
                showContactPicker();
                break;
            case R.id.iv_first_edit:
                startActivityForResult(new Intent(PersonalEmergencyContactsActivity.this, EditContactDialog.class)
                                .putExtra("serial", "01")
                                .putExtra("jwToken", mPresenter.getInteractor().getJwtToken())
                                .putExtra("name", tv_first_contact_name.getText().toString())
                                .putExtra("phone", getPhoneNumber(tv_first_contact_phone_number.getText().toString().trim()))
                                .putExtra("country_code", getCountryCode(tv_first_contact_phone_number.getText().toString().trim()))
                        , REQUEST_EDIT_CONTACT_ONE);
                break;
            case R.id.iv_second_edit:
                startActivityForResult(new Intent(PersonalEmergencyContactsActivity.this, EditContactDialog.class)
                                .putExtra("serial", "02")
                                .putExtra("jwToken", mPresenter.getInteractor().getJwtToken())
                                .putExtra("name", tv_second_contact_name.getText().toString())
                                .putExtra("phone", getPhoneNumber(tv_second_contact_phone_number.getText().toString().trim()))
                                .putExtra("country_code", getCountryCode(tv_second_contact_phone_number.getText().toString().trim()))
                        , REQUEST_EDIT_CONTACT_TWO);
                break;
            case R.id.iv_third_edit:
                startActivityForResult(new Intent(PersonalEmergencyContactsActivity.this, EditContactDialog.class)
                                .putExtra("serial", "03")
                                .putExtra("jwToken", mPresenter.getInteractor().getJwtToken())
                                .putExtra("name", tv_third_contact_name.getText().toString())
                                .putExtra("phone", getPhoneNumber(tv_third_contact_phone_number.getText().toString().trim()))
                                .putExtra("country_code", getCountryCode(tv_third_contact_phone_number.getText().toString().trim()))
                        , REQUEST_EDIT_CONTACT_THREE);
                break;
            case R.id.iv_fourth_edit:
                startActivityForResult(new Intent(PersonalEmergencyContactsActivity.this, EditContactDialog.class)
                                .putExtra("serial", "04")
                                .putExtra("jwToken", mPresenter.getInteractor().getJwtToken())
                                .putExtra("name", tv_fourth_contact_name.getText().toString())
                                .putExtra("phone", getPhoneNumber(tv_fourth_contact_phone_number.getText().toString().trim()))
                                .putExtra("country_code", getCountryCode(tv_fourth_contact_phone_number.getText().toString().trim()))
                        , REQUEST_EDIT_CONTACT_FOUR);
                break;
            case R.id.iv_fifth_edit:
                startActivityForResult(new Intent(PersonalEmergencyContactsActivity.this, EditContactDialog.class)
                                .putExtra("serial", "05")
                                .putExtra("jwToken", mPresenter.getInteractor().getJwtToken())
                                .putExtra("name", tv_fifth_contact_name.getText().toString())
                                .putExtra("phone", getPhoneNumber(tv_fifth_contact_phone_number.getText().toString().trim()))
                                .putExtra("country_code", getCountryCode(tv_fifth_contact_phone_number.getText().toString().trim()))
                        , REQUEST_EDIT_CONTACT_FIVE);
                break;
            default:
                request = 0;
                break;
        }

    }

    private String getPhoneNumber(String phoneNumber) {
        String number = phoneNumber.replaceAll(" ", "");
        number = number.replaceAll("-", "");
        switch (number.length()) {
            case 17:
                number = number.substring(7);
                break;
            case 16:
                number = number.substring(6);
                break;
            case 15:
                number = number.substring(5);
                break;
            case 14:
                number = number.substring(4);
                break;
            case 13:
                number = number.substring(3);
                break;
            case 12:
                number = number.substring(2);
                break;
            case 11:
                number = number.substring(1);
                break;
        }
        return number;
    }

    private String getCountryCode(String phoneNumber) {
        String countryCode = phoneNumber.replaceAll(" ", "");
        countryCode = countryCode.replaceAll("-", "");
        switch (countryCode.length()) {
            case 17:
                countryCode = phoneNumber.substring(0, 7);
                break;
            case 16:
                countryCode = phoneNumber.substring(0, 6);
                break;
            case 15:
                countryCode = phoneNumber.substring(0, 5);
                break;
            case 14:
                countryCode = phoneNumber.substring(0, 4);
                break;
            case 13:
                countryCode = phoneNumber.substring(0, 3);
                break;
            case 12:
                countryCode = phoneNumber.substring(0, 2);
                break;
            case 11:
                countryCode = "0";
                break;
            case 10:
                countryCode = "0";
                break;
        }
        return countryCode;
    }

    private void showContactPicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_CONTACT);
    }
}
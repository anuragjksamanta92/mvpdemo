package com.basu.ui.personalemergencycontacts;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.basu.R;
import com.basu.ui.registration.CountryCodeFilterable;

public class EditContactDialog extends AppCompatActivity {

    private String jwToken;
    private String name;
    private String phoneNumber;
    private String serial;
    private String countryCode;
    private EditText edt_contact_name;
    private TextView tv_country_code;
    private TextView tv_contact_serial;
    private EditText edt_contact_number;
    private RelativeLayout rl_save;
    private static final int REQUEST_COUNTRY_CODE = 301;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_contact_dialog);
        this.setFinishOnTouchOutside(true);

        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        tv_contact_serial = findViewById(R.id.tv_contact_serial);
        edt_contact_name = findViewById(R.id.edt_contact_name);
        tv_country_code = findViewById(R.id.tv_country_code);
        edt_contact_number = findViewById(R.id.edt_contact_number);
        rl_save = findViewById(R.id.rl_save);

        serial = getIntent().getExtras().getString("serial");
        jwToken = getIntent().getExtras().getString("jwToken");
        name = getIntent().getExtras().getString("name");
        phoneNumber = getIntent().getExtras().getString("phone");
        countryCode = getIntent().getExtras().getString("country_code");

        tv_contact_serial.setText(serial);
        edt_contact_name.setText(name);
        tv_country_code.setText(countryCode);
        edt_contact_number.setText(phoneNumber);

        edt_contact_name.getBackground().mutate().setColorFilter(getResources().getColor(R.color.dark_gray), PorterDuff.Mode.SRC_ATOP);
        edt_contact_number.getBackground().mutate().setColorFilter(getResources().getColor(R.color.dark_gray), PorterDuff.Mode.SRC_ATOP);
        tv_country_code.getBackground().mutate().setColorFilter(getResources().getColor(R.color.dark_gray), PorterDuff.Mode.SRC_ATOP);

        tv_country_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(EditContactDialog.this, CountryCodeFilterable.class)
                                .putExtra("jwToken", jwToken)
                                .putExtra("class", EditContactDialog.class.getSimpleName())
                        , REQUEST_COUNTRY_CODE);
            }
        });

        rl_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edt_contact_name.getText().toString().trim().isEmpty()) {
                    if (!edt_contact_number.getText().toString().trim().isEmpty()) {
                        if (!tv_country_code.getText().toString().equals("0")) {
                            setResult(RESULT_OK, new Intent(EditContactDialog.this, PersonalEmergencyContactsActivity.class)
                                    .putExtra("name", edt_contact_name.getText().toString().trim())
                                    .putExtra("phone", edt_contact_number.getText().toString().trim())
                                    .putExtra("country_code", tv_country_code.getText().toString().trim())
                            );
                            finish();
                        } else
                            Toast.makeText(EditContactDialog.this, R.string.valid_country_code, Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(EditContactDialog.this, R.string.valid_phone, Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(EditContactDialog.this, R.string.valid_contact_name, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_COUNTRY_CODE) {
                countryCode = data.getExtras().getString("code");
                tv_country_code.setText(countryCode);
            }
        }
    }
}

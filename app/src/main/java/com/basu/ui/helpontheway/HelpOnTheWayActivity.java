package com.basu.ui.helpontheway;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.basu.R;
import com.basu.ui.base.BaseActivity;
import com.basu.ui.helpontheway.locationService.BackgroundService;
import com.basu.ui.helpontheway.locationService.LocationService;
import com.basu.ui.thankyou.ThankYouActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

/**
 * Created by root on 4/3/19.
 */

public class HelpOnTheWayActivity extends BaseActivity implements HelpOnTheWayMvpView, OnMapReadyCallback {

    private String One, Two, Three, Four, Otp_number;
    private GoogleMap mMap;

    @BindView(R.id.edt_otp_number_one)
    EditText edt_otp_number_one;
    @BindView(R.id.edt_otp_number_two)
    EditText edt_otp_number_two;
    @BindView(R.id.edt_otp_number_three)
    EditText edt_otp_number_three;
    @BindView(R.id.edt_otp_number_four)
    EditText edt_otp_number_four;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.ll_stop)
    LinearLayout ll_stop;

    @Inject
    HelpOnTheWayMvpPresenter<HelpOnTheWayMvpView, HelpOnTheWayMvpInteractor> mPresenter;
    private GoogleApiClient mGoogleApiClient;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HelpOnTheWayActivity.class);
        return intent;
    }

    private double latitude = 0.0;
    private double longitude = 0.0;
    private String alert_response = "abc";
    private String current_location = "";
    public LatLng latLng;
    private FusedLocationProviderClient mFusedLocationClient;
    public BackgroundService gpsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_on_the_way);
        changeToolBarColorActivity(HelpOnTheWayActivity.this, R.color.light_blue);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));

        mFusedLocationClient = getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        mPresenter.onAttach(HelpOnTheWayActivity.this);

        Handler handler = new Handler();
        handler.postDelayed(this::getCurrentLocation, 100);

        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                startAlarm(latitude, longitude, current_location);
            }
        }, 1000);

        ll_stop.setOnClickListener(view ->
                stopAlarm());
    }

    private void stopAlarm() {
        if (getCurrentLocation()) {
            mPresenter.stopAlarm(HelpOnTheWayActivity.this,
                    edt_otp_number_one.getText().toString(),
                    edt_otp_number_two.getText().toString(),
                    edt_otp_number_three.getText().toString(),
                    edt_otp_number_four.getText().toString(),
                    latitude, longitude, current_location);
        }
    }

    @SuppressLint("MissingPermission")
    private boolean getCurrentLocation() {
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this,
                location -> {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                        List<Address> addresses;
                        try {
                            addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (addresses.size() > 0)
                                current_location = addresses.get(0).getAddressLine(0);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.setMyLocationEnabled(true);
                        mMap.setBuildingsEnabled(true);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
                    }
                });
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUp();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        stopBackgroundService();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
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

                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                        List<Address> addresses;
                        try {
                            addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (addresses.size() > 0)
                                current_location = addresses.get(0).getAddressLine(0);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.setMyLocationEnabled(true);
                        mMap.setBuildingsEnabled(true);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
                    }
                });
    }

    private void startAlarm(double latitude, double longitude, String current_location) {
        mPresenter.startAlarm(HelpOnTheWayActivity.this, latitude, longitude, alert_response, current_location);
    }

    @Override
    public void openThankYouPage() {
        startActivity(new Intent(HelpOnTheWayActivity.this, ThankYouActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    @Override
    public void stopBackgroundService() {
        Intent intent = new Intent(HelpOnTheWayActivity.this, LocationService.class);
        intent.setAction(LocationService.ACTION_STOP_FOREGROUND_SERVICE);
        startService(intent);
    }

    @Override
    public void startService() {
        Intent intent = new Intent(HelpOnTheWayActivity.this, LocationService.class);
        intent.putExtra("userId", mPresenter.getInteractor().getPreferencesHelper().getCurrentUserId());
        intent.putExtra("alertLocationTableId", mPresenter.getInteractor().getPreferencesHelper().getAlertLocationTableId());
        intent.putExtra("alertId", mPresenter.getInteractor().getPreferencesHelper().getAlertId());
        intent.putExtra("jwtToken", mPresenter.getInteractor().getPreferencesHelper().getjwttoken());
        intent.setAction(LocationService.ACTION_START_FOREGROUND_SERVICE);
        startService(intent);
    }
}
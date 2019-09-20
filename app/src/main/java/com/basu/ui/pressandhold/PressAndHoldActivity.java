package com.basu.ui.pressandhold;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.basu.R;
import com.basu.ui.base.BaseActivity;
import com.basu.ui.confirmemergencyalert.ConfirmEmergencyAlertActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anurag on 5/3/19.
 */

public class PressAndHoldActivity extends BaseActivity implements PressAndHoldMvpView, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int REQUEST_CHECK_SETTINGS = 301;
    long firstTouch;
    @BindView(R.id.img_press_and_hold)
    ImageView img_press_and_hold;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    private AlertDialog alertDialog;
    private boolean gps_enabled = false;
    private boolean network_enabled = false;
    private LocationRequest mLocationRequestHighAccuracy;
    private LocationRequest mLocationRequestBalancedPowerAccuracy;
    private LocationSettingsRequest.Builder builder;


    @Inject
    PressAndHoldMvpPresenter<PressAndHoldMvpView, PressAndHoldMvpInteractor> mPresenter;
    private GoogleApiClient mGoogleApiClient;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, PressAndHoldActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_press_and_hold);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(PressAndHoldActivity.this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mGoogleApiClient.connect();

        mLocationRequestHighAccuracy = LocationRequest.create();
        mLocationRequestHighAccuracy.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mLocationRequestBalancedPowerAccuracy = LocationRequest.create();
        mLocationRequestBalancedPowerAccuracy.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequestHighAccuracy)
                .addLocationRequest(mLocationRequestBalancedPowerAccuracy);

        initView();
    }

    private void checkLocationSettings() {

        Task<LocationSettingsResponse> task =
                LocationServices.getSettingsClient(this).checkLocationSettings(builder.build());

        task.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    // All location settings are satisfied. The client can initialize location
                    // requests here.
                } catch (ApiException exception) {
                    switch (exception.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the
                            // user a dialog.
                            try {
                                // Cast to a resolvable exception.
                                ResolvableApiException resolvable = (ResolvableApiException) exception;
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                resolvable.startResolutionForResult(
                                        PressAndHoldActivity.this,
                                        REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            } catch (ClassCastException e) {
                                // Ignore, should be an impossible error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLocationService();
    }

    private void checkLocationService() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        img_press_and_hold.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.press_icon_inactive));
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (lm != null) {
            try {
                gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (!gps_enabled && !network_enabled) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                View view = inflater.inflate(R.layout.location_servicce_checking_alert, null);
                builder.setView(view);
                TextView tv_deny = view.findViewById(R.id.tv_deny);
                TextView tv_allow = view.findViewById(R.id.tv_allow);

                tv_deny.setOnClickListener(v -> {
                    alertDialog.dismiss();
                    checkLocationService();
                });

                tv_allow.setOnClickListener(v -> {
                    alertDialog.dismiss();
                    checkLocationSettings();
                });
                alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            } else {
                img_press_and_hold.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.press_icon));
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {

        Runnable run = () -> {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (v != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(250, VibrationEffect.DEFAULT_AMPLITUDE));
                    startActivity(PressAndHoldActivity.this, ConfirmEmergencyAlertActivity.class);
                } else {
                    //deprecated in API 26

                    v.vibrate(250);

                    startActivity(PressAndHoldActivity.this, ConfirmEmergencyAlertActivity.class);
                }
            }
        };

        Handler handler = new Handler();

        img_press_and_hold.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    firstTouch = System.currentTimeMillis();
                    handler.postDelayed(run, 5000);
                    break;
                case MotionEvent.ACTION_UP:
                    if (System.currentTimeMillis() - firstTouch < 5000)
                        handler.removeCallbacks(run);
                    Log.e("duration", String.valueOf(System.currentTimeMillis() - firstTouch));
                    break;
            }
            return true;
        });

        ll_back.setOnClickListener(view -> finish());
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
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        final LocationSettingsStates states = LocationSettingsStates.fromIntent(intent);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        img_press_and_hold.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.press_icon));
                        break;
                    case Activity.RESULT_CANCELED:
                        checkLocationService();
                        break;
                    default:
                        break;
                }
                break;
        }
    }
}
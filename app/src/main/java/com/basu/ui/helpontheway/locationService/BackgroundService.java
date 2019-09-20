package com.basu.ui.helpontheway.locationService;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.basu.R;
import com.basu.data.network.ApiEndPoint;
import com.basu.utils.VolleyClass;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class BackgroundService extends Service {
    private final LocationServiceBinder binder = new LocationServiceBinder();
    private final String TAG = "BackgroundService";
    private NotificationManager notificationManager;
    private NotificationChannel channel;
    private Notification.Builder builder;
    private VolleyClass volleyClass;
    private String current_location;
    private String userId;
    private int alertLocationTableId;
    private int alertId;
    private String jwtToken;


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        volleyClass = new VolleyClass(this);
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        volleyClass = new VolleyClass(this);
        Log.i(TAG, "onCreate");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(12345678, getNotification());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getFusedLocationProviderClient(this).removeLocationUpdates(new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
//                onLocationChanged(locationResult.getLastLocation());
            }
        });
    }

    @SuppressLint("MissingPermission")
    public void start(String userId, int alertLocationTableId, int alertId, String jwttoken) {
        this.userId = userId;
        this.alertLocationTableId = alertLocationTableId;
        this.alertId = alertId;
        this.jwtToken = jwttoken;

        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(30 * 1000);                      //30 Sec interval.
        mLocationRequest.setFastestInterval(30 * 1000);               //30 Sec interval.
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        // do work here
                        onLocationChanged(locationResult.getLastLocation());
                    }
                },
                Looper.myLooper());

    }

    private void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses.size() > 0) {
                current_location = addresses.get(0).getAddressLine(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        updateLocation(latitude, longitude, current_location);
        Log.e("Updated Location: ", Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude()));
    }

    public void stopTracking() {
        getFusedLocationProviderClient(this).removeLocationUpdates(new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
//                onLocationChanged(locationResult.getLastLocation());
            }
        });

        stopForeground(true);
        stopSelf();
    }

    private Notification getNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("channel_01", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
            builder = new Notification.Builder(getApplicationContext(), "channel_01")
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle("BASU using device location")
                    .setContentText("Do not turn off your location. BASU continuously tracking your location every minute.")
                    .setAutoCancel(true);
        }
        return builder.build();
    }

    public class LocationServiceBinder extends Binder {
        public BackgroundService getService() {
            return BackgroundService.this;
        }
    }

    private void updateLocation(double latitude, double longitude, String current_location) {
        JSONObject urlObject = new JSONObject();
        try {
            urlObject.put("alert_response", "abc");
            urlObject.put("current_location", current_location);
            urlObject.put("latitude", latitude);
            urlObject.put("longitude", longitude);
            urlObject.put("user_id", userId);
            urlObject.put("alert_location_tableId", alertLocationTableId);
            urlObject.put("alert_id", alertId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("Update Request: ", urlObject.toString());

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, ApiEndPoint.ENDPOINT_UPDATE_LOCATION, urlObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Update Response: ", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                error.printStackTrace();
            }
        }) {
            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer: " + jwtToken);
                return headers;
            }
        };
        volleyClass.addToRequestQueue(jsObjRequest);
    }
}
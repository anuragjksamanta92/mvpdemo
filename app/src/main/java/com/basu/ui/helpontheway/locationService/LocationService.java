package com.basu.ui.helpontheway.locationService;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
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
import java.util.Timer;
import java.util.TimerTask;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class LocationService extends Service {

    private static final String TAG_FOREGROUND_SERVICE = "FOREGROUND_SERVICE";

    public static final String ACTION_START_FOREGROUND_SERVICE = "ACTION_START_FOREGROUND_SERVICE";

    public static final String ACTION_STOP_FOREGROUND_SERVICE = "ACTION_STOP_FOREGROUND_SERVICE";

    private VolleyClass volleyClass;
    private String current_location;
    private String userId;
    private int alertLocationTableId;
    private int alertId;
    private String jwtToken;
    private double latitude;
    private double longitude;
    private static Timer timer = new Timer();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        volleyClass = new VolleyClass(this);
        Log.d(TAG_FOREGROUND_SERVICE, "My foreground service onCreate().");
        if (timer != null)
            timer.scheduleAtFixedRate(new UpdateLocation(), 0, 30000);
        else {
            timer = new Timer();
            timer.scheduleAtFixedRate(new UpdateLocation(), 0, 30000);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();

            if (action != null) {
                switch (action) {
                    case ACTION_START_FOREGROUND_SERVICE:
                        userId = intent.getExtras().getString("userId");
                        alertLocationTableId = intent.getExtras().getInt("alertLocationTableId");
                        alertId = intent.getExtras().getInt("alertId");
                        jwtToken = intent.getExtras().getString("jwtToken");
                        startForegroundService();
                        startLocationUpdating();
                        break;
                    case ACTION_STOP_FOREGROUND_SERVICE:
                        stopForegroundService();
                        break;
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdating() {

        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(30000);
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

    private void startForegroundService() {
        Log.d(TAG_FOREGROUND_SERVICE, "Start foreground service.");

        // Create notification default intent.
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_01", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
            builder = new NotificationCompat.Builder(getApplicationContext(), "channel_01");

        } else
            // Create notification builder.
            builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Sharing Location");
        builder.setContentText("We are sharing your location with our monitoring team to help you.");
        builder.setStyle(new NotificationCompat.BigTextStyle()
                .bigText("We are sharing your location with our monitoring team to help you."));
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(R.mipmap.ic_launcher_basu);
        Bitmap largeIconBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.basu_app_icon);
        builder.setLargeIcon(largeIconBitmap);
        // Make the notification max priority.
        // Make head-up notification.
        builder.setFullScreenIntent(pendingIntent, true);

        // Build the notification.
        Notification notification = builder.build();

        // Start foreground service.
        startForeground(1, notification);

    }

    private void stopForegroundService() {
        Log.d(TAG_FOREGROUND_SERVICE, "Stop foreground service.");

        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
        // Stop foreground service and remove the notification.
        stopForeground(true);

        // Stop the foreground service.
        stopSelf();
    }

    private void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
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

    private class UpdateLocation extends TimerTask {
        @Override
        public void run() {
            updateLocation(latitude, longitude, current_location);
        }
    }
}

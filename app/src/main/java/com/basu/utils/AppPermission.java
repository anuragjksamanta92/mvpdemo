package com.basu.utils;

import android.Manifest;
import android.content.Context;

import com.tedpark.tedpermission.rx2.TedRx2Permission;

public class AppPermission {

    private static boolean permission = false;
    private static boolean READ_CONTACTS_PERMISSION = false;
    private static boolean ACCESS_FINE_LOCATION_PERMISSION = false;
    public Context mContext;

    /*public AppPermission(Context mContext) {
        this.mContext = mContext;
    }*/

    public static boolean checkCameraPermission(Context mContext) {
        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        // only for gingerbread and newer versions

        TedRx2Permission.with(mContext)
                .setRationaleTitle("Allow")
                .setRationaleMessage("we need permission for using camera and phone storage") // "we need permission for read contact and find your location"
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .request()
                .subscribe(tedPermissionResult -> {
                        /*Intent i = new Intent(EventListActivity.this,QrCodeActivity.class);
                        startActivityForResult( i,REQUEST_CODE_QR_SCAN);
                        Toast.makeText(EventListActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();*//*Toast.makeText(EventListActivity.this,
                                "Permission Denied\n" + tedPermissionResult.getDeniedPermissions().toString(), Toast.LENGTH_SHORT)
                                .show();
                        System.out.println(tedPermissionResult.getDeniedPermissions().toString());*/
                    permission = tedPermissionResult.isGranted();
                }, throwable -> {
                });
        /*}else{
            Intent i = new Intent(EventListActivity.this,QrCodeActivity.class);
            startActivityForResult( i,REQUEST_CODE_QR_SCAN);
        }*/
        return permission;
    }

    public static boolean checkContactsPermission(Context mContext) {
        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        // only for gingerbread and newer versions

        TedRx2Permission.with(mContext)
                .setRationaleTitle("Allow")
                .setRationaleMessage("we need permission for read contact") // "we need permission for read contact and find your location"
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)
                .request()
                .subscribe(tedPermissionResult -> {
                    /*Intent i = new Intent(EventListActivity.this,QrCodeActivity.class);
                    startActivityForResult( i,REQUEST_CODE_QR_SCAN);
                    Toast.makeText(EventListActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();*//*Toast.makeText(EventListActivity.this,
                            "Permission Denied\n" + tedPermissionResult.getDeniedPermissions().toString(), Toast.LENGTH_SHORT)
                            .show();
                    System.out.println(tedPermissionResult.getDeniedPermissions().toString());*/
                    permission = tedPermissionResult.isGranted();
                }, throwable -> {
                });
        /*}else{
            Intent i = new Intent(EventListActivity.this,QrCodeActivity.class);
            startActivityForResult( i,REQUEST_CODE_QR_SCAN);
        }*/
        return permission;
    }

    public static boolean checkLocationPermission(Context context) {
        TedRx2Permission.with(context)
                .setRationaleTitle("Allow")
                .setRationaleMessage("We need permission for location") // "we need permission for read contact and find your location"
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .request()
                .subscribe(tedPermissionResult -> {
                    /*Intent i = new Intent(EventListActivity.this,QrCodeActivity.class);
                    startActivityForResult( i,REQUEST_CODE_QR_SCAN);
                    Toast.makeText(EventListActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();*//*Toast.makeText(EventListActivity.this,
                            "Permission Denied\n" + tedPermissionResult.getDeniedPermissions().toString(), Toast.LENGTH_SHORT)
                            .show();
                    System.out.println(tedPermissionResult.getDeniedPermissions().toString());*/
                    ACCESS_FINE_LOCATION_PERMISSION = tedPermissionResult.isGranted();
                }, throwable -> {
                });
        return ACCESS_FINE_LOCATION_PERMISSION;
    }
}

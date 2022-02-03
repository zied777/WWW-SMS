package com.zied.nasri.www_sms.Tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;

public class LocationTools {

    private static LocationManager locationManager;

    @SuppressLint("MissingPermission")
    public static void getCurrentLocation(Context context, LocationListener locationListener){

        if(locationManager==null)locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        String provider = LocationManager.NETWORK_PROVIDER;
        if(!isNetworkEnabled){
            provider = LocationManager.GPS_PROVIDER;
        }
        locationManager.requestLocationUpdates(provider, 0, 0, locationListener);
    }

    public static void stop(LocationListener listener){
        if(locationManager != null){
            locationManager.removeUpdates(listener);
        }
    }
}

package com.example.user.facebook;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by user on 6/15/2016.
 */
public class MyLocationListener implements LocationListener {
    private double lat,lon;
    @Override
    public void onLocationChanged(Location location) {
        lat=location.getLatitude();
        lon=location.getLongitude();

    }
    public double getLat()
    {
        return this.lat;
    }
    public double getLon()
    {
        return  this.lon;
    }
    public String user(){
        String s = "longitude="+lon+"lattitude="+lat+"";
        return s;
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

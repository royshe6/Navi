package com.example.roy.navi;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by Roy on 17/11/2015.
 */
public class MyLocationListener implements LocationListener
{

    private CarSendThread m_SendThread;

    public MyLocationListener(CarSendThread a_SendThread)
    {
        m_SendThread = a_SendThread;
    }

    @Override
    public void onLocationChanged(Location location)
    {
        m_SendThread.SetCurrentLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }
}

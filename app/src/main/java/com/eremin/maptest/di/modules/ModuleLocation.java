package com.eremin.maptest.di.modules;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.core.Observable;

@Module(includes = ModuleContext.class)
public class ModuleLocation implements LocationListener {

    private static Location mLocation;
    private LocationManager mLocationManager;

    @Provides
    public Observable<Location> getLocation(Context context) {
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationData();
        boolean enabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return Observable.interval(1000, TimeUnit.MILLISECONDS).create(subscriber -> {
            if(enabled){
                subscriber.onNext(mLocation);
            }else {
                /**
                 * TODO Запустить intent для включения GPS
                 */
            }
        });
    }
    @SuppressLint("MissingPermission")
    public void locationData(){
        mLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000 * 10,
                10,
                this);
        mLocationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000 * 10,
                10,
                this);
        mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }
    @Override
    public void onLocationChanged(Location location) {
        mLocation = location;
    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }
    @Override
    public void onProviderEnabled(String s) {
        locationData();
    }
    @SuppressLint("MissingPermission")
    @Override
    public void onProviderDisabled(String s) {
        //startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        mLocation = mLocationManager.getLastKnownLocation(s);
    }
}
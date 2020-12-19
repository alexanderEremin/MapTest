package com.eremin.maptest.di.modules;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;

@Module(includes = ModuleContext.class)
public class ModuleLocation implements LocationListener {

    private LocationManager mLocationManager;
    private PublishSubject<Location> publishLocation;
    private Location location;

    @SuppressLint("MissingPermission")
    @Provides
    public Observable<Location> getLocation(Context context) {
        publishLocation = PublishSubject.create();
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                1,
                this);
        mLocationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000,
                1,
                this);
        if(mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null){
            setLocation(mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        }
        return publishLocation.startWith(publishLocation);
    }

    public void setLocation(Location location) {
        this.location = location;
        publishLocation.onNext(location);
    }

    @Override
    public void onLocationChanged(Location location) {
        setLocation(location);
    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}
    @Override
    public void onProviderEnabled(String s) {
        //locationData();
    }
    @SuppressLint("MissingPermission")
    @Override
    public void onProviderDisabled(String s) {
        setLocation(mLocationManager.getLastKnownLocation(s));
    }
}
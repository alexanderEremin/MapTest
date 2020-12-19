package com.eremin.maptest.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends AndroidViewModel {

    private final LocationManager mLocationManager;
    private MutableLiveData<String> mText;
    private MutableLiveData<Double> lattitude, longitude;

    @SuppressLint("MissingPermission")
    public DashboardViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        lattitude = new MutableLiveData<>();
        longitude = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");

        mLocationManager = (LocationManager) application.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                1,
                locationListener);
        mLocationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000,
                1,
                locationListener);
    }
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            System.out.println("-----444--------location------------");
            longitude.setValue(location.getLongitude());
            lattitude.setValue(location.getLatitude());
        }
    };

    public MutableLiveData<Double> getLattitude() {
        return lattitude;
    }

    public MutableLiveData<Double> getLongitude() {
        return longitude;
    }

    public LiveData<String> getText() {
        return mText;
    }
}
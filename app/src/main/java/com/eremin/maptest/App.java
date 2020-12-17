package com.eremin.maptest;

import android.app.Application;
import android.os.StrictMode;
import android.preference.PreferenceManager;

import com.eremin.maptest.di.AppComponent;
import com.eremin.maptest.di.DaggerAppComponent;
import com.eremin.maptest.di.modules.ModuleContext;

import org.osmdroid.config.Configuration;
import org.osmdroid.library.BuildConfig;

public class App extends Application {
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildComponent();
        /**
         * Настройки для корректного отображения openStreetMap
         */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        /** END */
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .moduleContext(new ModuleContext(getApplicationContext()))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}

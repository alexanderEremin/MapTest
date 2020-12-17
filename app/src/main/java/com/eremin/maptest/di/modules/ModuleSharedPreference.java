package com.eremin.maptest.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ModuleContext.class})
public abstract class ModuleSharedPreference {
    @Provides
    static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}

package com.eremin.maptest.di.modules;

import android.content.Context;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleContext {
    private final Context mContext;
    public ModuleContext (Context context){
        mContext = context;
    }
    @Provides
    public Context getContext(){
        return mContext;
    }

}

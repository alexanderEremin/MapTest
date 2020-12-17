package com.eremin.maptest.di.modules;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Module
public abstract class ModuleComposeDisposable {
    @Provides
    static CompositeDisposable getComposeDisposable(){
        return new CompositeDisposable();
    }
}

package com.eremin.maptest.di;

import com.eremin.maptest.di.modules.ModuleComposeDisposable;
import com.eremin.maptest.di.modules.ModuleContext;
import com.eremin.maptest.di.modules.ModuleLocation;
import com.eremin.maptest.di.modules.ModuleRetrofit;
import com.eremin.maptest.di.modules.ModuleSharedPreference;
import com.eremin.maptest.ui.home.HomeViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ModuleRetrofit.class,
                      ModuleContext.class,
                      ModuleComposeDisposable.class,
                      ModuleSharedPreference.class,
                      ModuleLocation.class})
@Singleton
public interface AppComponent {
    void injectToViewModel(HomeViewModel viewModel);
}

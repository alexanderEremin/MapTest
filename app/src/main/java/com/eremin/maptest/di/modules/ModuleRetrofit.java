package com.eremin.maptest.di.modules;

import com.eremin.maptest.data.ConstantManager;
import com.eremin.maptest.interfaces.IForSputnik;
import com.eremin.maptest.interfaces.IForYandex;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class ModuleRetrofit {

    @Provides
    static IForSputnik getRetrofitSputnik(Gson gson){
        Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(ConstantManager.DEFAULT_URL_SPUTNIK_SEARCH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

        return mRetrofit.create(IForSputnik.class);
    }

    @Provides
    static IForYandex getRetrofitYandex(Gson gson){
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(ConstantManager.DEFAULT_URL_YANDEX_SEARCH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        return mRetrofit.create(IForYandex.class);
    }
    @Provides
    static Gson getGson(){
        return new Gson();
    }
}

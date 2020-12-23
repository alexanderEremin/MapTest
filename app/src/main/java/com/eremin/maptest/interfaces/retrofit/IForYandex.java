package com.eremin.maptest.interfaces.retrofit;

import com.eremin.maptest.pojo.sputnikssearsh.Main;
import com.eremin.maptest.pojo.yandexsearch.MainSearch;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IForYandex {
    @GET("v1/")
    Observable<MainSearch> getListSchools(@Query("text") String text,
                                          @Query("ll") String ll,
                                          @Query("lang") String lang,
                                          @Query("apikey") String apikey); // TODO Нужно уменьшить колиество параметров
    //?text=тело запроса&ll=50.78,46.9099&spn=0.552069,0.400552&lang=ru_RU&apikey=539175ef-5761-4af3-9c0b-1fcb74192592
}

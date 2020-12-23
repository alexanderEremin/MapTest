package com.eremin.maptest.interfaces.retrofit;

import com.eremin.maptest.pojo.sputnikssearsh.Main;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IForSputnik {
    @GET("point")
    Observable<Main> getAddressUser(@Query("lat") double lat, @Query("lon") double lon);
}

package com.eremin.maptest.interfaces.retrofit;

import com.eremin.maptest.pojo.dadatasearsh.Main;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IForDadata {
    @Headers({"Accept: application/json",
            "Authorization: Token 615a5e4b27ffdccef616d28e69485d187b849f4e"})
    @GET("4_1/rs/geolocate/address")
    Observable<Main> getAddressUser(@Query("lat") double lat, @Query("lon") double lon, @Query("lon") int count );
    // https://suggestions.dadata.ru/suggestions/api/4_1/rs/geolocate/address?lat=55.878&lon=37.653
}

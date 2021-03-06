package com.futuresky.simpleweathermate.Model;

import com.futuresky.simpleweathermate.ForeCasterApi.WeatherResponseList;
import com.futuresky.simpleweathermate.WeatherApi.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("data/2.5/forecast?")
    Call<WeatherResponseList> getWeatherForeCaster(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("units") String units,
            @Query("appid") String appId
    );


    @GET("data/2.5/weather?")
    Call<WeatherResponse> getByLatLng(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("units") String units,
            @Query("appid") String appId
    );
}

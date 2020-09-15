package com.example.wheathergooglemap.api;

import com.example.wheathergooglemap.pojo.CityWeatherPojo;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit API
 */
public interface WeatherApi {

    String WEATHER_API_KEY = "f33d491b436020cb0ce43118625a5d4f";

    @GET("weather")
    Observable<CityWeatherPojo> getWeatherInCity(@Query("id") int id,
                                                 @Query("appid") String key);
}

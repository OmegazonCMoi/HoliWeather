package com.example.holiweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("forecast")
    Call<WeatherResponse> getWeatherForecast(
            @Query("q") String city,
            @Query("cnt") int days,
            @Query("appid") String apiKey,
            @Query("units") String units
    );
}
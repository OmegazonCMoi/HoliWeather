package com.example.holiweather;

import com.example.holiweather.WeatherForecast;

import java.util.List;

public class WeatherResponse {
    private List<WeatherForecast> list;

    public List<WeatherForecast> getForecasts() {
        return list;
    }
}

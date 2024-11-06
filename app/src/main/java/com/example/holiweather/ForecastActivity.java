package com.example.holiweather;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;

import java.util.List;

public class ForecastActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    private TextView cityTitle;
    private String city;
    private int days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        recyclerView = findViewById(R.id.forecastRecyclerView);
        cityTitle = findViewById(R.id.resultTextView);

        city = getIntent().getStringExtra("city");
        days = getIntent().getIntExtra("days", 1);

        cityTitle.setText("Prévisions pour : " + city);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchWeatherForecast(city, days);
    }

    private void fetchWeatherForecast(String city, int days) {
        WeatherService weatherService = RetrofitClientInstance.getRetrofitInstance().create(WeatherService.class);
        Call<WeatherResponse> call = weatherService.getWeatherForecast(city, days, "e075a0b59517e88cc46940bb262add13", "metric");
        Log.d("ForecastActivity", "fetchWeatherForecast: " + call.request().url());

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<WeatherForecast> forecastList = response.body().getForecasts();
                    weatherAdapter = new WeatherAdapter(forecastList);
                    recyclerView.setAdapter(weatherAdapter);
                } else {
                    cityTitle.setText("Erreur de chargement des prévisions.");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                cityTitle.setText("Échec de connexion : " + t.getMessage());
            }
        });
    }
}

package com.example.holiweather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText cityEditText;
    private String city;
    private int days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        recyclerView = findViewById(R.id.forecastRecyclerView);
        cityTitle = findViewById(R.id.resultTextView);
        String cityEditText = getIntent().getStringExtra("cityEditText");
        Button openMapButton = findViewById(R.id.openMapButton);

        city = getIntent().getStringExtra("city");
        days = getIntent().getIntExtra("days", 1) * 4;

        cityTitle.setText("Prévisions pour : " + city);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchWeatherForecast(city, days);

        openMapButton.setOnClickListener(v -> {
            String city = cityEditText;
            if (!TextUtils.isEmpty(city)) {
                String location = "geo:0,0?q=" + city;
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(location));
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(this, "Aucune application de cartographie disponible", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Veuillez entrer une ville pour l'ouvrir sur la carte", Toast.LENGTH_SHORT).show();
            }
        });
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
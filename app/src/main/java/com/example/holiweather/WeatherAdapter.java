package com.example.holiweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private List<WeatherForecast> forecasts;

    public WeatherAdapter(List<WeatherForecast> forecasts) {
        this.forecasts = forecasts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_forecast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherForecast forecast = forecasts.get(position);
        holder.tempTextView.setText(String.format("%s°C", forecast.getMain().getTemp()));
        holder.descriptionTextView.setText(forecast.getWeather().get(0).getDescription());

        // String iconUrl = "https://openweathermap.org/img/wn/" + forecast.getWeather().get(0).getIcon() + "@2x.png";
        switch (forecast.getWeather().get(0).getIcon()) {
            case "01d":
            case "01n":
                holder.iconImageView.setImageResource(R.drawable.ic_clear_sky);
                break;
            case "02d":
            case "02n":
                holder.iconImageView.setImageResource(R.drawable.ic_few_clouds);
                break;
            case "03d":
            case "03n":
                holder.iconImageView.setImageResource(R.drawable.ic_scattered_clouds);
                break;
            case "04d":
            case "04n":
                holder.iconImageView.setImageResource(R.drawable.ic_broken_clouds);
                break;
            case "09d":
            case "09n":
                holder.iconImageView.setImageResource(R.drawable.ic_shower_rain);
                break;
            case "10d":
            case "10n":
                holder.iconImageView.setImageResource(R.drawable.ic_rain);
                break;
            case "11d":
            case "11n":
                holder.iconImageView.setImageResource(R.drawable.ic_thunderstorm);
                break;
            case "13d":
            case "13n":
                holder.iconImageView.setImageResource(R.drawable.ic_snow);
                break;
            case "50d":
            case "50n":
                holder.iconImageView.setImageResource(R.drawable.ic_mist);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tempTextView;
        TextView descriptionTextView;
        ImageView iconImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tempTextView = itemView.findViewById(R.id.temperature);
            descriptionTextView = itemView.findViewById(R.id.weatherDescription);
            iconImageView = itemView.findViewById(R.id.weatherIcon);
        }
    }
}
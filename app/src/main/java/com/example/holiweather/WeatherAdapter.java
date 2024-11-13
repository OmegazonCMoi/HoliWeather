package com.example.holiweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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

        holder.dateTextView.setText(forecast.getDt_txt());

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

    // Classe ViewHolder pour définir les vues
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tempTextView;
        TextView descriptionTextView;
        TextView dateTextView; // Nouveau TextView pour la date
        ImageView iconImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tempTextView = itemView.findViewById(R.id.temperature);
            descriptionTextView = itemView.findViewById(R.id.weatherDescription);
            dateTextView = itemView.findViewById(R.id.weatherDate); // Assurez-vous que cet ID correspond au TextView de la date dans item_weather_forecast.xml
            iconImageView = itemView.findViewById(R.id.weatherIcon);
        }
    }
}

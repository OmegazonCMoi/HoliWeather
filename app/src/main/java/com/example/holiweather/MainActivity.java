package com.example.holiweather;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText cityEditText;
    private EditText daysEditText;
    private Button validateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues
        cityEditText = findViewById(R.id.cityEditText);
        daysEditText = findViewById(R.id.daysEditText);
        validateButton = findViewById(R.id.validateButton);

        // Action pour le bouton de validation
        validateButton.setOnClickListener(v -> {
            String city = cityEditText.getText().toString().trim();
            String daysText = daysEditText.getText().toString().trim();

            // Vérifications de base
            if (TextUtils.isEmpty(city)) {
                Toast.makeText(MainActivity.this, "Veuillez entrer une ville", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(daysText)) {
                Toast.makeText(MainActivity.this, "Veuillez entrer un nombre de jours", Toast.LENGTH_SHORT).show();
            } else {
                int days = Integer.parseInt(daysText);
                if (days < 1 || days > 5) {
                    Toast.makeText(MainActivity.this, "Le nombre de jours doit être entre 1 et 5", Toast.LENGTH_SHORT).show();
                } else {
                    // Lancement de ForecastActivity avec les données
                    Intent intent = new Intent(MainActivity.this, ForecastActivity.class);
                    intent.putExtra("city", city);
                    intent.putExtra("days", days);
                    startActivity(intent);
                }
            }
        });
    }
}

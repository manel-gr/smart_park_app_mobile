package com.example.smartparktn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {
    private TextView greetingText;
    private View sabotButton;
    private View garageButton;
    private Button startNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        greetingText = findViewById(R.id.greetingText);
        sabotButton = findViewById(R.id.sabotButton);
        garageButton = findViewById(R.id.garageButton);
        startNowButton = findViewById(R.id.startNowButton);

        // Customize greeting based on user data
        String userName = getUserName();
        greetingText.setText("Hello, " + userName);

        sabotButton.setOnClickListener(v -> {
            Intent intent = new Intent(HelpActivity.this, SabotActivity.class);
            startActivity(intent);
        });

        garageButton.setOnClickListener(v -> {
            Intent intent = new Intent(HelpActivity.this, GarageActivity.class);
            startActivity(intent);
        });

        startNowButton.setOnClickListener(v -> finish());
    }

    private String getUserName() {
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        return prefs.getString("userName", "User");
    }
}


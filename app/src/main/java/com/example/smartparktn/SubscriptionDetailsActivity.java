package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class SubscriptionDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_details);

        // Initialize views
        ImageButton backButton = findViewById(R.id.backButton);
        Button homeButton = findViewById(R.id.homeButton);

        // Setup click listeners
        backButton.setOnClickListener(v -> finish());
        homeButton.setOnClickListener(v -> {
            // Clear activity stack and go to home
            finishAffinity();
            startActivity(new Intent(this, HomeActivity.class));      });
    }
}


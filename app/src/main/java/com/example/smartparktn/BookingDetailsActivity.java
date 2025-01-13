package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BookingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        String parkingLotName = getIntent().getStringExtra("parking_lot_name");

        // Initialize views
        ImageButton backButton = findViewById(R.id.backButton);
        TextView parkingNameText = findViewById(R.id.parkingNameText);
        Button homeButton = findViewById(R.id.homeButton);

        // Set parking lot name
        parkingNameText.setText(parkingLotName);

        // Setup click listeners
        backButton.setOnClickListener(v -> finish());

        homeButton.setOnClickListener(v -> {
            // Clear activity stack and go to home
            finishAffinity();
            startActivity(new Intent(this, TimerActivity.class));      });
    }


    }



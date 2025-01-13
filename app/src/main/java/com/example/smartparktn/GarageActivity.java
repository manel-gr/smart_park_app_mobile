package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GarageActivity extends AppCompatActivity {
    private ImageButton backButton;
    private ImageView garageImage;
    private TextView garageName;
    private TextView garageAddress;
    private TextView garageHours;
    private TextView garageDescription;
    private RatingBar ratingBar;
    private Button chatButton;
    private Button goNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);

        initializeViews();
        setupClickListeners();
        loadGarageDetails();
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        garageImage = findViewById(R.id.garageImage);
        garageName = findViewById(R.id.garageName);
        garageAddress = findViewById(R.id.garageAddress);
        garageHours = findViewById(R.id.garageHours);
        garageDescription = findViewById(R.id.garageDescription);
        ratingBar = findViewById(R.id.ratingBar);
        chatButton = findViewById(R.id.chatButton);
        goNowButton = findViewById(R.id.goNowButton);
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(v -> finish());

        chatButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, GarageChatActivity.class);
            startActivity(intent);
        });

        goNowButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, GarageTrackingActivity.class);
            startActivity(intent);
        });
    }

    private void loadGarageDetails() {
        garageName.setText("Morris Garages");
        garageAddress.setText("28 Belkhir, Hammam-Lif, Tunis");
        garageHours.setText("8:00 am - 10:00 pm");
        garageDescription.setText("Professional car care and maintenance in a modern facility. Our expert team provides comprehensive services, including maintenance, repairs, and detailing.");
        ratingBar.setRating(4.5f);
    }
}


package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotDetailActivity extends AppCompatActivity implements ParkingSpotAdapter.OnSpotClickListener {
    private RecyclerView spotsRecyclerView;
    private Button bookButton;
    private ParkingSpotAdapter spotAdapter;
    private Spinner floorSpinner;
    private TextView priceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot_detail);

        // Initialize views
        ImageButton backButton = findViewById(R.id.backButton);
        spotsRecyclerView = findViewById(R.id.spotsRecyclerView);
        bookButton = findViewById(R.id.bookButton);
        floorSpinner = findViewById(R.id.floorSpinner);
        priceText = findViewById(R.id.priceText);
        ImageButton infoButton = findViewById(R.id.infoButton);

        // Setup floor spinner
        ArrayAdapter<String> floorAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"1st Floor", "2nd Floor", "3rd Floor"});
        floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floorSpinner.setAdapter(floorAdapter);

        // Setup RecyclerView
        spotAdapter = new ParkingSpotAdapter(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        spotsRecyclerView.setLayoutManager(layoutManager);
        spotsRecyclerView.setAdapter(spotAdapter);

        // Load sample spots
        loadSampleSpots();

        // Setup click listeners
        backButton.setOnClickListener(v -> finish());
        bookButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, OrderDetailActivity.class);
            startActivity(intent);
        });
        infoButton.setOnClickListener(v -> {
            // Show info dialog or tooltip
            Toast.makeText(this, "Parking spot information", Toast.LENGTH_SHORT).show();
        });

        // Initially disable book button
        bookButton.setEnabled(false);
    }

    private void loadSampleSpots() {
        List<ParkingSpot> spots = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            boolean isAvailable = Math.random() > 0.3; // 70% spots are available
            spots.add(new ParkingSpot(i, isAvailable));
        }
        spotAdapter.setSpots(spots);
    }

    @Override
    public void onSpotClick(ParkingSpot spot) {
        bookButton.setEnabled(true);
        priceText.setText("5 DNT / 4 hours");
    }
}


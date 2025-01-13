package com.example.smartparktn;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import android.graphics.Color;

public class GarageTrackingActivity extends AppCompatActivity {
    private ImageButton backButton;
    private TextView addressText;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_tracking);

        initializeViews();
        setupMap();
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        addressText = findViewById(R.id.addressText);

        backButton.setOnClickListener(v -> finish());
    }

    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(googleMap -> {
                mMap = googleMap;
                drawRoute();
            });
        }
    }

    private void drawRoute() {
        if (mMap != null) {
            LatLng start = new LatLng(36.8065, 10.1815);
            LatLng end = new LatLng(36.8066, 10.1814);

            PolylineOptions polylineOptions = new PolylineOptions()
                    .add(start, end)
                    .width(5)
                    .color(Color.parseColor("#6200EE"));

            mMap.addPolyline(polylineOptions);
        }
    }
}


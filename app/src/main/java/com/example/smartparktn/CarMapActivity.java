package com.example.smartparktn;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import android.graphics.Color;

public class CarMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ImageButton backButton;
    private TextView addressText;
    private TextView timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_map);

        // Initialize views
        backButton = findViewById(R.id.backButton);
        addressText = findViewById(R.id.addressText);
        timeText = findViewById(R.id.timeText);

        // Setup map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Setup click listeners
        backButton.setOnClickListener(v -> finish());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Example coordinates - replace with actual car location
        LatLng carLocation = new LatLng(36.8065, 10.1815); // Tunis coordinates

        // Add car marker
        mMap.addMarker(new MarkerOptions()
                .position(carLocation)
                .title("Your Car"));

        // Draw route (simplified example)
        LatLng userLocation = new LatLng(36.8066, 10.1814);
        mMap.addPolyline(new PolylineOptions()
                .add(userLocation, carLocation)
                .width(5)
                .color(Color.parseColor("#6200EE")));

        // Move camera to show both points
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carLocation, 15f));

        // Update UI
        addressText.setText("123 Dhaka Street");
        timeText.setText("7 minutes");
    }
}


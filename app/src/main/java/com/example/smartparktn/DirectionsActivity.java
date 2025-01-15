package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class DirectionsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private EditText searchInput;
    private Button eTicketButton;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);

        // Initialize views
        ImageButton menuButton = findViewById(R.id.menuButton);
        ImageButton notificationButton = findViewById(R.id.notificationButton);
        searchInput = findViewById(R.id.searchInput);
        eTicketButton = findViewById(R.id.eTicketButton);

        // Setup click listeners
        menuButton.setOnClickListener(v -> {
            // Handle menu click
        });

        notificationButton.setOnClickListener(v -> {
            // Handle notifications
        });

        eTicketButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, BookingDetailsActivity.class);
            startActivity(intent);        });

        // Get the SupportMapFragment and request notification when the map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker for the parking location (example coordinates)
        LatLng parkingLocation = new LatLng(36.8065, 10.1815); // Tunis coordinates
        mMap.addMarker(new MarkerOptions().position(parkingLocation).title("Parking Location"));

        // Add a marker for the current location (example coordinates)
        LatLng currentLocation = new LatLng(36.8008, 10.1800); // Nearby location
        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Your Location"));

        // Move the camera to show both markers
        LatLng center = new LatLng(
                (parkingLocation.latitude + currentLocation.latitude) / 2,
                (parkingLocation.longitude + currentLocation.longitude) / 2
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 15));

        // Draw a simple line between the two points
        mMap.addPolyline(new PolylineOptions()
                .add(currentLocation, parkingLocation)
                .width(5)
                .color(getResources().getColor(R.color.purple_500)));
    }
}


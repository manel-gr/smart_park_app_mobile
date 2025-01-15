package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ParkingActivity extends AppCompatActivity implements OnMapReadyCallback, ParkingLotAdapter.OnParkingLotClickListener {
    private EditText searchInput;
    private RecyclerView parkingLotsRecyclerView;
    private View bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private ParkingLotAdapter parkingLotAdapter;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        // Initialize views
        searchInput = findViewById(R.id.searchInput);
        parkingLotsRecyclerView = findViewById(R.id.parkingLotsRecyclerView);
        bottomSheet = findViewById(R.id.bottomSheet);
        ImageButton backButton = findViewById(R.id.backButton);

        // Setup bottom sheet
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        // Setup RecyclerView
        parkingLotAdapter = new ParkingLotAdapter(this);
        parkingLotsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        parkingLotsRecyclerView.setAdapter(parkingLotAdapter);

        // Load sample data
        loadSampleParkingLots();

        // Setup click listeners
        backButton.setOnClickListener(v -> finish());

        searchInput.setOnClickListener(v -> {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        });

        // Initialize map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapContainer);
        mapFragment.getMapAsync(this);
    }

    private void loadSampleParkingLots() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot("Lakki Gardens Car Park", "2.5 km", "4 spots left", 10.5f));
        parkingLots.add(new ParkingLot("Central Station Parking", "1.2 km", "12 spots left", 8.0f));
        parkingLots.add(new ParkingLot("Mall of Tunisia", "3.7 km", "20 spots left", 15.0f));
        parkingLotAdapter.setParkingLots(parkingLots);
    }

    @Override
    public void onParkingLotClick(ParkingLot parkingLot) {
        Intent intent = new Intent(this, ParkingLotDetailActivity.class);
        intent.putExtra("parking_lot_name", parkingLot.getName());
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Tunisia and move the camera
        LatLng tunisia = new LatLng(36.8065, 10.1815);
        mMap.addMarker(new MarkerOptions().position(tunisia).title("Marker in Tunisia"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tunisia, 10));
    }
}


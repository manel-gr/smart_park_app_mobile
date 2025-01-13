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

public class ParkingActivity extends AppCompatActivity implements ParkingLotAdapter.OnParkingLotClickListener {
    private EditText searchInput;
    private RecyclerView parkingLotsRecyclerView;
    private View bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private ParkingLotAdapter parkingLotAdapter;

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
}


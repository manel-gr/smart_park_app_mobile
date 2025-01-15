package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private RecyclerView parkingRecyclerView;
    private ParkingAdapter parkingAdapter;
    private ImageButton logoutButton;
    private ImageButton profileButton;
    private TextView locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        parkingRecyclerView = findViewById(R.id.parkingRecyclerView);
        logoutButton = findViewById(R.id.logoutButton);
        profileButton = findViewById(R.id.profileButton);

        // Setup RecyclerView
        setupParkingList();

        // Setup bottom navigation
        setupBottomNavigation();

        // Setup click listeners
        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);

        });

        profileButton.setOnClickListener(v -> {
            // Handle profile click
        });


    }

    private void setupParkingList() {
        parkingAdapter = new ParkingAdapter(getParkingData());
        parkingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        parkingRecyclerView.setAdapter(parkingAdapter);
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.navigation_parking) {
                Intent intent = new Intent(HomeActivity.this, ParkingActivity.class);
                startActivity(intent);

                return true;
            } else if (itemId == R.id.navigation_help) {
                Intent intent = new Intent(HomeActivity.this, HelpActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.navigation_subscriptions) {
                Intent intent = new Intent(HomeActivity.this, SubscriptionActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private List<ParkingItem> getParkingData() {
        List<ParkingItem> items = new ArrayList<>();
        items.add(new ParkingItem(
                "Stationnement 24/7",
                "1415 Av. Maguire",
                "5,8 km",
                "3 DNT / 15 free",
                R.drawable.parking1
        ));
        items.add(new ParkingItem(
                "The Vallon Gardens",
                "2741 Rue Tremblay",
                "7,9 km",
                "4 DNT / 8 free",
                R.drawable.parking2
        ));
        return items;
    }
}


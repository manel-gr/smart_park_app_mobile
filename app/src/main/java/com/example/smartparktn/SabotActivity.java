package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.smartparktn.models.Car;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SabotActivity extends AppCompatActivity {
    private Button findMyCarButton;
    private Button changeVehicleButton;
    private ImageButton backButton;
    private DatabaseReference carsRef;
    private Car selectedCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabot);

        // Initialize views
        findMyCarButton = findViewById(R.id.findMyCarButton);
        changeVehicleButton = findViewById(R.id.changeVehicleButton);
        backButton = findViewById(R.id.backButton);

        // Initialize Firebase
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        carsRef = FirebaseDatabase.getInstance().getReference("cars").child(userId);

        // Load user's default car
        loadDefaultCar();

        // Setup click listeners
        findMyCarButton.setOnClickListener(v -> {
            Intent intent = new Intent(SabotActivity.this, CarMapActivity.class);
            if (selectedCar != null) {
                intent.putExtra("car_id", selectedCar.getId());
            }
            startActivity(intent);
        });

        changeVehicleButton.setOnClickListener(v -> {
            // Navigate to car selection activity
            startActivity(new Intent(SabotActivity.this, MyCarsActivity.class));
        });

        backButton.setOnClickListener(v -> finish());
    }

    private void loadDefaultCar() {
        carsRef.limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        selectedCar = snapshot.getValue(Car.class);
                        if (selectedCar != null) {
                            selectedCar.setId(snapshot.getKey());
                            updateUI();
                        }
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    private void updateUI() {
        if (selectedCar != null) {
            // Update vehicle info in the UI
            // You'll need to add TextViews for these in the layout
            ((TextView) findViewById(R.id.licensePlateText)).setText(selectedCar.getLicensePlate());
        }
    }
}


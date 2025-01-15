package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MyCarsActivity extends AppCompatActivity {
    private RecyclerView carsRecyclerView;
    private Button addCarButton;
    private View emptyCarsView;
    private ImageButton backButton;
    private DatabaseReference carsRef;
    private CarAdapter carAdapter;
    private List<com.example.smartparktn.models.Car> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars);

        // Initialize Firebase
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        carsRef = FirebaseDatabase.getInstance().getReference("cars").child(userId);

        // Initialize views
        carsRecyclerView = findViewById(R.id.carsRecyclerView);
        addCarButton = findViewById(R.id.addCarButton);
        emptyCarsView = findViewById(R.id.emptyCarsView);
        backButton = findViewById(R.id.backButton);

        // Setup RecyclerView
        carList = new ArrayList<>();
        carAdapter = new CarAdapter(carList);
        carsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        carsRecyclerView.setAdapter(carAdapter);

        // Setup buttons
        addCarButton.setOnClickListener(v -> {
            Intent intent = new Intent(MyCarsActivity.this, AddCarActivity.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(MyCarsActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Facultatif : termine l'activité actuelle pour qu'elle ne reste pas dans la pile
        });

        // Load cars from Firebase
        loadCars();
    }

    private void loadCars() {
        carsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                carList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    com.example.smartparktn.models.Car car = snapshot.getValue(com.example.smartparktn.models.Car.class);
                    if (car != null) {
                        car.setId(snapshot.getKey());
                        carList.add(car);
                    }
                }
                updateUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    private void updateUI() {
        if (carList.isEmpty()) {
            emptyCarsView.setVisibility(View.VISIBLE);
            carsRecyclerView.setVisibility(View.GONE);
        } else {
            emptyCarsView.setVisibility(View.GONE);
            carsRecyclerView.setVisibility(View.VISIBLE);
            carAdapter.notifyDataSetChanged();
        }
    }
}


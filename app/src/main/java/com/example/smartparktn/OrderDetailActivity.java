package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailActivity extends AppCompatActivity {
    private TextView timeSlotText;
    private Button changeVehicleButton;
    private Button confirmButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        // Initialize views
        ImageButton backButton = findViewById(R.id.backButton);
        timeSlotText = findViewById(R.id.timeSlotText);
        changeVehicleButton = findViewById(R.id.changeVehicleButton);
        confirmButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);

        // Set time slot
        timeSlotText.setText("10:00 - 14:00");

        // Setup click listeners
        backButton.setOnClickListener(v -> finish());

        changeVehicleButton.setOnClickListener(v -> {
            // Handle vehicle change
            Intent intent = new Intent(this, MyCarsActivity.class);
            startActivity(intent);
        });

        confirmButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, DirectionsActivity.class);
            startActivity(intent);
        });

        cancelButton.setOnClickListener(v -> finish());
    }
}


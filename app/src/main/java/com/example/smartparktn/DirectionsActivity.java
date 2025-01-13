package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class DirectionsActivity extends AppCompatActivity {
    private EditText searchInput;
    private Button eTicketButton;

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
    }
}


package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class AddCardActivity extends AppCompatActivity {
    private EditText nameInput;
    private EditText cardNumberInput;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        // Initialize views
        ImageButton backButton = findViewById(R.id.backButton);
        nameInput = findViewById(R.id.nameInput);
        cardNumberInput = findViewById(R.id.cardNumberInput);
        confirmButton = findViewById(R.id.confirmButton);

        // Setup click listeners
        backButton.setOnClickListener(v -> finish());

        confirmButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SubscriptionDetailsActivity.class);
            startActivity(intent);
        });
    }
}


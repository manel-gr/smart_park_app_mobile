package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartparktn.GarageChatActivity;
import com.example.smartparktn.GarageTrackingActivity;
import com.example.smartparktn.R;

public class GarageActivity extends AppCompatActivity {
    private ImageButton backButton;
    private ImageButton chatButton;
    private ImageView garageImage;
    private Button goNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        garageImage = findViewById(R.id.garageImage);
        chatButton = findViewById(R.id.chatButton);
        goNowButton = findViewById(R.id.goNowButton);
    }

    private void setupClickListeners() {
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        if (chatButton != null) {
            chatButton.setOnClickListener(v -> {
                Intent intent = new Intent(this, GarageChatActivity.class);
                startActivity(intent);
            });
        }

        if (goNowButton != null) {
            goNowButton.setOnClickListener(v -> {
                Intent intent = new Intent(this, GarageTrackingActivity.class);
                startActivity(intent);
            });
        }
    }
}


package com.example.smartparktn;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GarageCallActivity extends AppCompatActivity {
    private ImageButton backButton;
    private ImageButton endCallButton;
    private TextView agentName;
    private ImageView agentImage;
    private TextView callStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_call);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        endCallButton = findViewById(R.id.endCallButton);
        agentName = findViewById(R.id.agentName);
        agentImage = findViewById(R.id.agentImage);
        callStatus = findViewById(R.id.callStatus);

        agentName.setText("Sergio Ramasis");
        callStatus.setText("Connected");
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(v -> finish());
        endCallButton.setOnClickListener(v -> finish());
    }
}


package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionActivity extends AppCompatActivity implements SubscriptionAdapter.OnSubscriptionClickListener {
    private RecyclerView subscriptionRecyclerView;
    private Button activateButton;
    private SubscriptionAdapter subscriptionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        // Initialize views
        ImageButton backButton = findViewById(R.id.backButton);
        subscriptionRecyclerView = findViewById(R.id.subscriptionRecyclerView);
        activateButton = findViewById(R.id.activateButton);

        // Setup RecyclerView
        subscriptionAdapter = new SubscriptionAdapter(this);
        subscriptionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        subscriptionRecyclerView.setAdapter(subscriptionAdapter);

        // Load subscription options
        loadSubscriptionOptions();

        // Setup click listeners
        backButton.setOnClickListener(v -> finish());

        activateButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, PaymentMethodActivity.class);
            startActivity(intent);
        });
    }

    private void loadSubscriptionOptions() {
        List<SubscriptionOption> options = new ArrayList<>();
        options.add(new SubscriptionOption("Premium 1 month", "90.00 DNT", 1));
        options.add(new SubscriptionOption("Premium 3 months", "150.00 DNT", 3));
        options.add(new SubscriptionOption("Premium 6 months", "300.00 DNT", 6));
        options.add(new SubscriptionOption("Premium 12 months", "500.00 DNT", 12));
        subscriptionAdapter.setOptions(options);
    }

    @Override
    public void onSubscriptionClick(SubscriptionOption option) {
        subscriptionAdapter.setSelectedOption(option);
        activateButton.setEnabled(true);
    }
}


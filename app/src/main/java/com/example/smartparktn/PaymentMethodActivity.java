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

public class PaymentMethodActivity extends AppCompatActivity implements PaymentMethodAdapter.OnPaymentMethodClickListener {
    private RecyclerView paymentMethodsRecyclerView;
    private Button addCardButton;
    private PaymentMethodAdapter paymentMethodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        // Initialize views
        ImageButton backButton = findViewById(R.id.backButton);
        paymentMethodsRecyclerView = findViewById(R.id.paymentMethodsRecyclerView);
        addCardButton = findViewById(R.id.addCardButton);

        // Setup RecyclerView
        paymentMethodAdapter = new PaymentMethodAdapter(this);
        paymentMethodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        paymentMethodsRecyclerView.setAdapter(paymentMethodAdapter);

        // Load payment methods
        loadPaymentMethods();

        // Setup click listeners
        backButton.setOnClickListener(v -> finish());

        addCardButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddCardActivity.class);
            startActivity(intent);
        });
    }

    private void loadPaymentMethods() {
        List<PaymentMethod> methods = new ArrayList<>();
        methods.add(new PaymentMethod("Master Card", R.drawable.ic_mastercard));
        methods.add(new PaymentMethod("Visa", R.drawable.ic_visa));
        methods.add(new PaymentMethod("D17", R.drawable.ic_d17));
        paymentMethodAdapter.setMethods(methods);
    }

    @Override
    public void onPaymentMethodClick(PaymentMethod method) {
        Intent intent = new Intent(this, AddCardActivity.class);
        startActivity(intent);
    }
}


package com.example.smartparktn;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartparktn.models.Car;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCarActivity extends AppCompatActivity {
    private EditText nameInput;
    private EditText licensePlateInput;
    private Spinner countrySpinner;
    private Switch greenLicenseSwitch;
    private Button saveButton;
    private TextView cancelButton;
    private ImageButton backButton;
    private DatabaseReference carsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        // Initialize Firebase
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        carsRef = FirebaseDatabase.getInstance().getReference("cars").child(userId);

        // Initialize views
        nameInput = findViewById(R.id.nameInput);
        licensePlateInput = findViewById(R.id.licensePlateInput);
        countrySpinner = findViewById(R.id.countrySpinner);
        greenLicenseSwitch = findViewById(R.id.greenLicenseSwitch);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        backButton = findViewById(R.id.backButton);

        // Setup country spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);

        // Setup buttons
        saveButton.setOnClickListener(v -> saveCar());
        cancelButton.setOnClickListener(v -> finish());
        backButton.setOnClickListener(v -> finish());
    }

    private void saveCar() {
        String name = nameInput.getText().toString().trim();
        String licensePlate = licensePlateInput.getText().toString().trim();
        String country = countrySpinner.getSelectedItem().toString();
        boolean hasGreenLicense = greenLicenseSwitch.isChecked();

        if (TextUtils.isEmpty(name)) {
            nameInput.setError("Name is required");
            return;
        }

        if (TextUtils.isEmpty(licensePlate)) {
            licensePlateInput.setError("License plate is required");
            return;
        }

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Car car = new Car(name, licensePlate, country, hasGreenLicense, userId);

        carsRef.push().setValue(car)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(AddCarActivity.this, "Car saved successfully", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(AddCarActivity.this, "Failed to save car", Toast.LENGTH_SHORT).show();
                });
    }
}

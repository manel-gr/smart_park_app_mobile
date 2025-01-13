package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class EmailSignUpActivity extends AppCompatActivity {
    private EditText emailInput;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_signup);

        ImageButton backButton = findViewById(R.id.backButton);
        emailInput = findViewById(R.id.emailInput);
        continueButton = findViewById(R.id.continueButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                boolean isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches();
                continueButton.setEnabled(isValid);
                continueButton.setBackgroundTintList(ContextCompat.getColorStateList(
                        EmailSignUpActivity.this,
                        isValid ? R.color.purple_500 : R.color.purple_200
                ));
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                Intent intent = new Intent(EmailSignUpActivity.this, EmailVerificationActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }
}


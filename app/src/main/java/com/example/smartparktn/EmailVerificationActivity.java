package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;

public class EmailVerificationActivity extends AppCompatActivity {

    private ImageButton backButton;
    private TextView emailText;
    private EditText[] codeInputs;
    private Button verifyButton;
    private TextView differentEmailButton;
    private FirebaseAuth mAuth;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        mAuth = FirebaseAuth.getInstance();
        email = getIntent().getStringExtra("email");

        backButton = findViewById(R.id.backButton);
        emailText = findViewById(R.id.emailText);
        verifyButton = findViewById(R.id.verifyButton);
        differentEmailButton = findViewById(R.id.differentEmailButton);

        codeInputs = new EditText[]{
                findViewById(R.id.code1),
                findViewById(R.id.code2),
                findViewById(R.id.code3),
                findViewById(R.id.code4),
                findViewById(R.id.code5)
        };

        emailText.setText(email);

        backButton.setOnClickListener(v -> finish());

        for (int i = 0; i < codeInputs.length; i++) {
            final int index = i;
            codeInputs[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() == 1 && index < codeInputs.length - 1) {
                        codeInputs[index + 1].requestFocus();
                    }
                    updateVerifyButtonState();
                }
            });
        }

        verifyButton.setOnClickListener(v -> verifyCode());

        differentEmailButton.setOnClickListener(v -> {
            // Go back to email input activity
            Intent intent = new Intent(EmailVerificationActivity.this, EmailSignUpActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void updateVerifyButtonState() {
        boolean allFilled = true;
        for (EditText input : codeInputs) {
            if (input.getText().toString().isEmpty()) {
                allFilled = false;
                break;
            }
        }
        verifyButton.setEnabled(allFilled);
        verifyButton.setBackgroundTintList(ContextCompat.getColorStateList(this,
                allFilled ? R.color.purple_500 : R.color.purple_200));
    }

    private void verifyCode() {
        StringBuilder codeBuilder = new StringBuilder();
        for (EditText input : codeInputs) {
            codeBuilder.append(input.getText().toString());
        }
        String code = codeBuilder.toString();

        // Here you would typically verify the code with Firebase
        // For this example, we'll just check if the code is "12345"
        if (code.equals("12345")) {
            Toast.makeText(this, "Email verified successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EmailVerificationActivity.this, PasswordCreationActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid verification code", Toast.LENGTH_SHORT).show();
        }
    }
}


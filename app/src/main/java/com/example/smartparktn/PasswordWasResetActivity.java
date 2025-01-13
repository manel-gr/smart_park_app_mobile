package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordWasResetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_was_reset);

        Button goToLoginButton = findViewById(R.id.goToLoginButton);

        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers la page de connexion
                Intent intent = new Intent(PasswordWasResetActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Terminer cette activité
            }
        });
    }
}

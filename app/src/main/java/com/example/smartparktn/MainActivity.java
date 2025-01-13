package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Vérifier si l'utilisateur a déjà vu l'onboarding
        boolean hasSeenOnboarding = PreferenceManager
                .getDefaultSharedPreferences(this)
                .getBoolean("has_seen_onboarding", false);

        // Si l'utilisateur n'a pas vu l'onboarding, le rediriger vers OnboardingActivity
        if (!hasSeenOnboarding) {
            startActivity(new Intent(this, OnboardingActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        Button createAccountButton = findViewById(R.id.createAccountButton);
        TextView loginTextView = findViewById(R.id.loginTextView);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement login activity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class); // Remplacez par l'activité de connexion
                startActivity(intent);
            }
        });
    }
}


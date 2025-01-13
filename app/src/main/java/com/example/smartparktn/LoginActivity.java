package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Lien vers le fichier XML correspondant

        // Récupération du bouton "Log in with Email" (exemple d'ID)
        Button loginWithEmailButton = findViewById(R.id.emailButtonLogin);

        loginWithEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers une autre activité, comme DashboardActivity
                Intent intent = new Intent(LoginActivity.this, LoginWithEmailActivity.class);
                startActivity(intent);
                finish(); // Terminer LoginActivity si nécessaire
            }
        });
    }
}

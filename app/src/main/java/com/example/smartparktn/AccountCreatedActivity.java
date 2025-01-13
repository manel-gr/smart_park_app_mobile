package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AccountCreatedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_created);

        Button continueButton = findViewById(R.id.loginButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers une autre activité ou tableau de bord
                Intent intent = new Intent(AccountCreatedActivity.this,  MyCarsActivity.class);
                startActivity(intent);
                finish(); // Terminer cette activité
            }
        });
    }
}

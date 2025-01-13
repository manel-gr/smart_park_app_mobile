package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText emailInput;
    private Button resetPasswordButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Initialiser FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Lier les éléments UI
        emailInput = findViewById(R.id.email);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);

        // Ajouter un événement au bouton de réinitialisation
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();

                // Validation de l'email
                if (TextUtils.isEmpty(email)) {
                    emailInput.setError("Email est requis");
                    return;
                }

                // Envoyer un email de réinitialisation
                sendPasswordResetEmail(email);
            }
        });
    }

    private void sendPasswordResetEmail(String email) {
        Log.d("ResetPassword", "Envoi de l'email de réinitialisation à : " + email);

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Email de réinitialisation envoyé
                        Log.d("ResetPassword", "Email envoyé avec succès !");
                        Toast.makeText(ResetPasswordActivity.this, "Un email de réinitialisation a été envoyé à " + email, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ResetPasswordActivity.this, PasswordWasResetActivity.class);
                        startActivity(intent);
                        finish(); // Fermer l'activité
                    } else {
                        // Erreur lors de l'envoi
                        Log.d("ResetPassword", "Erreur : " + task.getException().getMessage());
                        Toast.makeText(ResetPasswordActivity.this, "Erreur : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

package com.example.smartparktn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginWithEmailActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailInput, passwordInput;
    private Button loginButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_email); // Vérifier que votre fichier XML est correct.

        // Initialiser FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Lier les éléments UI
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.logButton);
        TextView forgotPasswordTextView = findViewById(R.id.forgotPasswordText);

        // Ajouter un événement au bouton de connexion
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                // Validation des champs email et mot de passe
                if (TextUtils.isEmpty(email)) {
                    emailInput.setError("Email est requis");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    passwordInput.setError("Mot de passe est requis");
                    return;
                }

                // Connexion avec email et mot de passe
                signInWithEmailAndPassword(email, password);
            }
        });

        // Ajouter un événement au TextView "Forgot Password"
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers une autre activité, par exemple ResetPasswordActivity
                Intent intent = new Intent(LoginWithEmailActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signInWithEmailAndPassword(String email, String password) {
        Log.d("Login", "Tentative de connexion avec l'email : " + email);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Connexion réussie
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Log.d("Login", "Connexion réussie !");
                            Toast.makeText(LoginWithEmailActivity.this, "Bienvenue " + user.getEmail(), Toast.LENGTH_SHORT).show();
                            // Rediriger vers le tableau de bord (Dashboard)
                            Intent intent = new Intent(LoginWithEmailActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish(); // Terminer cette activité
                        }
                    } else {
                        // Erreur lors de la connexion
                        Log.d("Login", "Erreur de connexion : " + task.getException().getMessage());
                        Toast.makeText(LoginWithEmailActivity.this, "Erreur de connexion. Vérifiez vos informations.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

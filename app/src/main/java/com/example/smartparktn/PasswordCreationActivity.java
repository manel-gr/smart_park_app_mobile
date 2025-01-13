package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordCreationActivity extends AppCompatActivity {

    private EditText passwordEditText;
    private Button createAccountButton;
    private FirebaseAuth mAuth;
    private String email;
    private CheckBox lengthCheck, numberCheck, symbolCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_creation);

        mAuth = FirebaseAuth.getInstance();
        email = getIntent().getStringExtra("email"); // Email envoyé depuis l'activité précédente

        passwordEditText = findViewById(R.id.passwordInput);
        createAccountButton = findViewById(R.id.continueButton);
        lengthCheck = findViewById(R.id.lengthCheck);
        numberCheck = findViewById(R.id.numberCheck);
        symbolCheck = findViewById(R.id.symbolCheck);

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String password = charSequence.toString();
                checkPasswordStrength(password);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(password)) {
                    createAccount(email, password);
                } else {
                    Toast.makeText(PasswordCreationActivity.this, "Veuillez entrer un mot de passe", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkPasswordStrength(String password) {
        boolean hasMinLength = password.length() >= 8;
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSymbol = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        lengthCheck.setChecked(hasMinLength);
        numberCheck.setChecked(hasNumber);
        symbolCheck.setChecked(hasSymbol);

        // Activez le bouton uniquement si toutes les conditions sont remplies
        createAccountButton.setEnabled(hasMinLength && hasNumber && hasSymbol);
    }

    private void createAccount(String email, String password) {
        // Créer un utilisateur avec email et mot de passe
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(PasswordCreationActivity.this, "Compte créé avec succès", Toast.LENGTH_SHORT).show();
                            // Rediriger vers une autre activité ou terminer
                            Intent intent = new Intent(PasswordCreationActivity.this, AccountCreatedActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Gérer les erreurs éventuelles
                            String errorMessage = task.getException() != null ?
                                    task.getException().getMessage() :
                                    "Erreur inconnue";
                            Toast.makeText(PasswordCreationActivity.this, "Erreur : " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

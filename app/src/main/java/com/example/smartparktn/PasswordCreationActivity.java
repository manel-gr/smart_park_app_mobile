package com.example.smartparktn;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
    private ProgressBar strengthIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_creation);

        mAuth = FirebaseAuth.getInstance();
        email = getIntent().getStringExtra("email"); // Email reçu depuis l'activité précédente

        // Initialiser les vues
        passwordEditText = findViewById(R.id.passwordInput);
        createAccountButton = findViewById(R.id.continueButton);
        lengthCheck = findViewById(R.id.lengthCheck);
        numberCheck = findViewById(R.id.numberCheck);
        symbolCheck = findViewById(R.id.symbolCheck);
        strengthIndicator = findViewById(R.id.strengthIndicator);

        // Ajouter un TextWatcher pour surveiller les changements dans le mot de passe
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

        // Gérer le clic sur le bouton "Créer un compte"
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(PasswordCreationActivity.this, "Veuillez entrer un mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isValidEmail(email)) {
                    createAccount(email, password);
                } else {
                    Toast.makeText(PasswordCreationActivity.this, "Adresse email invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkPasswordStrength(String password) {
        // Vérifiez les critères de force du mot de passe
        boolean hasMinLength = password.length() >= 8;
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSymbol = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        // Mettez à jour les cases à cocher
        updateCheckBox(lengthCheck, hasMinLength);
        updateCheckBox(numberCheck, hasNumber);
        updateCheckBox(symbolCheck, hasSymbol);

        // Calculez la force du mot de passe
        int strength = 0;
        if (hasMinLength) strength += 33;
        if (hasNumber) strength += 33;
        if (hasSymbol) strength += 34;

        // Animer la barre de progression
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(strengthIndicator, "progress", strengthIndicator.getProgress(), strength);
        progressAnimator.setDuration(300); // Durée de l'animation
        progressAnimator.start();

        // Mettez à jour la couleur de la barre de progression
        if (strength < 50) {
            strengthIndicator.getProgressDrawable().setColorFilter(
                    ContextCompat.getColor(this, R.color.weak), android.graphics.PorterDuff.Mode.SRC_IN);
        } else if (strength < 100) {
            strengthIndicator.getProgressDrawable().setColorFilter(
                    ContextCompat.getColor(this, R.color.medium), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            strengthIndicator.getProgressDrawable().setColorFilter(
                    ContextCompat.getColor(this, R.color.strong), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        // Activez le bouton uniquement si tous les critères sont remplis
        createAccountButton.setEnabled(strength == 100);
        createAccountButton.setBackgroundTintList(ContextCompat.getColorStateList(this,
                strength == 100 ? R.color.purple_500 : R.color.purple_200));
    }

    private void updateCheckBox(CheckBox checkBox, boolean isChecked) {
        checkBox.setChecked(isChecked);
        if (isChecked) {
            checkBox.setTextColor(ContextCompat.getColor(this, R.color.strong)); // Vert si le critère est rempli
        } else {
            checkBox.setTextColor(ContextCompat.getColor(this, R.color.gray)); // Gris sinon
        }
    }

    private void createAccount(String email, String password) {
        // Créez un utilisateur avec email et mot de passe
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(PasswordCreationActivity.this, "Compte créé avec succès", Toast.LENGTH_SHORT).show();
                            // Redirigez vers une autre activité ou terminez
                            Intent intent = new Intent(PasswordCreationActivity.this, AccountCreatedActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Gérez les erreurs éventuelles
                            if (task.getException() != null) {
                                String errorMessage;
                                if (task.getException().getMessage().contains("email")) {
                                    errorMessage = "Cet email est déjà utilisé.";
                                } else if (task.getException().getMessage().contains("password")) {
                                    errorMessage = "Le mot de passe est trop faible.";
                                } else {
                                    errorMessage = task.getException().getMessage();
                                }
                                Toast.makeText(PasswordCreationActivity.this, "Erreur : " + errorMessage, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(PasswordCreationActivity.this, "Erreur inconnue", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

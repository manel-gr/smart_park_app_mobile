package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity {
    private TextView hoursText;
    private TextView minutesText;
    private TextView secondsText;
    private Button extendTimeButton;
    private ImageButton closeButton;
    private ImageButton homeButton;
    private CircularProgressView progressView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 3 * 60 * 60 * 1000; // 3 hours
    private long totalTime = 3 * 60 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // Initialize views
        hoursText = findViewById(R.id.hoursText);
        minutesText = findViewById(R.id.minutesText);
        secondsText = findViewById(R.id.secondsText);
        extendTimeButton = findViewById(R.id.extendTimeButton);
        closeButton = findViewById(R.id.closeButton);
        homeButton = findViewById(R.id.homeButton);
        progressView = findViewById(R.id.progressView);

        // Setup click listeners
        extendTimeButton.setOnClickListener(v -> {
            // Handle time extension
            extendTime();
        });

        closeButton.setOnClickListener(v -> {
            // Créer un nouvel Intent pour démarrer HomeActivity
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

            // Fermer l'activité actuelle
            finish();
        });

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        startTimer();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerUI();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateTimerUI();
            }
        }.start();
    }

    private void updateTimerUI() {
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((timeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        hoursText.setText(String.format("%02d", hours));
        minutesText.setText(String.format("%02d", minutes));
        secondsText.setText(String.format("%02d", seconds));

        float progress = (float) timeLeftInMillis / totalTime;
        progressView.setProgress(progress);
    }

    private void extendTime() {
        // Add 1 hour
        timeLeftInMillis += 60 * 60 * 1000;
        totalTime += 60 * 60 * 1000;
        countDownTimer.cancel();
        startTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}


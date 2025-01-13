package com.example.smartparktn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.viewpager2.widget.ViewPager2;

public class OnboardingActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private Button skipButton;
    private Button nextButton;
    private OnboardingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        skipButton = findViewById(R.id.skipButton);
        nextButton = findViewById(R.id.nextButton);

        adapter = new OnboardingAdapter(this);
        viewPager.setAdapter(adapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateButtonsState(position);
            }
        });

        skipButton.setOnClickListener(v -> finishOnboarding());

        nextButton.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() == adapter.getItemCount() - 1) {
                finishOnboarding();
            } else {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });

        updateButtonsState(0);
    }

    private void updateButtonsState(int position) {
        // La logique qui change le texte de "Next" à "Get Started" est supprimée.
        nextButton.setText("Next"); // Le texte du bouton "Next" reste toujours "Next"
        skipButton.setVisibility(View.VISIBLE); // Le bouton "Skip" est toujours visible
    }

    private void finishOnboarding() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putBoolean("has_seen_onboarding", true).apply();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

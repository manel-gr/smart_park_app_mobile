package com.example.smartparktn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private List<OnboardingItem> items;

    public OnboardingAdapter(Context context) {
        this.items = new ArrayList<>();

        // Add onboarding items
        items.add(new OnboardingItem(R.drawable.img2,
                "You can feel best performance on your drive \uD83D\uDCAA"));
        items.add(new OnboardingItem(R.drawable.img1,
                "Awesome \uD83E\uDDE4experience car energy charge"));
        items.add(new OnboardingItem(R.drawable.img1,
                "Awesome \uD83E\uDDE4experience car energy charge"));
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_onboarding, parent, false);
        return new OnboardingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class OnboardingViewHolder extends RecyclerView.ViewHolder {
        private ImageView slideImage;
        private TextView slideTitle;

        OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            slideImage = itemView.findViewById(R.id.slideImage);
            slideTitle = itemView.findViewById(R.id.slideTitle);
        }

        void bind(OnboardingItem item) {
            slideImage.setImageResource(item.imageRes);
            slideTitle.setText(item.title);
        }
    }

    static class OnboardingItem {
        int imageRes;
        String title;

        OnboardingItem(int imageRes, String title) {
            this.imageRes = imageRes;
            this.title = title;
        }
    }
}


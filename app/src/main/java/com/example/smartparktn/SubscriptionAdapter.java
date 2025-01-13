package com.example.smartparktn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder> {
    private List<SubscriptionOption> options = new ArrayList<>();
    private SubscriptionOption selectedOption;
    private OnSubscriptionClickListener listener;

    public interface OnSubscriptionClickListener {
        void onSubscriptionClick(SubscriptionOption option);
    }

    public SubscriptionAdapter(OnSubscriptionClickListener listener) {
        this.listener = listener;
    }

    public void setOptions(List<SubscriptionOption> options) {
        this.options = options;
        notifyDataSetChanged();
    }

    public void setSelectedOption(SubscriptionOption option) {
        this.selectedOption = option;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubscriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subscription, parent, false);
        return new SubscriptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubscriptionViewHolder holder, int position) {
        SubscriptionOption option = options.get(position);
        holder.bind(option);
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    class SubscriptionViewHolder extends RecyclerView.ViewHolder {
        private RadioButton radioButton;
        private TextView nameText;
        private TextView priceText;

        SubscriptionViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.radioButton);
            nameText = itemView.findViewById(R.id.nameText);
            priceText = itemView.findViewById(R.id.priceText);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    SubscriptionOption option = options.get(position);
                    listener.onSubscriptionClick(option);
                }
            });
        }

        void bind(SubscriptionOption option) {
            nameText.setText(option.getName());
            priceText.setText(option.getPrice());
            radioButton.setChecked(option == selectedOption);
        }
    }
}


package com.example.smartparktn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpotAdapter extends RecyclerView.Adapter<ParkingSpotAdapter.ParkingSpotViewHolder> {
    private List<ParkingSpot> spots = new ArrayList<>();
    private ParkingSpot selectedSpot = null;
    private OnSpotClickListener listener;

    public interface OnSpotClickListener {
        void onSpotClick(ParkingSpot spot);
    }

    public ParkingSpotAdapter(OnSpotClickListener listener) {
        this.listener = listener;
    }

    public void setSpots(List<ParkingSpot> spots) {
        this.spots = spots;
        notifyDataSetChanged();
    }

    public void setSelectedSpot(ParkingSpot spot) {
        ParkingSpot oldSelection = selectedSpot;
        selectedSpot = spot;

        if (oldSelection != null) {
            notifyItemChanged(spots.indexOf(oldSelection));
        }
        if (selectedSpot != null) {
            notifyItemChanged(spots.indexOf(selectedSpot));
        }
    }

    @NonNull
    @Override
    public ParkingSpotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_parking_spot, parent, false);
        return new ParkingSpotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingSpotViewHolder holder, int position) {
        ParkingSpot spot = spots.get(position);
        holder.bind(spot);

        if (spot == selectedSpot) {
            holder.carIcon.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.blue_500));
        } else {
            holder.carIcon.setColorFilter(null);
        }
    }

    @Override
    public int getItemCount() {
        return spots.size();
    }

    class ParkingSpotViewHolder extends RecyclerView.ViewHolder {
        private ImageView carIcon;
        private TextView spotNumberText;
        private View selectionIndicator;
        private View spotContainer;

        ParkingSpotViewHolder(@NonNull View itemView) {
            super(itemView);
            carIcon = itemView.findViewById(R.id.carIcon);
            spotNumberText = itemView.findViewById(R.id.spotNumberText);
            selectionIndicator = itemView.findViewById(R.id.selectionIndicator);
            spotContainer = itemView.findViewById(R.id.spotContainer);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    ParkingSpot spot = spots.get(position);
                    if (spot.isAvailable()) {
                        setSelectedSpot(spot);
                        listener.onSpotClick(spot);
                    }
                }
            });
        }

        void bind(ParkingSpot spot) {
            spotNumberText.setText("A" + spot.getNumber());
            carIcon.setAlpha(spot.isAvailable() ? 1f : 0.5f);
            selectionIndicator.setVisibility(spot == selectedSpot ? View.VISIBLE : View.GONE);
        }
    }
}


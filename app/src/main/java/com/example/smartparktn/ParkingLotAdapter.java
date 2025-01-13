package com.example.smartparktn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotAdapter extends RecyclerView.Adapter<ParkingLotAdapter.ParkingLotViewHolder> {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private OnParkingLotClickListener listener;

    public interface OnParkingLotClickListener {
        void onParkingLotClick(ParkingLot parkingLot);
    }

    public ParkingLotAdapter(OnParkingLotClickListener listener) {
        this.listener = listener;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ParkingLotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_parking_lot, parent, false);
        return new ParkingLotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingLotViewHolder holder, int position) {
        ParkingLot parkingLot = parkingLots.get(position);
        holder.bind(parkingLot);
    }

    @Override
    public int getItemCount() {
        return parkingLots.size();
    }

    class ParkingLotViewHolder extends RecyclerView.ViewHolder {
        private TextView nameText;
        private TextView distanceText;
        private TextView availabilityText;
        private TextView priceText;

        ParkingLotViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            distanceText = itemView.findViewById(R.id.distanceText);
            availabilityText = itemView.findViewById(R.id.availabilityText);
            priceText = itemView.findViewById(R.id.priceText);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onParkingLotClick(parkingLots.get(position));
                }
            });
        }

        void bind(ParkingLot parkingLot) {
            nameText.setText(parkingLot.getName());
            distanceText.setText(parkingLot.getDistance());
            availabilityText.setText(parkingLot.getAvailability());
            priceText.setText(String.format("%.1f DT/h", parkingLot.getPricePerHour()));
        }
    }
}


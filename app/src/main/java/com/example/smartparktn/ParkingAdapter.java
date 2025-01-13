package com.example.smartparktn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder> {
    private List<ParkingItem> parkingItems;

    public ParkingAdapter(List<ParkingItem> parkingItems) {
        this.parkingItems = parkingItems;
    }

    @NonNull
    @Override
    public ParkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_parking, parent, false);
        return new ParkingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingViewHolder holder, int position) {
        ParkingItem item = parkingItems.get(position);
        holder.parkingImage.setImageResource(item.getImageResource());
        holder.parkingName.setText(item.getName());
        holder.parkingAddress.setText(item.getAddress());
        holder.parkingDistance.setText(item.getDistance());
        holder.parkingPriceAndSpaces.setText(item.getPriceAndSpaces());
    }

    @Override
    public int getItemCount() {
        return parkingItems.size();
    }

    static class ParkingViewHolder extends RecyclerView.ViewHolder {
        ImageView parkingImage;
        TextView parkingName;
        TextView parkingAddress;
        TextView parkingDistance;
        TextView parkingPriceAndSpaces;

        ParkingViewHolder(View itemView) {
            super(itemView);
            parkingImage = itemView.findViewById(R.id.parkingImage);
            parkingName = itemView.findViewById(R.id.parkingName);
            parkingAddress = itemView.findViewById(R.id.parkingAddress);
            parkingDistance = itemView.findViewById(R.id.parkingDistance);
            parkingPriceAndSpaces = itemView.findViewById(R.id.parkingPriceAndSpaces);
        }
    }
}


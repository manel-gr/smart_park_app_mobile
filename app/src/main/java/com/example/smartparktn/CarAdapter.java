package com.example.smartparktn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private List<com.example.smartparktn.models.Car> cars;

    public CarAdapter(List<com.example.smartparktn.models.Car> cars) {
        this.cars = cars;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        com.example.smartparktn.models.Car car = cars.get(position);
        holder.nameText.setText(car.getName());
        holder.licensePlateText.setText(car.getLicensePlate());
        holder.countryText.setText(car.getCountry());
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView licensePlateText;
        TextView countryText;

        CarViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            licensePlateText = itemView.findViewById(R.id.licensePlateText);
            countryText = itemView.findViewById(R.id.countryText);
        }
    }
}


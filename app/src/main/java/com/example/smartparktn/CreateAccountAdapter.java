package com.example.smartparktn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CreateAccountAdapter extends RecyclerView.Adapter<CreateAccountAdapter.StepViewHolder> {
    private final Context context;
    private final int[] layouts = {
            R.layout.step_email,
            R.layout.step_verification,
            R.layout.step_password,
            R.layout.step_complete
    };

    public CreateAccountAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layouts[viewType], parent, false);
        view.setTag("step_" + viewType);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        // ViewHolder is just a container, no binding needed
    }

    @Override
    public int getItemCount() {
        return layouts.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class StepViewHolder extends RecyclerView.ViewHolder {
        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}


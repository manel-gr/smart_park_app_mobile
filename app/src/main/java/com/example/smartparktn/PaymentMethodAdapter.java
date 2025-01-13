package com.example.smartparktn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder> {
    private List<PaymentMethod> methods = new ArrayList<>();
    private PaymentMethod selectedMethod;
    private OnPaymentMethodClickListener listener;

    public interface OnPaymentMethodClickListener {
        void onPaymentMethodClick(PaymentMethod method);
    }

    public PaymentMethodAdapter(OnPaymentMethodClickListener listener) {
        this.listener = listener;
    }

    public void setMethods(List<PaymentMethod> methods) {
        this.methods = methods;
        notifyDataSetChanged();
    }

    public void setSelectedMethod(PaymentMethod method) {
        this.selectedMethod = method;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_payment_method, parent, false);
        return new PaymentMethodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder holder, int position) {
        PaymentMethod method = methods.get(position);
        holder.bind(method);
    }

    @Override
    public int getItemCount() {
        return methods.size();
    }

    class PaymentMethodViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconImage;
        private TextView nameText;
        private RadioButton radioButton;

        PaymentMethodViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImage);
            nameText = itemView.findViewById(R.id.nameText);
            radioButton = itemView.findViewById(R.id.radioButton);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    PaymentMethod method = methods.get(position);
                    setSelectedMethod(method);
                    listener.onPaymentMethodClick(method);
                }
            });
        }

        void bind(PaymentMethod method) {
            iconImage.setImageResource(method.getIconResource());
            nameText.setText(method.getName());
            radioButton.setChecked(method == selectedMethod);
        }
    }
}


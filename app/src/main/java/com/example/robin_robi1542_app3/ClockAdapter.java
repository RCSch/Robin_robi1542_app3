package com.example.robin_robi1542_app3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ClockAdapter extends RecyclerView.Adapter<ClockAdapter.ClockViewHolder> {
    private List<ClockActivity> clockList;

    public ClockAdapter(List<ClockActivity> clockList) {
        this.clockList = clockList;
    }

    @NonNull
    @Override
    public ClockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clock_item, parent, false);
        return new ClockViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClockViewHolder holder, int position) {
        // Bind data to the ViewHolder (if needed)
        // You might want to set text or handle clicks here
    }

    @Override
    public int getItemCount() {
        return clockList.size();
    }

    public static class ClockViewHolder extends RecyclerView.ViewHolder {
        // Declare your UI components here (e.g., TextView, Button)
        public ClockViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize UI components
        }
    }
}

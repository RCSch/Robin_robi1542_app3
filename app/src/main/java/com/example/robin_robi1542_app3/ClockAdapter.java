package com.example.robin_robi1542_app3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ClockAdapter extends RecyclerView.Adapter<ClockAdapter.ClockViewHolder> {
    private List<ClockActivity> clockList;
    private ClockListener clockListener;

    public ClockAdapter(List<ClockActivity> clockList, ClockListener clockListener) {
        this.clockList = clockList;
        this.clockListener = clockListener;
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
        // Bind data to the ViewHolder
        ClockActivity currentClock = clockList.get(position);

        // Set text or handle clicks here
        holder.textViewHours.setText(String.valueOf(currentClock.getHours()));
        holder.textViewMinutes.setText(String.valueOf(currentClock.getMinutes()));
        holder.textViewSeconds.setText(String.valueOf(currentClock.getSeconds()));

        // Set the delete button click listener
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Notify the listener when the delete button is clicked
                if (clockListener != null) {
                    clockListener.onDeleteButtonClick(currentClock);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return clockList.size();
    }

    public void removeClock(ClockActivity clock) {
        clockList.remove(clock);
        notifyDataSetChanged();
    }

    public static class ClockViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHours, textViewMinutes, textViewSeconds;
        Button btnDelete;

        public ClockViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHours = itemView.findViewById(R.id.editTextHours);
            textViewMinutes = itemView.findViewById(R.id.editTextMinutes);
            textViewSeconds = itemView.findViewById(R.id.editTextSeconds);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}

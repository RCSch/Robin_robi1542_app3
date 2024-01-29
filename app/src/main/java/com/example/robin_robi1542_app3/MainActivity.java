package com.example.robin_robi1542_app3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClockListener {

    private RecyclerView recyclerView;
    private ClockAdapter clockAdapter;
    private List<ClockActivity> clockList;
    private Button btnNew;

    private Handler handler;

    private EditText startHours, startMinutes, startSeconds;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnNew = findViewById(R.id.btnNew);

        // Initialize the clock list and adapter
        clockList = new ArrayList<>();

        clockAdapter = new ClockAdapter(clockList, this);
        recyclerView.setAdapter(clockAdapter);

        // Set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewClock();
            }
        });
    }
    private void addNewClock() {
        // Retrieve the values from the EditText fields
        EditText editTextHours = findViewById(R.id.startHours);
        EditText editTextMinutes = findViewById(R.id.startMinutes);
        EditText editTextSeconds = findViewById(R.id.startTextSeconds);

        // Here you should obtain the text from the EditText fields
        String hoursText = editTextHours.getText().toString();
        String minutesText = editTextMinutes.getText().toString();
        String secondsText = editTextSeconds.getText().toString();

        // Log the retrieved values for debugging
        Log.d("AddNewClock", "Hours: " + hoursText + " Minutes: " + minutesText + " Seconds: " + secondsText);

        // Check if any of the input strings is empty
        if (hoursText.isEmpty() || minutesText.isEmpty() || secondsText.isEmpty()) {
            // Handle the case where any of the input strings is empty
            // You may show a toast or provide feedback to the user
            return;
        }

        // Convert hours, minutes, and seconds to milliseconds
        long totalMilliseconds = (Integer.parseInt(hoursText) * 3600 +
                Integer.parseInt(minutesText) * 60 +
                Integer.parseInt(secondsText)) * 1000;

        // Log the totalMilliseconds for debugging
        Log.d("AddNewClock", "Total Milliseconds: " + totalMilliseconds);

        // Pass the totalMilliseconds when creating a new clock
        ClockActivity newClock = new ClockActivity(totalMilliseconds, 1000, this, handler);

        // Add the new clock to the list and notify the adapter
        clockList.add(newClock);

        if (clockAdapter != null) {
            clockAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onStartStopClick(ClockActivity clock) {
        // Start or stop the countdown based on the current state
        clock.startStop();

        // Notify the adapter that the data set has changed
        if (clockAdapter != null) {
            clockAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDeleteButtonClick(ClockActivity clock) {
        // Remove the clock from the list
        clockList.remove(clock);

        // Notify the adapter that the data set has changed
        if (clockAdapter != null) {
            clockAdapter.notifyDataSetChanged();
        }
    }
}
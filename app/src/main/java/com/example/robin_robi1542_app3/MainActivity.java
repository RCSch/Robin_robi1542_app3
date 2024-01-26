package com.example.robin_robi1542_app3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClockListener {

    private RecyclerView recyclerView;
    private ClockAdapter clockAdapter;
    private List<ClockActivity> clockList;
    private Button btnNew;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnNew = findViewById(R.id.btnNew);

        // Initialize the clock list and adapter
        clockList = new ArrayList<>();
        clockAdapter = new ClockAdapter(clockList);

        // Set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(clockAdapter);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewClock();
            }
        });
    }

//    private void addNewClock() {
//        EditText editTextHours = findViewById(R.id.editTextHours);
//        EditText editTextMinutes = findViewById(R.id.editTextMinutes);
//        EditText editTextSeconds = findViewById(R.id.editTextSeconds);
//
//        try {
//            int hours = Integer.parseInt(editTextHours.getText().toString());
//            int minutes = Integer.parseInt(editTextMinutes.getText().toString());
//            int seconds = Integer.parseInt(editTextSeconds.getText().toString());
//
//            // Validate the entered values
//            if ( minutes > 59 || seconds > 59) {
//                Toast.makeText(this, "Minut- og sekundtal må ikke overskride 59", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            // Create a new ClockActivity instance and set the time components
//            ClockActivity newClock = new ClockActivity();
//            newClock.setHours(hours);
//            newClock.setMinutes(minutes);
//            newClock.setSeconds(seconds);
//
//            // Add the new clock to the list and notify the adapter
//            clockList.add(newClock);
//            clockAdapter.notifyDataSetChanged();
//        } catch (NumberFormatException e) {
//            // Handle invalid input (non-numeric values in EditText)
//            Toast.makeText(this, "Indsæt venligst tal mellem 0 og 59 for minutter og sekunder", Toast.LENGTH_SHORT).show();
//        }
//    }

    private void addNewClock() {
        // Create a new ClockActivity instance with default values
        ClockActivity newClock = new ClockActivity();

        // Add the new clock to the list and notify the adapter
        clockList.add(newClock);
        clockAdapter.notifyDataSetChanged();
    }
    @Override
    public void onTick(long millisUntilFinished) {
        // Update UI with the tick information
        // For example, you might want to update a TextView with the remaining time
    }

    @Override
    public void onFinish() {
        // Handle completion event
        // For example, update UI to indicate that the timer has finished
    }



}
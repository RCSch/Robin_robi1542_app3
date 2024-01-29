package com.example.robin_robi1542_app3;

import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.EditText;

public class ClockActivity extends CountDownTimer {
    private int hours; //Brugt i min egen hjemmebyggede version, der ikke kørte med millis.
    private int minutes;
    private int seconds;
    private boolean isRunning;
    private boolean isUpdatingUI = false;

    private Activity activity;
    private Handler handler;
    private EditText editTextHours, editTextMinutes, editTextSeconds;

    public ClockActivity(long millisInFuture, long countDownInterval, Activity activity, Handler handler
                     //, EditText editTextHours, EditText editTextMinutes, EditText editTextSeconds
    ) {
        super(millisInFuture, countDownInterval);
        this.activity = activity;
        this.handler = new Handler(Looper.getMainLooper());
//        this.editTextHours = editTextHours;
//        this.editTextMinutes = editTextMinutes;
//        this.editTextSeconds = editTextSeconds;
    }
    @Override
    public void onTick(long millisUntilFinished) {
        // Convert millisUntilFinished to hours, minutes, and seconds
        long totalSeconds = millisUntilFinished / 1000;
        int hours = (int) (totalSeconds / 3600);
        int minutes = (int) ((totalSeconds % 3600) / 60);
        int seconds = (int) (totalSeconds % 60);

        // Log the current values
        System.out.println("onTick: " + hours + "h " + minutes + "m " + seconds + "s"); //Ikke nødvendig i det endelige build

        // Update UI components (EditText fields) based on the timer values
        editTextHours.setText(String.format("%02d", hours));
        editTextMinutes.setText(String.format("%02d", minutes));
        editTextSeconds.setText(String.format("%02d", seconds));
    }


    public void updateUI() {
        if (!isUpdatingUI) {
            isUpdatingUI = true;

            new Thread(() -> {
                try {
                    while (isRunning && !Thread.interrupted()) {
                        // Use runOnUiThread to update UI from the main thread
                        activity.runOnUiThread(() -> updateTextViews());

                        // Sleep for one second
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    // Handle interruption if needed
                } finally {
                    isUpdatingUI = false;
                }
            }).start();
        }
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    private void updateTextViews() {
        // Update UI components (EditText fields) based on the clock state
        activity.runOnUiThread(() -> {
            editTextHours.setText(String.format("%02d", hours));
            editTextMinutes.setText(String.format("%02d", minutes));
            editTextSeconds.setText(String.format("%02d", seconds));
        });
    }
    public void startStop() {
        isRunning = !isRunning;

        if (isRunning) {
            // Start the countdown timer
            this.start();
        } else {
            // Stop the countdown timer
            this.cancel();
        }
    }

//    public void startStop() { //Simpel version, der ikke bruger millis
//        isRunning = !isRunning;
//
//        new Thread(() -> {
//            while (isRunning) {
//                if (seconds > 0) {
//                    seconds--;
//                } else if (seconds == 0 && minutes > 0) {
//                    seconds = 59;
//                    minutes--;
//                } else if (seconds == 0 && minutes == 0 && hours > 0) {
//                    seconds = 59;
//                    minutes = 59;
//                    hours--;
//                } else {
//                    isRunning = false;
//                }
//
//                // Update UI on the main thread
//                //runOnUiThread(() -> updateUI());
//                handler.post(() -> updateUI());
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
    @Override
    public void onFinish() {

    }
}

//if ( minutes > 59 || seconds > 59) {
//                Toast.makeText(this, "Minut- og sekundtal må ikke overskride 59", Toast.LENGTH_SHORT).show();
//                return;
//            }
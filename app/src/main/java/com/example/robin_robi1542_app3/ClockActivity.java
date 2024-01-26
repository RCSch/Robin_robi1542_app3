package com.example.robin_robi1542_app3;import android.os.CountDownTimer;
import android.widget.EditText;

public class ClockActivity {
    private int hours;
    private int minutes;
    private int seconds;
    private long totalTimeInMillis;
    private boolean isRunning;
    private CountDownTimer countDownTimer;

    //De tre metoder nedenfor har til formål at sikre, at timer, minutter og sekunder ikke er null.
    public void setHoursFromEditText(EditText editText) {
        String hoursText = editText.getText().toString();
        if (hoursText.isEmpty() || hoursText.equals("00")) {
            hours = 0;
        } else {
            hours = Integer.parseInt(hoursText);
        }
        totalTimeInMillis = calculateTotalTimeInMillis();
    }

    public void setMinutesFromEditText(EditText editText) {
        String minutesText = editText.getText().toString();
        if (minutesText.isEmpty() || minutesText.equals("00")) {
            minutes = 0;
        } else {
            minutes = Integer.parseInt(minutesText);
        }
        totalTimeInMillis = calculateTotalTimeInMillis();
    }

    public void setSecondsFromEditText(EditText editText) {
        String secondsText = editText.getText().toString();
        if (secondsText.isEmpty() || secondsText.equals("00")) {
            seconds = 0;
        } else {
            seconds = Integer.parseInt(secondsText);
        }
        totalTimeInMillis = calculateTotalTimeInMillis();
    }

    public ClockActivity() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.totalTimeInMillis = calculateTotalTimeInMillis();
        this.isRunning = false;
        this.countDownTimer = createCountDownTimer();
    }

    public void setHours(int hours) {
        this.hours = hours;
        this.totalTimeInMillis = calculateTotalTimeInMillis();
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
        this.totalTimeInMillis = calculateTotalTimeInMillis();
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
        this.totalTimeInMillis = calculateTotalTimeInMillis();
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

    public long getTotalTimeInMillis() {
        return totalTimeInMillis;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void startStop() {
        isRunning = !isRunning;

        if (isRunning) {
            startCountdown();
        } else {
            stopCountdown();
        }
    }

    private void startCountdown() {
        countDownTimer.start();
    }

    private void stopCountdown() {
        countDownTimer.cancel();
    }

    private CountDownTimer createCountDownTimer() {
        return new CountDownTimer(totalTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update UI or perform actions on tick
            }

            @Override
            public void onFinish() {
                // Handle countdown completion
                isRunning = false;
            }
        };
    }

    private long calculateTotalTimeInMillis() {
        return (seconds * 1000) + (minutes * 60000) + (hours * 3600000);
    }


    private ClockListener clockListener;

    public void setClockListener(ClockListener clockListener) {
        this.clockListener = clockListener;
    }

    private void notifyTick(long millisUntilFinished) {
        if (clockListener != null) {
            clockListener.onTick(millisUntilFinished);
        }
    }

    private void notifyFinish() {
        if (clockListener != null) {
            clockListener.onFinish();
        }
    }
}



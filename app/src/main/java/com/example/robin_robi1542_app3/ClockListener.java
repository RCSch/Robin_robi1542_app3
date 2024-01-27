package com.example.robin_robi1542_app3;

public interface ClockListener {
    void onDeleteButtonClick(ClockActivity clock);

    void onStartStopClick(ClockActivity clock);

    void onTick(long millisUntilFinished);

    void onFinish();
}

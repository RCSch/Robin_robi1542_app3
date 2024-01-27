package com.example.robin_robi1542_app3;

public interface ClockListener {
    void onTick(long millisUntilFinished);
    void onFinish();
    void onDeleteButtonClick(ClockActivity clock);
}

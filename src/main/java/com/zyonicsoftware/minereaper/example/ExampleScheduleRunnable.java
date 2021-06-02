package com.zyonicsoftware.minereaper.example;

import com.zyonicsoftware.minereaper.runnable.RedEugeneScheduleRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class ExampleScheduleRunnable extends RedEugeneScheduleRunnable {

    public ExampleScheduleRunnable(@NotNull final String eugeneJobName, final TimeUnit timeUnit, final long period) {
        super(eugeneJobName, timeUnit, period);
    }

    @Override
    public void run() {
        super.run(); // don't remove super.run(); this called the inject reference!
        this.callCodeBlock();
    }

    private void callCodeBlock() {
        System.out.println(this.getEugeneJob().toString());
    }
}

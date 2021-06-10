/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.example;

import com.zyonicsoftware.minereaper.runnable.RedEugeneScheduleFutureRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class ExampleScheduleFutureRunnable extends RedEugeneScheduleFutureRunnable {

    public ExampleScheduleFutureRunnable(@NotNull final String eugeneJobName, @NotNull final TimeUnit timeUnit, final long period) {
        super(eugeneJobName, timeUnit, period);
    }

    @Override
    public void run() {
        super.run(); // don't remove super.run(); this called the inject reference!
        this.callCodeBlock(); // checked the execution to interrupted your self ;)
    }

    private void callCodeBlock() {
        if (this.getEugeneJob().getExecute() == 50001) {
            if (ExampleMain.getRedEugeneIntroduction().forceCancelEugeneJob(this.getEugeneJob().getName())) {
                System.out.println("Job is finished");
            }
        } else {
            System.out.println(this.getEugeneJob().toString());
        }
    }


}

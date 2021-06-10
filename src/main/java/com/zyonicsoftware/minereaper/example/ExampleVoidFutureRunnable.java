/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.example;

import com.zyonicsoftware.minereaper.runnable.RedEugeneVoidFutureRunnable;
import org.jetbrains.annotations.NotNull;

public class ExampleVoidFutureRunnable extends RedEugeneVoidFutureRunnable {

    /**
     * @param eugeneJobName check, create and cache an new Job
     */
    public ExampleVoidFutureRunnable(@NotNull final String eugeneJobName) {
        super(eugeneJobName);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Hello World!");
    }
}

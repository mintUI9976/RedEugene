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

/**
 * @author Niklas Griese
 * @see com.zyonicsoftware.minereaper.runnable.RedEugeneVoidFutureRunnable
 */

public class ExampleVoidFutureRunnable extends RedEugeneVoidFutureRunnable {

    /**
     * @param eugeneJobName check, create and cache a new Job
     */
    public ExampleVoidFutureRunnable(@NotNull final String eugeneJobName) {
        super(eugeneJobName);
    }

    /**
     * don't remove super.run(); this calls the inject reference!
     * then you can call your own void execution
     */

    @Override
    public void run() {
        super.run();
        System.out.println("Hello World!"); // test execution
    }
}

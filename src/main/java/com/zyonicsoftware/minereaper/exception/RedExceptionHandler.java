/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.exception;

public class RedExceptionHandler implements Thread.UncaughtExceptionHandler {

    /**
     * @param thread    the thread the exception got thrown in
     * @param throwable thrown when the thread couldn't handle a exception
     */

    @Override
    public void uncaughtException(final Thread thread, final Throwable throwable) {
        System.out.println("Thread: " + thread.getName());
        System.out.println("ThreadState: " + thread.getState());
        System.out.println("ThreadID: " + thread.getId());
        System.out.println("ThreadGroup: " + thread.getThreadGroup());
        System.out.println("ThreadExceptionHandler: " + thread.getUncaughtExceptionHandler());
        System.out.println("Exception: " + throwable);
    }
}

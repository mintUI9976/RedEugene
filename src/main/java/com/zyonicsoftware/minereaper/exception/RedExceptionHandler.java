package com.zyonicsoftware.minereaper.exception;

public class RedExceptionHandler implements Thread.UncaughtExceptionHandler {

    /**
     * @param thread    sets the thread above the thread factory
     * @param throwable will be called when the thread the exception can not be handle
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

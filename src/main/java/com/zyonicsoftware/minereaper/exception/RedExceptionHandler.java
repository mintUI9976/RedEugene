package com.zyonicsoftware.minereaper.exception;

public class RedExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(final Thread thread, final Throwable throwable) {
        System.out.println("Exception: " + throwable);
        System.out.println("Thread: " + thread.getName());
        System.out.println("ThreadState: " + thread.getState());
        System.out.println("ThreadID: " + thread.getId());
        System.out.println("ThreadGroup: " + thread.getThreadGroup());
        System.out.println("ThreadExceptionHandler: " + thread.getUncaughtExceptionHandler());
    }
}

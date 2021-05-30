package com.zyonicsoftware.minereaper.threadfactory;

import com.zyonicsoftware.minereaper.exception.RedExceptionHandler;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;

public class EugeneFactory {

    private String factoryName = "RedEugeneFactory";
    private boolean daemon = false;
    private int priority = Thread.NORM_PRIORITY;

    public EugeneFactory setName(@NotNull final String factoryName) {
        this.factoryName = factoryName;
        return this;
    }

    public EugeneFactory setDaemon(final boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public EugeneFactory setPriority(final int priority) {
        this.priority = priority;
        return this;
    }

    public ThreadFactory build() {
        return runnable -> {
            final Thread thread = new Thread(runnable);
            thread.setDaemon(EugeneFactory.this.daemon);
            thread.setPriority(EugeneFactory.this.priority);
            thread.setName(EugeneFactory.this.factoryName);
            thread.setUncaughtExceptionHandler(new RedExceptionHandler());
            return thread;
        };
    }

}

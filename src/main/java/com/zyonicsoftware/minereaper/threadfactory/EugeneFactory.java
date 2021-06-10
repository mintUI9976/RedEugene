/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.threadfactory;

import com.zyonicsoftware.minereaper.exception.RedExceptionHandler;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Niklas Griese
 */

public class EugeneFactory {

    private String factoryName = "RedEugeneFactory";
    private boolean daemon = false;
    private int priority = Thread.NORM_PRIORITY;
    private final AtomicInteger count = new AtomicInteger(0);

    /**
     * @param factoryName sets the overall thread name
     * @return an builder instance
     */

    public EugeneFactory setName(@NotNull final String factoryName) {
        this.factoryName = factoryName;
        return this;
    }

    /**
     * @param daemon sets the overall thread daemon
     * @return an builder instance
     */

    public EugeneFactory setDaemon(final boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    /**
     * @param priority sets the overall thread priority
     * @return an builder instance
     */

    public EugeneFactory setPriority(final int priority) {
        this.priority = priority;
        return this;
    }

    /**
     * @return the custom build of the thread factory
     * creates a new thread and increments the thread count
     * add an custom UncaughtExceptionHandler with more details
     */

    public ThreadFactory build() {
        return runnable -> {
            final Thread thread = new Thread(runnable);
            this.count.getAndIncrement();
            thread.setDaemon(EugeneFactory.this.daemon);
            thread.setPriority(EugeneFactory.this.priority);
            thread.setName(EugeneFactory.this.factoryName + "-" + this.count.get());
            thread.setUncaughtExceptionHandler(new RedExceptionHandler());
            return thread;
        };
    }

    /**
     * @return your custom factory name
     */

    public String getFactoryName() {
        return this.factoryName;
    }

    /**
     * @return your selected daemon
     */

    public boolean isDaemon() {
        return this.daemon;
    }

    /**
     * @return your priority
     */

    public int getPriority() {
        return this.priority;
    }

    /**
     * @return the count of current threads
     */

    public int getCount() {
        return this.count.get();
    }
}

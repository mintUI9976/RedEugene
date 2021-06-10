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

public class EugeneFactory {

    private String factoryName = "RedEugeneFactory";
    private boolean daemon = false;
    private int priority = Thread.NORM_PRIORITY;
    private final AtomicInteger count = new AtomicInteger(0);

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
            this.count.getAndIncrement();
            thread.setDaemon(EugeneFactory.this.daemon);
            thread.setPriority(EugeneFactory.this.priority);
            thread.setName(EugeneFactory.this.factoryName + "-" + this.count.get());
            thread.setUncaughtExceptionHandler(new RedExceptionHandler());
            return thread;
        };
    }

    public String getFactoryName() {
        return this.factoryName;
    }

    public boolean isDaemon() {
        return this.daemon;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getCount() {
        return this.count.get();
    }
}

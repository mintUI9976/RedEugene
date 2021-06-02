package com.zyonicsoftware.minereaper.objects;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class EugeneJob {

    private final String name;
    private volatile long execute;
    private Thread thread;
    private final TimeUnit timeunit;
    private final long period;
    private final Runnable runnable;

    /**
     * @param name     sets the name of the created job
     * @param runnable sets the created runnable to cancel it later
     */

    public EugeneJob(@NotNull final String name, final TimeUnit timeunit, final long period, @NotNull final Runnable runnable) {
        this.name = name;
        this.timeunit = timeunit;
        this.period = period;
        this.runnable = runnable;
    }

    /**
     * @param thread will be set via runnable injection
     */

    public void setThread(@NotNull final Thread thread) {
        this.thread = thread;
    }

    /**
     * @return the current thread of the job
     */

    public Thread getThread() {
        return this.thread;
    }

    /**
     * @return the current runnable of the job
     */

    public Runnable getRunnable() {
        return this.runnable;
    }

    /**
     * @param execute will be set via runnable injection
     */

    public void setExecute(final long execute) {
        this.execute = execute;
    }

    /**
     * @return the name of the job
     */

    public String getName() {
        return this.name;
    }

    /**
     * @return the current executions of the job
     */

    public long getExecute() {
        return this.execute;
    }

    /**
     * @return your job TimeUnit e.g MILLISECONDS
     */

    public TimeUnit getTimeunit() {
        return this.timeunit;
    }

    /**
     * @return your job period as long
     */

    public long getPeriod() {
        return this.period;
    }

    @Override
    public String toString() {
        return "@EugeneJob" + " | Job:" + this.name + " | Count:" + this.execute + " | Thread:" + this.thread.getName() + " | Timeunit:" + this.timeunit + " | Period:" + this.period;
    }
}

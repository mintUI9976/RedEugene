/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.objects;

import com.zyonicsoftware.minereaper.enums.EugeneJobState;
import com.zyonicsoftware.minereaper.enums.EugeneRunnableState;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class EugeneJob {

    private final String name;
    private volatile long execute;
    private volatile String lastWorkThread;
    private final TimeUnit timeunit;
    private final long period;
    private EugeneJobState eugeneJobState;
    private final EugeneRunnableState eugeneRunnableState;
    private Future<?> future;


    public EugeneJob(@NotNull final String name, @NotNull final EugeneRunnableState eugeneRunnableState, final TimeUnit timeunit, final long period) {
        this.name = name;
        this.eugeneRunnableState = eugeneRunnableState;
        this.timeunit = timeunit;
        this.period = period;
    }

    /**
     * @param lastWorkThread will be set via runnable injection
     */

    public void setLastWorkThread(final String lastWorkThread) {
        this.lastWorkThread = lastWorkThread;
    }

    /**
     * @return the current thread of the job
     */

    public String getLastWorkThread() {
        return this.lastWorkThread;
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

    public EugeneJobState getEugeneJobState() {
        return this.eugeneJobState;
    }

    public void setEugeneJobState(final EugeneJobState eugeneJobState) {
        this.eugeneJobState = eugeneJobState;
    }

    public Future<?> getFuture() {
        return this.future;
    }

    public void setFuture(final Future<?> future) {
        this.future = future;
    }

    public EugeneRunnableState getEugeneRunnableState() {
        return this.eugeneRunnableState;
    }

    @Override
    public String toString() {
        return "@EugeneJob" + " | Job:" + this.name + " | State:" + this.eugeneJobState.name() + " | Count:" + this.execute + " | LastWorkThread:" + this.lastWorkThread + " | Timeunit:" + this.timeunit + " | Period:" + this.period;
    }
}

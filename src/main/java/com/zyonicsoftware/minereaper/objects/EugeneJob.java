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

/** @author Niklas Griese */
public class EugeneJob {

  private final String name;
  private volatile long execute;
  private volatile String lastWorkThread;
  private final TimeUnit timeunit;
  private final long period;
  private EugeneJobState eugeneJobState;
  private final EugeneRunnableState eugeneRunnableState;
  private Future<?> future;

  /**
   * @param name the name of current job
   * @param eugeneRunnableState the state of runnable
   * @param timeunit the timeunit the period is in
   * @param period the period time between runs
   */
  public EugeneJob(
      @NotNull final String name,
      @NotNull final EugeneRunnableState eugeneRunnableState,
      final TimeUnit timeunit,
      final long period) {
    this.name = name;
    this.eugeneRunnableState = eugeneRunnableState;
    this.timeunit = timeunit;
    this.period = period;
  }

  /** @param lastWorkThread will be set via runnable injection */
  public void setLastWorkThread(final String lastWorkThread) {
    this.lastWorkThread = lastWorkThread;
  }

  /** @return the current thread of the job as name */
  public String getLastWorkThread() {
    return this.lastWorkThread;
  }

  /** @param execute will be set via runnable injection */
  public void setExecute(final long execute) {
    this.execute = execute;
  }

  /** @return the name of the job */
  public String getName() {
    return this.name;
  }

  /** @return the current executions of the job */
  public long getExecute() {
    return this.execute;
  }

  /** @return your job TimeUnit e.g MILLISECONDS */
  public TimeUnit getTimeunit() {
    return this.timeunit;
  }

  /** @return your job period as long */
  public long getPeriod() {
    return this.period;
  }

  /** @return whether the job is currently OPEN or CLOSED */
  public EugeneJobState getEugeneJobState() {
    return this.eugeneJobState;
  }

  /**
   * @param eugeneJobState the jobState - OPEN when the job is getting created and CLOSE if it's
   *     cancelled
   */
  public void setEugeneJobState(final EugeneJobState eugeneJobState) {
    this.eugeneJobState = eugeneJobState;
  }

  /** @return the future to force cancel the task without interrupt the thread */
  public Future<?> getFuture() {
    return this.future;
  }

  /** @param future set future to cancel it at the end */
  public void setFuture(final Future<?> future) {
    this.future = future;
  }

  /** @return the job state, either SCHEDULE or EXECUTOR */
  public EugeneRunnableState getEugeneRunnableState() {
    return this.eugeneRunnableState;
  }

  /** @return the current pool of the job as name */
  public String getThreadWorkingPool() {
    return this.lastWorkThread.split("-")[0];
  }

  /** @return a custom toString method */
  @Override
  public String toString() {
    return "@EugeneJob"
        + " | Job:"
        + this.name
        + " | State:"
        + this.eugeneJobState.name()
        + " | Count:"
        + this.execute
        + " | LastWorkThread:"
        + this.lastWorkThread
        + " | Timeunit:"
        + this.timeunit
        + " | Period:"
        + this.period;
  }
}

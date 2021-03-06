/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.runnable;

import com.zyonicsoftware.minereaper.cache.RedEugeneCache;
import com.zyonicsoftware.minereaper.enums.EugeneJobState;
import com.zyonicsoftware.minereaper.enums.EugeneRunnableState;
import com.zyonicsoftware.minereaper.exception.EugeneException;
import com.zyonicsoftware.minereaper.objects.EugeneJob;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

/**
 * @author Niklas Griese
 * @see java.lang.Runnable
 */
public abstract class RedEugeneSchedulerRunnable implements Runnable {

  private EugeneJob eugeneJob;

  /**
   * @param eugeneJobName check, create and cache an new Job
   * @param timeUnit used your specified timeUnit
   * @param period used your specified period
   */
  public RedEugeneSchedulerRunnable(
      @NotNull final String eugeneJobName, @NotNull final TimeUnit timeUnit, final long period) {
    if (RedEugeneCache.getLive(eugeneJobName).isPresent()) {
      throw new EugeneException("The job is already exists.");
    }
    if (period <= 0) {
      throw new EugeneException("The period value must not be less than or equal to 0");
    }
    this.eugeneJob = new EugeneJob(eugeneJobName, EugeneRunnableState.SCHEDULE, timeUnit, period);
    this.eugeneJob.setEugeneJobState(EugeneJobState.OPEN);
    RedEugeneCache.put(eugeneJobName, this.eugeneJob);
  }

  /**
   * inject in the default interface of an runnable -> the current thread of this job and their
   * execution count the default reference will be set to null after you canceled the job
   */
  @Override
  public void run() {
    if (this.eugeneJob.getEugeneJobState().equals(EugeneJobState.OPEN)) {
      this.eugeneJob.setExecute(this.eugeneJob.getExecute() + 1);
      this.eugeneJob.setLastWorkThread(Thread.currentThread().getName());
    } else {
      this.eugeneJob = null;
    }
  }

  /** @return the current job of this called reference */
  public EugeneJob getEugeneJob() {
    return this.eugeneJob;
  }
}

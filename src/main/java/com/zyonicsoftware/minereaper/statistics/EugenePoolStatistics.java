/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.statistics;

import com.zyonicsoftware.minereaper.redeugene.RedEugene;

/** @author Niklas Griese */
public class EugenePoolStatistics {

  private final RedEugene redEugene;

  /** @param redEugene will be passed the main instance */
  public EugenePoolStatistics(final RedEugene redEugene) {
    this.redEugene = redEugene;
  }

  /** @return the core size of the pool */
  public int corePoolSize() {
    return this.redEugene.getRedThreadPool().getCorePoolSize();
  }

  /** @return the pure size of the pool */
  public int poolSize() {
    return this.redEugene.getRedThreadPool().getPoolSize();
  }

  /** @return the largest size of the pool */
  public int largestPoolSize() {
    return this.redEugene.getRedThreadPool().getLargestPoolSize();
  }

  /** @return the max size of the pool */
  public int maxPoolSize() {
    return this.redEugene.getRedThreadPool().getMaximumPoolSize();
  }

  /** @return the active count of threads of the pool */
  public int activeCount() {
    return this.redEugene.getRedThreadPool().getActiveCount();
  }

  /** @return the overall completed task counts of the pool */
  public long completedTaskCount() {
    return this.redEugene.getRedThreadPool().getCompletedTaskCount();
  }

  /** @return all task counts with schedule counts */
  public long taskCount() {
    return this.redEugene.getRedThreadPool().getTaskCount();
  }
}

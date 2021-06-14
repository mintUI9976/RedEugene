/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.pool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * @author Niklas Griese
 * @see java.util.concurrent.ScheduledExecutorService
 * @see java.util.concurrent.ScheduledThreadPoolExecutor
 * @see java.util.concurrent.ThreadPoolExecutor
 */
public class RedThreadPool extends ScheduledThreadPoolExecutor {

  /**
   * @param corePoolSize the size of the pool
   * @param threadFactory the custom thread factory create a custom reference of the original
   */
  public RedThreadPool(final int corePoolSize, final ThreadFactory threadFactory) {
    super(corePoolSize, threadFactory);
  }
}

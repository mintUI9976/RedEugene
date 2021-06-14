/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.redeugene;

import com.zyonicsoftware.minereaper.enums.EugeneFactoryPriority;
import com.zyonicsoftware.minereaper.pool.RedThreadPool;
import com.zyonicsoftware.minereaper.threadfactory.EugeneFactory;
import org.jetbrains.annotations.NotNull;

/** @author Niklas Griese */
public class RedEugene {

  private RedThreadPool redThreadPool;
  private final int coreSize;

  /**
   * @param eugeneFactoryName used to create a new thread with your custom name
   * @param coreSize used to specify the size of the thread pool
   * @param daemon read the wiki for more information ->
   *     "https://gitlab.zyonicsoftware.com/mint9976/redeugene/-/wikis/What-is-an-Daemon-thread-in-Java%3F"
   * @param eugeneFactoryPriority read the wiki for more information ->
   *     "https://gitlab.zyonicsoftware.com/mint9976/redeugene/-/wikis/What-is-thread-priority-in-Java%3F"
   */
  public RedEugene(
      @NotNull final String eugeneFactoryName,
      final int coreSize,
      final boolean daemon,
      @NotNull final EugeneFactoryPriority eugeneFactoryPriority) {
    this.coreSize = coreSize;
    switch (eugeneFactoryPriority) {
      case MIN:
        this.redThreadPool =
            new RedThreadPool(
                this.coreSize,
                new EugeneFactory()
                    .setPriority(Thread.MIN_PRIORITY)
                    .setDaemon(daemon)
                    .setName(eugeneFactoryName)
                    .build());
        break;
      case NORM:
        this.redThreadPool =
            new RedThreadPool(
                this.coreSize,
                new EugeneFactory()
                    .setPriority(Thread.NORM_PRIORITY)
                    .setDaemon(daemon)
                    .setName(eugeneFactoryName)
                    .build());
        break;
      case MAX:
        this.redThreadPool =
            new RedThreadPool(
                this.coreSize,
                new EugeneFactory()
                    .setPriority(Thread.MAX_PRIORITY)
                    .setDaemon(daemon)
                    .setName(eugeneFactoryName)
                    .build());
        break;
    }
    this.redThreadPool.setRemoveOnCancelPolicy(true);
  }

  /** @return all information about the thread pool */
  public RedThreadPool getRedThreadPool() {
    return this.redThreadPool;
  }

  /** @return your size set */
  public int getCoreSize() {
    return this.coreSize;
  }
}

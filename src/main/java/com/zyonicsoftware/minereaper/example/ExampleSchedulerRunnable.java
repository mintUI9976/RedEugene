/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.example;

import com.zyonicsoftware.minereaper.runnable.RedEugeneSchedulerRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

/**
 * @author Niklas Griese
 * @see RedEugeneSchedulerRunnable
 */
public class ExampleSchedulerRunnable extends RedEugeneSchedulerRunnable {

  public ExampleSchedulerRunnable(
      @NotNull final String eugeneJobName, @NotNull final TimeUnit timeUnit, final long period) {
    super(eugeneJobName, timeUnit, period);
  }

  /**
   * don't remove super.run(); this calls the inject reference! then you can call your own void
   * scheduler
   */
  @Override
  public void run() {
    super.run();
    this.callCodeBlock(); // checked the execution count to cancel your self ;)
  }

  private void callCodeBlock() {
    if (this.getEugeneJob().getExecute() == 50001) {
      if (ExampleMain.getRedEugeneIntroduction()
          .forceCancelEugeneJob(this.getEugeneJob().getName())) {
        System.out.println("Job is finished");
      }
    } else {
      System.out.println(this.getEugeneJob());
    }
  }
}

/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.scheduler;

import com.zyonicsoftware.minereaper.cache.RedEugeneCache;
import com.zyonicsoftware.minereaper.enums.EugeneJobState;
import com.zyonicsoftware.minereaper.objects.EugeneJob;
import com.zyonicsoftware.minereaper.redeugene.RedEugene;
import com.zyonicsoftware.minereaper.runnable.RedEugeneScheduleFutureRunnable;
import com.zyonicsoftware.minereaper.runnable.RedEugeneVoidFutureRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Niklas Griese
 */

public class RedEugeneIntroduction {

    private final RedEugene redEugene;

    public RedEugeneIntroduction(final RedEugene redEugene) {
        this.redEugene = redEugene;
    }

    /**
     * @param redEugeneScheduleFutureRunnable used to run the runnable and grab your provided schedule preferences
     */

    public void scheduleWithoutDelay(@NotNull final RedEugeneScheduleFutureRunnable redEugeneScheduleFutureRunnable) {
        final ScheduledFuture<?> scheduledFuture = this.redEugene.getRedThreadPool().scheduleAtFixedRate(redEugeneScheduleFutureRunnable, 0, redEugeneScheduleFutureRunnable.getEugeneJob().getPeriod(), redEugeneScheduleFutureRunnable.getEugeneJob().getTimeunit());
        redEugeneScheduleFutureRunnable.getEugeneJob().setFuture(scheduledFuture);
    }

    /**
     * @param redEugeneScheduleFutureRunnable used to run the runnable and grab your provided schedule preferences
     * @param delay                           sets the first timeout before the scheduler uses the period as the timeout
     */

    public void scheduleWithDelay(@NotNull final RedEugeneScheduleFutureRunnable redEugeneScheduleFutureRunnable, final long delay) {
        final ScheduledFuture<?> scheduledFuture = this.redEugene.getRedThreadPool().scheduleAtFixedRate(redEugeneScheduleFutureRunnable, delay, redEugeneScheduleFutureRunnable.getEugeneJob().getPeriod(), redEugeneScheduleFutureRunnable.getEugeneJob().getTimeunit());
        redEugeneScheduleFutureRunnable.getEugeneJob().setFuture(scheduledFuture);
    }

    /**
     * @param redEugeneVoidFutureRunnable used to run the runnable and grab your provided executor preferences
     * @return a future to work with the result
     */

    public Future<?> submitWithNullResult(@NotNull final RedEugeneVoidFutureRunnable redEugeneVoidFutureRunnable) {
        final Future<?> future = this.redEugene.getRedThreadPool().submit(redEugeneVoidFutureRunnable);
        redEugeneVoidFutureRunnable.getEugeneJob().setFuture(future);
        return future;
    }

    /**
     * @param redEugeneVoidFutureRunnable used to run the runnable and grab your provided executor preferences
     * @param result                      the object to get returned after a successful run
     * @return a future to work with the result
     */

    public Future<?> submitWithObjectResult(@NotNull final RedEugeneVoidFutureRunnable redEugeneVoidFutureRunnable, @NotNull final Object result) {
        final Future<?> future = this.redEugene.getRedThreadPool().submit(redEugeneVoidFutureRunnable, result);
        redEugeneVoidFutureRunnable.getEugeneJob().setFuture(future);
        return future;
    }

    /**
     * @param redEugeneVoidFutureRunnable uses to run the runnable and grab your provided executor preferences
     * @return a future to work with the result
     */

    public CompletableFuture<Void> voidCompletableFuture(@NotNull final RedEugeneVoidFutureRunnable redEugeneVoidFutureRunnable) {
        final CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(redEugeneVoidFutureRunnable, this.redEugene.getRedThreadPool());
        redEugeneVoidFutureRunnable.getEugeneJob().setFuture(completableFuture);
        return completableFuture;
    }

    /**
     * @param timeout the timeout to gracefully shutdown the thread pool
     * @throws InterruptedException thrown when the shutdown failed
     */

    public void shutdownPool(final long timeout) throws InterruptedException {
        //Cancel scheduled but not started task, and avoid new ones
        this.redEugene.getRedThreadPool().shutdown();
        //Wait for the running tasks
        if (this.redEugene.getRedThreadPool().awaitTermination(timeout, TimeUnit.SECONDS)) {
            //Interrupt the threads and shutdown the scheduler
            this.redEugene.getRedThreadPool().shutdownNow();
        }
    }

    /**
     * @param eugeneJobName Cancels and purges all scheduled tasks and the executor
     * @return if it worked
     */

    public boolean forceCancelEugeneJob(@NotNull final String eugeneJobName) {
        if (RedEugeneCache.getLive(eugeneJobName).isEmpty()) {
            return false;
        }
        final Optional<EugeneJob> optionalEugeneJob = RedEugeneCache.getLive(eugeneJobName);
        if (optionalEugeneJob.isPresent()) {
            final EugeneJob eugeneJob = optionalEugeneJob.get();
            switch (eugeneJob.getEugeneRunnableState()) {
                case SCHEDULE:
                    if (eugeneJob.getFuture().cancel(false)) {
                        this.clearDeadTasks();
                        eugeneJob.setEugeneJobState(EugeneJobState.CLOSED);
                        RedEugeneCache.remove(eugeneJobName);
                        return true;
                    } else {
                        return false;
                    }
                case EXECUTOR:
                    this.clearDeadTasks();
                    eugeneJob.setEugeneJobState(EugeneJobState.CLOSED);
                    RedEugeneCache.remove(eugeneJobName);
                    return true;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    /**
     * use it when the task really is done
     */

    public void clearDeadTasks() {
        this.redEugene.getRedThreadPool().purge();
    }
}

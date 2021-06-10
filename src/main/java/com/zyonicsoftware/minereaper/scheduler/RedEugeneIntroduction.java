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

public class RedEugeneIntroduction {

    private final RedEugene redEugene;

    public RedEugeneIntroduction(final RedEugene redEugene) {
        this.redEugene = redEugene;
    }

    /**
     * @param redEugeneScheduleFutureRunnable will be use to run the runnable and to grab your provided schedule preferences
     */

    public void scheduleWithoutDelay(@NotNull final RedEugeneScheduleFutureRunnable redEugeneScheduleFutureRunnable) {
        final ScheduledFuture<?> scheduledFuture = this.redEugene.getRedThreadPool().scheduleAtFixedRate(redEugeneScheduleFutureRunnable, 0, redEugeneScheduleFutureRunnable.getEugeneJob().getPeriod(), redEugeneScheduleFutureRunnable.getEugeneJob().getTimeunit());
        redEugeneScheduleFutureRunnable.getEugeneJob().setFuture(scheduledFuture);
    }

    /**
     * @param redEugeneScheduleFutureRunnable will be use to run the runnable and to grab your provided schedule preferences
     * @param delay                           set the first timeout before the schedule use the period as timeout
     */

    public void scheduleWithDelay(@NotNull final RedEugeneScheduleFutureRunnable redEugeneScheduleFutureRunnable, final long delay) {
        final ScheduledFuture<?> scheduledFuture = this.redEugene.getRedThreadPool().scheduleAtFixedRate(redEugeneScheduleFutureRunnable, delay, redEugeneScheduleFutureRunnable.getEugeneJob().getPeriod(), redEugeneScheduleFutureRunnable.getEugeneJob().getTimeunit());
        redEugeneScheduleFutureRunnable.getEugeneJob().setFuture(scheduledFuture);
    }

    /**
     * @param redEugeneVoidFutureRunnable will be use to run the runnable and to grab your provided executor preferences
     * @return an future to work with the result
     */

    public Future<?> submitWithNullResult(@NotNull final RedEugeneVoidFutureRunnable redEugeneVoidFutureRunnable) {
        final Future<?> future = this.redEugene.getRedThreadPool().submit(redEugeneVoidFutureRunnable);
        redEugeneVoidFutureRunnable.getEugeneJob().setFuture(future);
        return future;
    }

    /**
     * @param redEugeneVoidFutureRunnable will be use to run the runnable and to grab your provided executor preferences
     * @param result                      is an object that will be returned after the future is successfully run
     * @return an future to work with the result
     */

    public Future<?> submitWithObjectResult(@NotNull final RedEugeneVoidFutureRunnable redEugeneVoidFutureRunnable, @NotNull final Object result) {
        final Future<?> future = this.redEugene.getRedThreadPool().submit(redEugeneVoidFutureRunnable, result);
        redEugeneVoidFutureRunnable.getEugeneJob().setFuture(future);
        return future;
    }

    /**
     * @param redEugeneVoidFutureRunnable will be use to run the runnable and to grab your provided executor preferences
     * @return an future to work with the result
     */

    public CompletableFuture<Void> voidCompletableFuture(@NotNull final RedEugeneVoidFutureRunnable redEugeneVoidFutureRunnable) {
        final CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(redEugeneVoidFutureRunnable, this.redEugene.getRedThreadPool());
        redEugeneVoidFutureRunnable.getEugeneJob().setFuture(completableFuture);
        return completableFuture;
    }

    /**
     * @param timeout will be use to make an gracefully shutdown for thread pool
     * @throws InterruptedException will be use after the await termination failed
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
     * @param eugeneJobName will be use to cancel running schedules/purge and will be purge finished void executor
     * @return an boolean to get the finished state
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
     * use it when the task really done
     */

    public void clearDeadTasks() {
        this.redEugene.getRedThreadPool().purge();
    }
}

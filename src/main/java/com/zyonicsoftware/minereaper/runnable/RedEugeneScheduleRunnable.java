package com.zyonicsoftware.minereaper.runnable;

import com.zyonicsoftware.minereaper.cache.RedEugeneCache;
import com.zyonicsoftware.minereaper.exception.EugeneException;
import com.zyonicsoftware.minereaper.objects.EugeneJob;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public abstract class RedEugeneScheduleRunnable implements Runnable {

    private final EugeneJob eugeneJob;

    /**
     * @param eugeneJobName check, create and cache an new Job
     */
    public RedEugeneScheduleRunnable(@NotNull final String eugeneJobName, final TimeUnit timeUnit, final long period) {
        if (RedEugeneCache.getLive(eugeneJobName).isPresent()) {
            throw new EugeneException("The job is already present.");
        }
        this.eugeneJob = new EugeneJob(eugeneJobName, timeUnit, period, this);
        RedEugeneCache.put(eugeneJobName, this.eugeneJob);
    }

    /**
     * inject in the default interface of the runnable -> the current thread of this job and their execution amount
     */
    @Override
    public void run() {
        this.eugeneJob.setExecute(this.eugeneJob.getExecute() + 1);
        this.eugeneJob.setThread(Thread.currentThread());
    }

    /**
     * @return the current job of this called interface
     */

    public EugeneJob getEugeneJob() {
        return this.eugeneJob;
    }
}

package com.zyonicsoftware.minereaper.scheduler;

import com.zyonicsoftware.minereaper.redeugene.RedEugene;
import com.zyonicsoftware.minereaper.runnable.RedEugeneScheduleRunnable;
import org.jetbrains.annotations.NotNull;

public class EugeneScheduler {

    private final RedEugene redEugene;

    public EugeneScheduler(final RedEugene redEugene) {
        this.redEugene = redEugene;
    }

    public void scheduleWithoutDelay(@NotNull final RedEugeneScheduleRunnable redEugeneScheduleRunnable) {
        this.redEugene.getRedThreadPool().scheduleAtFixedRate(redEugeneScheduleRunnable, 0, redEugeneScheduleRunnable.getEugeneJob().getPeriod(), redEugeneScheduleRunnable.getEugeneJob().getTimeunit());
    }

    public void scheduleWithDelay(@NotNull final RedEugeneScheduleRunnable redEugeneScheduleRunnable, final long delay) {
        this.redEugene.getRedThreadPool().scheduleAtFixedRate(redEugeneScheduleRunnable, delay, redEugeneScheduleRunnable.getEugeneJob().getPeriod(), redEugeneScheduleRunnable.getEugeneJob().getTimeunit());
    }

    public void killEugeneJob(@NotNull final String eugeneJobName) {
        
    }

    public RedEugene getRedEugene() {
        return this.redEugene;
    }
}

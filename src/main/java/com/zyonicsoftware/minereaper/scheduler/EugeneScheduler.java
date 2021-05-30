package com.zyonicsoftware.minereaper.scheduler;

import com.zyonicsoftware.minereaper.redeugene.RedEugene;

public class EugeneScheduler {

    private final RedEugene redEugene;

    public EugeneScheduler(final RedEugene redEugene) {
        this.redEugene = redEugene;
    }

    /*public void schedule(@NotNull final Runnable runnable, @NotNull final Duration duration) {
        this.redEugene.getRedThread().schedule(runnable, 0, TimeUnit.MILLISECONDS);
    }*/

    public RedEugene getRedEugene() {
        return this.redEugene;
    }
}

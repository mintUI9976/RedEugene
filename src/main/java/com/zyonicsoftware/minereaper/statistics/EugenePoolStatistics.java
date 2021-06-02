package com.zyonicsoftware.minereaper.statistics;

import com.zyonicsoftware.minereaper.redeugene.RedEugene;

import java.util.concurrent.ThreadFactory;

public class EugenePoolStatistics {

    private final RedEugene redEugene;

    public EugenePoolStatistics(final RedEugene redEugene) {
        this.redEugene = redEugene;
    }

    public int corePoolSize() {
        return this.redEugene.getRedThreadPool().getCorePoolSize();
    }

    public int poolSize() {
        return this.redEugene.getRedThreadPool().getPoolSize();
    }

    public int largestPoolSize() {
        return this.redEugene.getRedThreadPool().getLargestPoolSize();
    }

    public int maxPoolSize() {
        return this.redEugene.getRedThreadPool().getMaximumPoolSize();
    }

    public int activeCount() {
        return this.redEugene.getRedThreadPool().getActiveCount();
    }

    public long completedTaskCount() {
        return this.redEugene.getRedThreadPool().getCompletedTaskCount();
    }

    public long taskCount() {
        return this.redEugene.getRedThreadPool().getTaskCount();
    }

    public ThreadFactory currentThreadFactory() {
        return this.redEugene.getRedThreadPool().getThreadFactory();
    }

}

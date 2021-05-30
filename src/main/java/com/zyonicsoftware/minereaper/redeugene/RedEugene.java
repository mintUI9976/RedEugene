package com.zyonicsoftware.minereaper.redeugene;

import com.zyonicsoftware.minereaper.enums.EugeneFactoryPriority;
import com.zyonicsoftware.minereaper.thread.RedThread;
import com.zyonicsoftware.minereaper.threadfactory.EugeneFactory;
import org.jetbrains.annotations.NotNull;

public class RedEugene {

    private RedThread redThread;
    private final int coreSize;

    public RedEugene(@NotNull final String eugeneFactoryName, final int coreSize, final boolean daemon, @NotNull final EugeneFactoryPriority eugeneFactoryPriority) {
        this.coreSize = coreSize;
        switch (eugeneFactoryPriority) {
            case MIN:
                this.redThread = new RedThread(this.coreSize, new EugeneFactory().setPriority(Thread.MIN_PRIORITY).setDaemon(daemon).setName(eugeneFactoryName).build());
                break;
            case NORM:
                this.redThread = new RedThread(this.coreSize, new EugeneFactory().setPriority(Thread.NORM_PRIORITY).setDaemon(daemon).setName(eugeneFactoryName).build());
                break;
            case MAX:
                this.redThread = new RedThread(this.coreSize, new EugeneFactory().setPriority(Thread.MAX_PRIORITY).setDaemon(daemon).setName(eugeneFactoryName).build());
                break;
        }
    }

    public RedThread getRedThread() {
        return this.redThread;
    }

    public int getCoreSize() {
        return this.coreSize;
    }
}

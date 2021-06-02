package com.zyonicsoftware.minereaper.pool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

public class RedThreadPool extends ScheduledThreadPoolExecutor {


    public RedThreadPool(final int corePoolSize, final ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

}

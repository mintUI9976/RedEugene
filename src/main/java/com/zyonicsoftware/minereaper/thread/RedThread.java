package com.zyonicsoftware.minereaper.thread;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

public class RedThread extends ScheduledThreadPoolExecutor {


    public RedThread(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

}

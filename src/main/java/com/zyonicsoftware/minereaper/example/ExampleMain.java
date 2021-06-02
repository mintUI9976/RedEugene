package com.zyonicsoftware.minereaper.example;

import com.zyonicsoftware.minereaper.enums.EugeneFactoryPriority;
import com.zyonicsoftware.minereaper.redeugene.RedEugene;
import com.zyonicsoftware.minereaper.scheduler.EugeneScheduler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ExampleMain {

    private static final RedEugene redEugene = new RedEugene("ExampleWorker", 10, true, EugeneFactoryPriority.MAX);
    private static EugeneScheduler eugeneScheduler;

    public static void main(final String[] args) throws InterruptedException {
        ExampleMain.eugeneScheduler = new EugeneScheduler(ExampleMain.redEugene);
        ExampleMain.testSchedule();
        Thread.sleep(20000);
    }

    public static void testSchedule() {
        ExampleMain.eugeneScheduler.scheduleWithDelay(new ExampleScheduleRunnable("TestSchedule-" + 1, TimeUnit.MILLISECONDS, 1), 1000);
    }

    public static CompletableFuture<Void> testFuture() {
        return CompletableFuture.runAsync(() -> {

        }, ExampleMain.redEugene.getRedThreadPool());
    }
}

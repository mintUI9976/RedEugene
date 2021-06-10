/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.example;

import com.zyonicsoftware.minereaper.enums.EugeneFactoryPriority;
import com.zyonicsoftware.minereaper.redeugene.RedEugene;
import com.zyonicsoftware.minereaper.scheduler.RedEugeneIntroduction;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ExampleMain {

    /**
     * initialize our thread pool and thread factory
     */
    private static final RedEugene redEugene = new RedEugene("ExampleWorker", 100, true, EugeneFactoryPriority.MAX);
    /**
     * create the caller instance to interact with our methods
     */
    private static final RedEugeneIntroduction redEugeneIntroduction = new RedEugeneIntroduction(ExampleMain.redEugene);

    /**
     * All Void return futures will be protocolized from RedEugene
     * IMPORTANT -> Supplier / Callable won't be protocolized
     * But you have the possibility to pass the executor.
     * The job name is the key to all information
     */

    public static void completableFutureVoidExample() {
        final String jobName = "ExampleVoidCompletableFuture";
        final ExampleVoidFutureRunnable exampleVoidFutureRunnable = new ExampleVoidFutureRunnable(jobName);
        ExampleMain.redEugeneIntroduction.voidCompletableFuture(exampleVoidFutureRunnable).thenAccept(unused -> {
            System.out.println("Job: " + jobName + " is finished.");
            if (ExampleMain.redEugeneIntroduction.forceCancelEugeneJob(jobName)) {
                System.out.println("Job: " + jobName + " is canceled and purged!");
            }
        });
    }

    /**
     * The submit must be null to get her result of the future if her successfully called
     * The job name is the key to all information
     *
     * @throws ExecutionException   will be called if "get"(future) threw an exception
     * @throws InterruptedException thread(task) will threw an exception
     */

    public static void submitWithNullResultExample() throws ExecutionException, InterruptedException {
        final String jobName = "ExampleVoidNullResultSubmit";
        final ExampleVoidFutureRunnable exampleVoidFutureRunnable = new ExampleVoidFutureRunnable(jobName);
        if (ExampleMain.redEugeneIntroduction.submitWithNullResult(exampleVoidFutureRunnable).get() == null) {
            System.out.println("Job: " + jobName + " is finished.");
            if (ExampleMain.redEugeneIntroduction.forceCancelEugeneJob(jobName)) {
                System.out.println("Job: " + jobName + " is canceled and purged!");
            }
        }
    }

    /**
     * @param result The result object is used to pass the future and if the future has finished successfully,
     *               your future should have been called and your object should not be null.
     * @throws ExecutionException   will be called if "get"(future) threw an exception
     * @throws InterruptedException thread(task) will threw an exception
     */

    public static void submitWithObjectResult(final Object result) throws ExecutionException, InterruptedException {
        final String jobName = "ExampleVoidObjectResultSubmit";
        final ExampleVoidFutureRunnable exampleVoidFutureRunnable = new ExampleVoidFutureRunnable(jobName);
        if (ExampleMain.redEugeneIntroduction.submitWithObjectResult(exampleVoidFutureRunnable, result).get() == result) {
            System.out.println("Job: " + jobName + " is finished.");
            if (ExampleMain.redEugeneIntroduction.forceCancelEugeneJob(jobName)) {
                System.out.println("Job: " + jobName + " is canceled and purged!");
            }
        }
    }

    /**
     * the supplier will be called and if the future accepts you can work with your result and clear the dead task of the redEugene thread pool
     */

    public static void resultOfSupplierExample() {
        ExampleMain.completableFutureSupplierExample().thenAccept(result -> {
            System.out.println(result);
            ExampleMain.redEugeneIntroduction.clearDeadTasks();
        });
    }

    /**
     * @return a supplier will be returned on the thread pool of redEugene
     */

    public static CompletableFuture<String> completableFutureSupplierExample() {
        return CompletableFuture.supplyAsync(() -> "Hello World!", ExampleMain.getRedEugene().getRedThreadPool());
    }

    /**
     * The runnable will run as long as you want it to run
     * The job name is the key to all information
     */

    public static void scheduleWithoutDelayFutureExample() {
        final String jobName = "ExampleSchedule";
        ExampleMain.redEugeneIntroduction.scheduleWithoutDelay(new ExampleScheduleFutureRunnable(jobName, TimeUnit.NANOSECONDS, 1));
    }

    public static RedEugene getRedEugene() {
        return ExampleMain.redEugene;
    }

    public static RedEugeneIntroduction getRedEugeneIntroduction() {
        return ExampleMain.redEugeneIntroduction;
    }
}

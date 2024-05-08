package com.miguelvela;

import java.sql.Time;
import java.util.concurrent.*;

public class ScheduledPoolExample {

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(50);

    public static void main(String[] args) {

        // Scheduled future
        Future<Double> result = executorService.schedule(() -> {
            try {
                Thread.sleep((int) Math.random() * 200);
                System.out.println("Scheduled task: " + Thread.currentThread().threadId());
                return Math.random();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 500, TimeUnit.MILLISECONDS);


        // Print result
        try {
            System.out.println("Result: " + result.get(100, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            e.printStackTrace();
            result.cancel(true);
        }

        if (result.isCancelled()) {
            System.out.println("The future was cancelled");
        }

        if (result.isDone()) {
            System.out.println("The future is done, no longer waiting to complete");
        }

        ///////////////

        executorService.scheduleWithFixedDelay(
                () -> {
                    System.out.println("Delayed, thread id: " + Thread.currentThread().threadId());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                },
                1000,
                0,
                TimeUnit.MILLISECONDS);
        try {
            executorService.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
    }
}

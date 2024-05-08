package com.miguelvela;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleSubmitExample {
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

        Future<Double> future1 = getRandom(1);
        Future<Double> future2 = getRandom(2);
        try {
            System.out.println(future1.get());
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        Future<Double> future3 = getRandom(3);
        while(!future3.isDone()) {
            if(future3.isDone()) {
                System.out.println("The future was cancelled");
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            System.out.println(future3.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
    }

    public static Future<Double> getRandom(int i) {
        return executorService.submit(() -> {
            System.out.println(i);
            return Math.random();
        });
    }
}

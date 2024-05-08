package com.miguelvela;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {

    public static void main(String[] args) {

        // singleThreadExecutor();
        executorServiceFixedPool();
    }

    private static void executorServiceFixedPool() {
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            List<Callable<Integer>> callables = new ArrayList<>();
            callables.add(() -> 1);
            callables.add(() -> 2);
            callables.add(() -> 3);
            callables.add(() -> 4);

            try {
                Thread.sleep(100);
                //System.out.println(executorService.invokeAny(callables));
                System.out.println(executorService.invokeAll(callables));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            executorService.shutdown();
        }
    }

    private static void singleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> System.out.println("1: " + Math.random() + " - thread id: " + Thread.currentThread().threadId()));
        executorService.execute(() -> System.out.println("2: " + Math.random() + " - thread id: " + Thread.currentThread().threadId()));
        executorService.execute(() -> System.out.println("3: " + Math.random() + " - thread id: " + Thread.currentThread().threadId()));
        executorService.execute(() -> System.out.println("4: " + Math.random() + " - thread id: " + Thread.currentThread().threadId()));

        executorService.shutdown();
    }
}
package com.miguelvela;

public class Main {
    public static void main(String[] args) {

//        Runnable task = () -> System.out.println("Hello from a task!");
//        Thread thread1 = new Thread(task);
//        thread1.start();
//
//        Thread thread2 = new Thread(() -> System.out.println("Thread id: " + Thread.currentThread().threadId()));
//        thread2.start();
//
//        System.out.println("Main: " + Thread.currentThread().threadId());
//
//        try {
//            int seconds = 3;
//            int sleepTime = seconds * 1000;
//            System.out.println("Sleeping main thread for: " + seconds + " seconds");
//            Thread.sleep(sleepTime);
//            CustomThread myCustomThread = new CustomThread();
//            myCustomThread.start();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        Thread runnableThread = new Thread(new CustomRunnable());
        runnableThread.start();

        Thread thread2 = new Thread(() -> System.out.println("Another thread"));
        thread2.start();

        CallableThread callable = new CallableThread();
        try {
            String result =  callable.call();
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
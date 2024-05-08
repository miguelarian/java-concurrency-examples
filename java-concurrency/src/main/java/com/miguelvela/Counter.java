package com.miguelvela;

public class Counter {

    public static int counterSync = 0;
    public static int counterLock = 0;
    public static Object lock = new Object();

    public synchronized static void incrementCounterSynchornized() {
        int current = counterSync;
        System.out.println("Before: " + counterSync + " - thread Id: " + Thread.currentThread().threadId());
        counterSync = current + 1;
        System.out.println("After: " + counterSync + " - thread Id: " + Thread.currentThread().threadId());
    }

    public static void incrementCounterLock() {
        synchronized (lock) {
            int current = counterLock;
            System.out.println("Before: " + counterLock + " - thread Id: " + Thread.currentThread().threadId());
            counterLock = current + 1;
            System.out.println("After: " + counterLock + " - thread Id: " + Thread.currentThread().threadId());
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            Thread thread = new Thread(Counter::incrementCounterSynchornized);
            thread.start();
        }

        for (int i=0; i<10; i++) {
            Thread thread = new Thread(Counter::incrementCounterLock);
            thread.start();
        }
    }
}

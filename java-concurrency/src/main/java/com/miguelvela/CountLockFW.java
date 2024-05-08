package com.miguelvela;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountLockFW {
    public static int counter = 0;
    public static Lock lock = new ReentrantLock();

    public static void incrementCounter() {
        try {
            if (lock.tryLock(500, TimeUnit.MILLISECONDS))
            {
                try {
                    // System.out.println(lock.tryLock());
                    // lock.lock();
                    int current = counter;
                    System.out.println("Before: " + counter + " - thread Id: " + Thread.currentThread().threadId());
                    counter = current + 1;
                    System.out.println("After: " + counter + " - thread Id: " + Thread.currentThread().threadId());
                } finally {
                    lock.unlock();
                }
            }
            else {
                System.out.println("Unable to lock the code");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            Thread thread = new Thread(CountLockFW::incrementCounter);
            thread.start();
        }
    }
}

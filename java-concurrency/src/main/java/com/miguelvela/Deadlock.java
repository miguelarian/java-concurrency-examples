package com.miguelvela;

import javax.xml.transform.Source;

public class Deadlock {

    public static void main(String[] args) {
        final String resource1 = "R1";
        final String resource2 = "R2";

        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1 has: " + resource1);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (resource2) {
                    System.out.println("Thread 1: This code is not reachable");
                    System.out.println("Thread 1 has: "+ resource2);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2 has: " + resource2);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (resource1) {
                    System.out.println("Thread 2: This code is not reachable");
                    System.out.println("Thread 2 has: " + resource1);
                }
            }
        });

        t1.start();
        t2.start();
    }
}

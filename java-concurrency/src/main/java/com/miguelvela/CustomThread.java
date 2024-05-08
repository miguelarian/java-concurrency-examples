package com.miguelvela;

public class CustomThread extends Thread {
    @Override
    public void run() {
        System.out.println("A custom thread running!");
    }
}

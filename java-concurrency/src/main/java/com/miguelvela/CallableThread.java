package com.miguelvela;

import java.util.concurrent.Callable;

public class CallableThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("I am callable!");
        return "I am callable returned value";
    }
}

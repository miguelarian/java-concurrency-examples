package com.miguelvela;

import java.util.*;

public class SynchronizedCollections {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Mike", "John", "Peter", "Mary"));

        List<String> syncList = Collections.synchronizedList(names);
        for(String name : syncList) {
            System.out.println(name);
            syncList.remove(name); // exception
        }
    }
}

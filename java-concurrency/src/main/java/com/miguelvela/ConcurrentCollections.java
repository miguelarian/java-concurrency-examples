package com.miguelvela;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

public class ConcurrentCollections {

    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("Mike", "C#");
        map.put("Jacob", "Java");
        map.put("Pep", "PHP");

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.offer("Maria");
        queue.offer("Elisabet");
        queue.offer("Tom");

        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.size());
        System.out.println(queue.contains("Elisabet"));
        System.out.println(queue);

        try {
            queue.offer("Jim", 200, TimeUnit.MILLISECONDS);
            queue.poll(300, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(queue);

        Set<String> set = new ConcurrentSkipListSet<>();
        set.add("Elias");
        set.add("Mary");
        set.add("John");

        for(String name : set) {
            System.out.println(name);
        }

        Map<String,String> map2 = new ConcurrentHashMap<>();
        map2.put("Mike", "C#");
        map2.put("Jacob", "Java");
        map2.put("Pep", "PHP");

        for(String key : map2.keySet()) {
            System.out.println("key: " + key + " - value: " + map2.get(key));
        }
    }
}

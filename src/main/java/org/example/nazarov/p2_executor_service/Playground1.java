package org.example.nazarov.p2_executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Playground1 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        ExecutorService executor = Executors.newScheduledThreadPool(5);
//        ExecutorService executor = Executors.newWorkStealingPool();

        Runnable task = () -> {
            int result = 12 * 15;
            updateValue(result);
        };

        executor.submit(task);
    }

    private static void updateValue(int result) {
        System.out.println(Thread.currentThread().getName() + ": " + result);
    }
}

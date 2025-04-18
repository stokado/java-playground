package edu.nazarov.stepik.p2_executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Playground2 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int value = i;
            executor.submit(() -> System.out.println(value));
        }
    }
}

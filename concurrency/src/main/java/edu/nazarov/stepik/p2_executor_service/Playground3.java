package edu.nazarov.stepik.p2_executor_service;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Playground3 {
    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Callable<Integer> c = () -> {
            Thread.sleep(5000);
            return 12 * 15;
        };

        Future<Integer> res = executor.submit(c);
        while (!res.isDone()) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " still working...");
        }
        Integer value = res.get();
        System.out.println(value);
    }
}

package edu.nazarov.pools;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingDeque;

public class ForkJoinPoolSample {
    public static void main(String[] args) {
        ExecutorService pool = new ForkJoinPool();
        ConcurrentLinkedDeque<Integer> queue = new ConcurrentLinkedDeque<>();
        LinkedBlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque<>();
    }
}

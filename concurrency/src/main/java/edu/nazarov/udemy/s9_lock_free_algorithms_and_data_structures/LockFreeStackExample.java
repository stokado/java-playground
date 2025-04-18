package edu.nazarov.udemy.s9_lock_free_algorithms_and_data_structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class LockFreeStackExample {
    public static void main(String[] args) throws InterruptedException {
//        StandardStack<Integer> stack = new StandardStack<>();
        LockFreeStack<Integer> stack = new LockFreeStack<>(); // 3x better performance
        Random random = new Random();

        for (int i = 0; i < 100000; i++) {
            stack.push(random.nextInt());
        }

        List<Thread> threads = new ArrayList<>();

        int pushingThreads = 2;
        int poppingThreads = 2;

        for (int i = 0; i < pushingThreads; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    stack.push(random.nextInt());
                }
            });

            thread.setDaemon(true);
            threads.add(thread);
        }

        for (int i = 0; i < poppingThreads; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    stack.pop();
                }
            });

            thread.setDaemon(true);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        Thread.sleep(10000);

        System.out.printf("%,d operations were performed in 10 seconds ", stack.getCounter());
    }

    private static class StandardStack<T> {
        private StackNode<T> head;
        private int counter = 0;

        public synchronized void push(T value) {
            StackNode<T> newHead = new StackNode<>(value);
            newHead.next = head;
            head = newHead;
            counter++;
        }

        public synchronized T pop() {
            if (head == null) {
                counter++;
                return null;
            }

            T value = head.value;
            head = head.next;
            counter++;
            return value;
        }

        public int getCounter() {
            return counter;
        }
    }

    private static class LockFreeStack<T> {
        private AtomicReference<StackNode<T>> head = new AtomicReference<>();
        private AtomicInteger counter = new AtomicInteger(0);

        public void push(T value) {
            StackNode<T> newHead = new StackNode<>(value);

            while (true) {
                StackNode<T> current = head.get();
                newHead.next = current;
                if (head.compareAndSet(current, newHead)) {
                    break;
                } else {
                    LockSupport.parkNanos(1);
                }
            }
            counter.incrementAndGet();
        }

        public T pop() {
            StackNode<T> current = head.get();
            StackNode<T> newHead;

            while (current != null) {
                newHead = current.next;
                if (head.compareAndSet(current, newHead)) {
                    break;
                } else {
                    LockSupport.parkNanos(1);
                    current = head.get();
                }
            }
            counter.incrementAndGet();
            return current != null ? current.value : null;
        }

        public int getCounter() {
            return counter.get();
        }
    }

    private static class StackNode<T> {
        public T value;
        public StackNode<T> next;

        public StackNode(T value) {
            this.value = value;
        }
    }
}

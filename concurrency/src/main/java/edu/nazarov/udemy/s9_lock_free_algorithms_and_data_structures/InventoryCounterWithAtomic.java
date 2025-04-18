package edu.nazarov.udemy.s9_lock_free_algorithms_and_data_structures;

import java.util.concurrent.atomic.AtomicInteger;

public class InventoryCounterWithAtomic {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();

        decrementingThread.start();

        incrementingThread.join();

        decrementingThread.join();

        System.out.println("We currently have " + inventoryCounter.getCount() + " items");
    }

    private static class DecrementingThread extends Thread {
        private final InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; ++i) {
                inventoryCounter.decrement();
            }
        }
    }

    private static class IncrementingThread extends Thread {
        private final InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; ++i) {
                inventoryCounter.increment();
            }
        }
    }

    private static class InventoryCounter {
        private final AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet();
        }

        public void decrement() {
            count.decrementAndGet();
        }

        public int getCount() {
            return count.get();
        }
    }
}

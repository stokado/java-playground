package edu.nazarov.udemy.s6_concurrency_challenges_intro;

public class SynchronizedKeyword {
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
        private int count = 0;
        private final Object lock = new Object();

        public void increment() {
            synchronized (lock) {
                count++;
            }
        }

        public void decrement() {
            synchronized (lock) {
                count--;
            }
        }

        @SuppressWarnings("unused")
        public synchronized void increment2() {
            count++;
        }

        @SuppressWarnings("unused")
        public synchronized void decrement2() {
            count--;
        }

        public synchronized int getCount() {
            return count;
        }
    }
}

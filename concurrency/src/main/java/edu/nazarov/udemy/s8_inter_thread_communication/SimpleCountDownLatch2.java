package edu.nazarov.udemy.s8_inter_thread_communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleCountDownLatch2 {
    private int count;
    private final Lock lock = new ReentrantLock();
    private final Condition isZero = lock.newCondition();

    public SimpleCountDownLatch2(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count cannot be negative");
        }
        this.count = count;
    }

    /**
     * Causes the current thread to wait until the latch has counted down to zero.
     * If the current count is already zero then this method returns immediately.
     */
    public void await() throws InterruptedException {
        lock.lock();
        try {
            while (count > 0) {
                isZero.await();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Decrements the count of the latch, releasing all waiting threads when the count reaches zero.
     * If the current count already equals zero then nothing happens.
     */
    public void countDown() {
        lock.lock();
        try {
            if (count > 0) {
                count--;
                if (count == 0) {
                    isZero.signalAll();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns the current count.
     */
    public int getCount() {
        return count;
    }
}


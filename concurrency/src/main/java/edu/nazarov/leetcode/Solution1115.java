package edu.nazarov.leetcode;

import java.util.concurrent.CountDownLatch;

public class Solution1115 {
    private int n;
    private CountDownLatch fooLatch = new CountDownLatch(0);
    private CountDownLatch barLatch = new CountDownLatch(1);

    public Solution1115(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            fooLatch.await();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            barLatch.countDown();
            fooLatch = new CountDownLatch(1);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            barLatch.await();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            fooLatch.countDown();
            barLatch = new CountDownLatch(1);
        }
    }
}

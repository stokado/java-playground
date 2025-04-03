package org.example.nazarov.p3_problems;

import lombok.Getter;

/*
Lost updates

Count is incremented in 3 steps:
1) read current value to a temp variable
2) increment temp by 1
3) write temp value to a count

Since t1 and t2 work at the same time, changes from one thread
can rewrite changes from second thread

Solution: use synchronized block to ensure one thread is working with a variable
 */
@Getter
public class Playground2 {
    private int count = 0;
//    private Object lock = new Object();

    public /* synchronized */ void increment() {
//        synchronized (lock) {
        count++;
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        Playground2 playground = new Playground2();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                playground.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                playground.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("count = " + playground.getCount());
    }
}

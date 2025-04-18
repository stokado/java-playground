package edu.nazarov.stepik.p3_problems;

import lombok.AllArgsConstructor;
import lombok.ToString;

/*
Inconsistent variables

If variables are logically related to each other,
the object may be in an inconsistent state when they are changed.

Expected output: (0, 0) or (100, 100)
Actual: (100, 0) because we were unlucky with timings in each thread
 */
public class Playground1 {
    @ToString
    @AllArgsConstructor
    private static class Point {
        int x;
        int y;
    }

    public static void main(String[] args) {
        Point p = new Point(0, 0);

        Thread t1 = new Thread(() -> {
            p.x = 100;
            try {
                // simulate delay in t1
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            p.y = 100;
        });

        Thread t2 = new Thread(() -> System.out.println(p));

        t1.start();
        t2.start();
    }
}

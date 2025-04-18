package edu.nazarov.stepik.p3_problems;

/*
Threads do not see changes made by another threads

Here a reader thread may not see a new value for a variable that
a writer thread has written

Expected:
value updated: 1
value = 1
value updated: 2
value = 2
value updated: 3
value = 3
value updated: 4
value = 4
value updated: 5
value = 5

Actual:
value updated: 1
value = 1
value updated: 2
value updated: 3
value updated: 4
value updated: 5

Solution: use volatile keyword on a property, that is accessed by multiple threads.
 */
public class Playground3 {
    /* volatile */ private static int value;

    public static void main(String[] args) {
        // Read value
        Thread reader = new Thread(() -> {
            int temp = 0;
            while (true) {
                if (temp != value) {
                    temp = value;
                    System.out.println("value = " + value);
                }
            }
        });

        reader.setDaemon(true); // Stop a program after writer

        // Update variable value
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                value++;
                System.out.println("value updated: " + value);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        reader.start();
        writer.start();
    }
}

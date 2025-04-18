package edu.nazarov.udemy.s6_concurrency_challenges_intro;

public class RaceCondition {
    public static void main(String[] args) {
        SharedClass shared = new SharedClass();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                shared.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                shared.checkForDataRace();
            }
        });

        thread1.start();
        thread2.start();
    }

    private static class SharedClass {
        // solution - volatile, this guarantees that compiler does not perform optimization leading to out of order execution
        private volatile int x = 0;
        private volatile int y = 0;

        public void increment() {
            x++;
            y++;
        }

        public void checkForDataRace() {
            if (y > x) {
                System.out.println("y > x - Data Race is detected");
            }
        }
    }
}

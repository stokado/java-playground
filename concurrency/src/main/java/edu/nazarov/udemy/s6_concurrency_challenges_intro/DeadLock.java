package edu.nazarov.udemy.s6_concurrency_challenges_intro;

import java.util.Random;

/*
Deadlock Conditions
1) Mutual Exclusion - Only one thread can have exclusive access to a resource
2) Hold and Wait - at least one thread is holding a resource and is waiting for another resource
3) Non-preemptive allocation - A resource is released only after the thread is done using it
4) Circular wait - A chain of at least two threads each one is holding one resource and waiting for another resource

Solutions to deadlock
1) Avoid Circular wait - Enforce a strict order in lock acquisition
 */
public class DeadLock {
    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        Thread trainAThread = new Thread(new TrainA(intersection));
        Thread trainBThread = new Thread(new TrainB(intersection));

        trainAThread.start();
        trainBThread.start();
    }

    private static class TrainA implements Runnable {
        private final Intersection intersection;
        private final Random random = new Random();

        public TrainA(Intersection intersection) {
            this.intersection = intersection;
        }

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {}

                intersection.takeRoadA();
            }
        }
    }

    private static class TrainB implements Runnable {
        private final Intersection intersection;
        private final Random random = new Random();

        public TrainB(Intersection intersection) {
            this.intersection = intersection;
        }

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {}

                intersection.takeRoadB();
            }
        }
    }

    private static class Intersection {
        private final Object roadA = new Object();
        private final Object roadB = new Object();

        public void takeRoadA() {
            synchronized (roadA) {
                System.out.println("Road A is locked by thread " + Thread.currentThread().getName());

                synchronized (roadB) {
                    System.out.println("Train is passing through road A");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {}
                }
            }
        }

        public void takeRoadB() {
            synchronized (roadA) {
                System.out.println("Road B is locked by thread " + Thread.currentThread().getName());

                synchronized (roadB) {
                    System.out.println("Train is passing through road B");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {}
                }
            }
        }
    }
}

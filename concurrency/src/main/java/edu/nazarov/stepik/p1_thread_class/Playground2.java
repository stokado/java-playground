package edu.nazarov.stepik.p1_thread_class;

public class Playground2 {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " World!");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " Hello"));
        Thread t2 = new MyThread();
        t1.start();
        t2.start();
    }
}


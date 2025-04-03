package org.example.nazarov.p1_thread_class;

public class Playground7 {
    public static void print() {
        String name = Thread.currentThread().getName();
        System.out.println(name);
    }

    public static void main(String[] args) throws InterruptedException {
        String name = "Empty";

        Thread t1 = new Thread(Playground7::print);
        Thread t2 = new Thread(Playground7::print);

        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }
}

package org.example.nazarov.p1_thread_class;

public class Playground6 {
    public static void print() {
        int i = 5;
        System.out.println(i);
        i += 10;
        System.out.println(i);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(Playground6::print);
        Thread t2 = new Thread(Playground6::print);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

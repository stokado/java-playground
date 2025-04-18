package edu.nazarov.stepik.p1_thread_class;

public class Playground3 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()); // "main"

        Thread t = new Thread(() -> System.out.println("hello from thread"));
        System.out.println(t.getName()); // "Thread-0"
        t.setName("Update users thread");
        t.setDaemon(true);
        t.start();
        System.out.println(t.getName()); // "Update users thread"
    }
}

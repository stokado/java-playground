package edu.nazarov.udemy.s2_thread_creation;

public class ThreadCreation1 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Hello from thread: " + Thread.currentThread().getName());
        });

        System.out.println("Hello from main: " + Thread.currentThread().getName());
        thread.start();
    }
}

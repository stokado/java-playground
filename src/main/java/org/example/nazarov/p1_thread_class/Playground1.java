package org.example.nazarov.p1_thread_class;

public class Playground1 {
    public static void main(String[] args) {
        Runnable task1 = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println(i);
            }
        };

        Runnable task2 = () -> {
            for (int i = 10; i <= 14; i++) {
                System.out.println(i);
            }
        };

        Thread sveta = new Thread(task1);
        Thread misha = new Thread(task2);
        sveta.start();
        misha.start();
    }
}
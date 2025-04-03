package org.example.nazarov.p1_thread_class;

public class Playground5 extends Thread {
    private String message;

    public Playground5(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        int i = 5;
        i++;
        System.out.println(message + i);
    }

    public static void main(String[] args) {
        String message = "Hello ";

        Playground5 t1 = new Playground5(message);
        Playground5 t2 = new Playground5(message);
        t1.start();
        t2.start();
    }
}

package edu.nazarov.stepik.p1_thread_class;

import lombok.SneakyThrows;

public class Playground4 {
    @SneakyThrows
    public static void main(String[] args) {
        Thread t = new Thread(Playground4::longTask);
        t.start();
        t.join();

        System.out.println("Done");
    }

    @SneakyThrows
    public static void longTask() {
        Thread.sleep(1000L);
        System.out.println(Thread.currentThread().getName() + " done longTask");
    }
}

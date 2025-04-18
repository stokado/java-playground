package edu.nazarov.udemy.s10_threading_model;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IoBoundApplication {
    private static final int NUMBER_OF_TASKS = 10_000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to start");
        scanner.nextLine();
        System.out.printf("Running %d tasks\n", NUMBER_OF_TASKS);

        long startTime = System.currentTimeMillis();
        performTasks();
        System.out.printf("Tasks took %dms to complete\n", System.currentTimeMillis() - startTime);
    }

    private static void performTasks() {
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executorService.submit(IoBoundApplication::blockingIoOperation);
            }
        }
    }

    // Simulates a long blocking IO
    private static void blockingIoOperation() {
        System.out.println("Executing a blocking task from thread: " + Thread.currentThread());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

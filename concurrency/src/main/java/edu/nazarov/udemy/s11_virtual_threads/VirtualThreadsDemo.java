package edu.nazarov.udemy.s11_virtual_threads;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreadsDemo {
    private static final int NUMBER_OF_VIRTUAL_THREADS = 100;

    public static void main(String[] args) throws InterruptedException {
        List<Thread> virtualThreads = new ArrayList<>(NUMBER_OF_VIRTUAL_THREADS);

        for (int i = 0; i < NUMBER_OF_VIRTUAL_THREADS; i++) {
            Thread virtualThread = Thread.ofVirtual()
                    .unstarted(new BlockingTask());
            virtualThreads.add(virtualThread);
        }

        virtualThreads.forEach(Thread::start);

        for (Thread virtualThread : virtualThreads) {
            virtualThread.join();
        }
    }

    private static class BlockingTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Inside thread: " + Thread.currentThread() + " before blocking call");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Inside thread: " + Thread.currentThread() + " after blocking call");
        }
    }
}

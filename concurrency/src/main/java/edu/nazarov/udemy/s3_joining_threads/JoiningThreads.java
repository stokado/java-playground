package edu.nazarov.udemy.s3_joining_threads;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoiningThreads {

    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(10000000L, 3435L, 35435L, 2324L, 23L, 2453L, 5566L, 10000000L);
        List<FactorialThread> threads = new ArrayList<>(inputNumbers.size());
        for (Long number : inputNumbers) {
            threads.add(new FactorialThread(number));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join(10000);
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                factorialThread.interrupt();
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }
    }

    private static class FactorialThread extends Thread {
        private final long inputNumber;
        private BigInteger result = BigInteger.ONE;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            try {
                this.result = factorial(inputNumber);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " didn't calculate factorial of " + inputNumber + " in time");
                return;
            }
            this.isFinished = true;
        }

        private BigInteger factorial(long inputNumber) throws InterruptedException {
            BigInteger result = BigInteger.ONE;

            for (long i = inputNumber; i > 0; i--) {
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                result = result.multiply(BigInteger.valueOf(i));
            }

            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }
}

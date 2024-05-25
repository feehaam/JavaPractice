package com.feeham.concurrency;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

// All thread are set to daemon and joined with limit 1000ms: meaning that, they will stop executing
// after 1000ms regardless of they are done or not. Main thread will only wait 1 sec at max.
public class C_Join {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 50, 100, 10000, 100000, 10000000);
        List<Thread> threads = numbers.stream().map(n -> new Thread(new FactorialCalculator(n))).toList();

        threads.forEach(thread -> {
            thread.setDaemon(true);
            thread.start();
        });

        threads.forEach(thread -> {
            try {
                thread.join(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Main is done. ");
    }

    private static class FactorialCalculator implements Runnable {
        private final int number;
        public FactorialCalculator(int number) {
            this.number = number;
        }
        @Override
        public void run() {
            BigInteger factorial = calculateFactorial(number);
            System.out.println("Done calculating factorial of " + number);
            //System.out.printf("The factorial of %d is %s%n", number, factorial.toString());
        }
        private BigInteger calculateFactorial(int n) {
            BigInteger result = BigInteger.ONE;
            for (int i = 1; i <= n; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        }
    }
}

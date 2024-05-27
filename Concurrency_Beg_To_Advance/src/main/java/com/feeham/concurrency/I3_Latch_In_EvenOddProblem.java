package com.feeham.concurrency;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

// Latch is a trigger for thread, when the latch becomes 0, we can perform some actions in a thread.
// So instead of a turn, we can just use latch with counter 1 for the even thread. As a result even thread will never start
// first, because the latch is not zero. Odd thread will start, reduce the latch and then everything alright.
public class I3_Latch_In_EvenOddProblem {
    private static final int LIMIT = 10;

    static class OddThread extends Thread {
        private final Object locker;
        private final CountDownLatch latch;
        public OddThread(Object locker, CountDownLatch latch){
            this.locker = locker;
            this.latch = latch;
        }

        @SneakyThrows
        public void run(){
            synchronized (locker){
                for (int i=1; i<LIMIT; i+=2){
                    latch.countDown();
                    System.out.println("Odd thread: " + i);
                    locker.notify();
                    locker.wait();
                }
                locker.notify();
            }
        }
    }

    static class EvenThread extends Thread {
        private final Object locker;
        private final CountDownLatch latch;
        public EvenThread(Object locker, CountDownLatch latch){
            this.locker = locker;
            this.latch = latch;
        }

        @SneakyThrows
        public void run(){
            latch.await();
            synchronized (locker){
                for (int i=2; i<=LIMIT; i+=2){
                    System.out.println("Even thread: " + i);
                    locker.notify();
                    locker.wait();
                }
                locker.notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object locker = new Object();
        CountDownLatch latch = new CountDownLatch(1);
        Thread odd = new OddThread(locker, latch);
        Thread even = new EvenThread(locker, latch);

        odd.start();
        even.start();
        odd.join();
    }
}

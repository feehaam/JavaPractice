package com.feeham.concurrency;

import java.util.HashMap;
import java.util.Map;

// This program demonstrates the use of thread interruption in Java concurrency.
public class A_Interrupt {

    public static void main(String ... x){
        Map<String, Thread> threads = new HashMap<>();
        Thread threadA = new Thread(new ThreadA(threads));
        Thread threadB = new Thread(new ThreadB(threads));

        // Storing threads in a Map to reference them later for interruption
        threads.put("A", threadA);
        threads.put("B", threadB);

        // Starting both threads
        threadA.start();
        threadB.start();
    }

    private static class ThreadA implements Runnable{
        private Map<String, Thread> threads;

        public ThreadA(Map<String, Thread> threads){
            this.threads = threads;
        }

        @Override
        public void run() {
            for(int i=0; i<10; i++) {
                System.out.println(Thread.currentThread().getName() + " => " + i);

                // ThreadA sleeps for 200ms between printing numbers
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // Interrupting ThreadB after completion of ThreadA's for-loop.
            threads.get("B").interrupt();
        }
    }

    private static class ThreadB implements Runnable{
        private Map<String, Thread> threads;

        public ThreadB(Map<String, Thread> threads){
            this.threads = threads;
        }

        @Override
        public void run() {
            for(int i=0; i<100; i++) {

                // Checking for interruption
                if (Thread.interrupted()) {
                    System.out.println(Thread.currentThread().getName() + " is interrupted!");
                    return;
                }

                System.out.println(Thread.currentThread().getName() + " => " + i);

                // ThreadB sleeps for 50ms between printing numbers; handling interruption explicitly
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " is interrupted during sleep!");
                    return;
                }
            }
        }
    }
}
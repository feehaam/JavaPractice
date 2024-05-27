package com.feeham.concurrency;

/*
    Problem statement
    * Print numbers 1, 2, 3 to 20 in order.
    * Odd numbers should be printed by the odd thread
    * Even numbers should be printed by the even thread

    Sample Output
    OddThread: 1
    EvenThread: 2
    .
    .
    OddThread: 19
    EvenThread: 20
*/

// Solution: Each thread will use same lock so that one waits when other is working, odd will work -> notify -> go to wait,
// then even will work -> notify -> wait; done!

// Problem: Though very rare but still if EvenThread starts and calls wait before the OddThread calls notify, both threads could end up waiting on the lock,
// leading to a deadlock. However, in practice, due to thread scheduling and the fact that both threads start almost simultaneously,
// the OddThread typically gets to execute first and notify the EvenThread.

import lombok.SneakyThrows;

public class I1_Deadlock_In_EvenOddProblem {
    private static final int LIMIT = Integer.MAX_VALUE; // Didn't use 20 here to meet deadlock

    static class OddThread extends Thread {
        private final Object locker;
        public OddThread(Object locker){
            this.locker = locker;
        }

        @SneakyThrows
        public void run(){
            synchronized (locker){
                for (int i=1; i<LIMIT; i+=2){
                    System.out.println("Odd thread: " + i);
                    locker.notify();
                    locker.wait();
                }
            }
        }
    }

    static class EvenThread extends Thread {
        private final Object locker;
        public EvenThread(Object locker){
            this.locker = locker;
        }

        @SneakyThrows
        public void run(){
            synchronized (locker){
                for (int i=2; i<LIMIT - 2; i+=2){
                    System.out.println("Even thread: " + i);
                    locker.notify();
                    locker.wait();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object locker = new Object();
        Thread odd = new OddThread(locker);
        Thread even = new EvenThread(locker);

        odd.start();
        even.start();
    }
}

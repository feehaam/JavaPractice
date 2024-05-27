package com.feeham.concurrency;

import lombok.SneakyThrows;

// By using a flag, we can ensure that the odd starts first always solving the deadlock.
// NOTE: The turn is a single element array, why!? Why didn't use a boolean? Cz, passing to threads require a variable
// to be final, if we use boolean then we can't change the value however an array can be final yet change its elements value
// (though not applicable in this code, but for other times this is a tricky practice)
public class I2_TurnFlag_In_EvenOddProblem {
    private static final int LIMIT = 10;

    static class OddThread extends Thread {
        private final Object locker;
        private final boolean [] turn;
        public OddThread(Object locker, boolean [] turn){
            this.locker = locker;
            this.turn = turn;
        }

        @SneakyThrows
        public void run(){
            synchronized (locker){
                for (int i=1; i<LIMIT; i+=2){
                    while(!turn[0]) locker.wait();
                    System.out.println("Odd thread: " + i);
                    turn[0] = false;
                    locker.notify();
                }
            }
        }
    }

    static class EvenThread extends Thread {
        private final Object locker;
        private final boolean [] turn;
        public EvenThread(Object locker, boolean [] turn){
            this.locker = locker;
            this.turn = turn;
        }

        @SneakyThrows
        public void run(){
            synchronized (locker){
                for (int i=2; i<=LIMIT; i+=2){
                    while(turn[0]) locker.wait();
                    System.out.println("Even thread: " + i);
                    turn[0] = true;
                    locker.notify();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object locker = new Object();
        boolean [] turn = {true};
        Thread odd = new OddThread(locker, turn);
        Thread even = new EvenThread(locker, turn);

        odd.start();
        even.start();
    }
}

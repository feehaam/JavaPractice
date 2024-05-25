package com.feeham.concurrency;

// This program demonstrates the use of Daemon thread, which runs in background and terminates right when
// the main thread is done executing, regardless of Daemon thread finished with its task or not.
public class B_Daemon {
    public static void main(String ... x){
        Thread threadB = new Thread(new ThreadB());
        // Setting as daemon
        threadB.setDaemon(true);
        threadB.start();
        for(int i=0; i<10; i++) {
            System.out.println(Thread.currentThread().getName() + " => " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) { }
        }
    }

    private static class ThreadB implements Runnable{

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
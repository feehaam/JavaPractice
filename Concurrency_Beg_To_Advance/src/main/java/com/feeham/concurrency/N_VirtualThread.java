package com.feeham.concurrency;

public class N_VirtualThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = Thread.ofVirtual().start(() -> System.out.println("Hello from vt1"));
        Thread thread2 = Thread.ofVirtual().factory().newThread(() -> System.out.println("Hello from vt2"));
        thread2.start();
        thread1.join();
        thread2.join();
    }
}

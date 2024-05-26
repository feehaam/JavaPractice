package com.feeham.concurrency;

// Just the order is same now. Worst case: One chef will be working on a resource and other might be waiting for it
// although another resource is free, resulting in a queue. But it will never make deadlock.
public class H_DeadlockChef_Solution {

    public static void main(String[] args) throws InterruptedException {
        Stove stove = new Stove();
        Sink sink = new Sink();

        Chef1 chef1 = new Chef1();
        Chef2 chef2 = new Chef2();

        Thread thread1 = new Thread(() -> {
            chef1.work(stove, sink);
        });

        Thread thread2 = new Thread(() -> {
            chef2.work(stove, sink);
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    private static class Chef1 {
        public void work(Stove stove, Sink sink) {
            while (true) {
                System.out.println("Sink is taken by CHEF-1");
                synchronized (sink) {
                    sink.clean("CHEF-1");

                    System.out.println("Stove is taken by CHEF-1");
                    synchronized (stove) {
                        stove.cook("CHEF-1");
                    }
                }
            }
        }
    }

    private static class Chef2 {
        public void work(Stove stove, Sink sink) {
            while (true) {
                System.out.println("Sink is taken by CHEF-2");
                synchronized (sink) {
                    stove.cook("CHEF-2");

                    System.out.println("Stove is taken by CHEF-2");
                    synchronized (stove) {
                        sink.clean("CHEF-2");
                    }
                }
            }
        }
    }

    private static class Stove {
        public void cook(String chef) {
            System.out.println(chef + " is cooking.");
        }
    }

    private static class Sink {
        public void clean(String chef) {
            System.out.println(chef + " is cleaning.");
        }
    }
}

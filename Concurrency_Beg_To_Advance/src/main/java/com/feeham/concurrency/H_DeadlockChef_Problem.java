package com.feeham.concurrency;

// There are two chefs, chef1 always cleans the dish first then cooks foods and chef2 always cooks food then cleans the dish problem is: there is
// only one stove and one sink! In a busy day while both of them are working, the chef1 told chef2, "I'll leave the sink for you
// after you free the stove for me", and the chef2 replied, "if I leave the stove then where will I put my food as my dishes are not cleaned yet!
// I can only leave the stove when you are done in sink"... They got deadlocked, then, now, forever...
public class H_DeadlockChef_Problem {

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
                System.out.println("Stove is taken by CHEF-2");
                synchronized (stove) {
                    stove.cook("CHEF-2");

                    System.out.println("Sink is taken by CHEF-2");
                    synchronized (sink) {
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

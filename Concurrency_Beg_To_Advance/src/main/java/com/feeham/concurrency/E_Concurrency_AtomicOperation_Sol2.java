package com.feeham.concurrency;

// Instead of method blocking, lets do section blocking or locking, benefits: each method can be used by
// multiple threads, smaller blocking section
public class E_Concurrency_AtomicOperation_Sol2 {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);
        incrementingThread.start();
        decrementingThread.start();
        incrementingThread.join();
        decrementingThread.join();
        System.out.println("We currently have " + inventoryCounter.getItems() + " items");
    }

    public static class DecrementingThread extends Thread {
        private InventoryCounter inventoryCounter;
        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrement();
            }
        }
    }

    public static class IncrementingThread extends Thread {
        private InventoryCounter inventoryCounter;
        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }
        @Override
        public void run() {
            // Increment inventory counter 10,000 times
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
            }
        }
    }

    private static class InventoryCounter {
        private int items = 0;
        final Object lock = new Object();
        public void increment() {
            // Write un-blocking code here...
            synchronized (lock) {
                items++;
            }
            // Write un-blocking code here...
        }
        public void decrement() {
            // Write un-blocking code here...
            synchronized (lock) {
                items--;
            }
            // Write un-blocking code here...
        }
        public int getItems() {
            return items;
        }
    }
}
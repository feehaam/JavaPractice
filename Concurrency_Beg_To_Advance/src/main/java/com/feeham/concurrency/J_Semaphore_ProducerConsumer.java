package com.feeham.concurrency;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

public class J_Semaphore_ProducerConsumer {

    static class ProducerMachine implements Runnable {
        private final Item item;
        private final int productsPerHour;
        private final int timeToMakeAProductBatch;

        ProducerMachine(Item item, Semaphore semaphore, int productsPerHour, int timeToMakeAProductBatch) {
            this.item = item;
            this.productsPerHour = productsPerHour;
            this.timeToMakeAProductBatch = timeToMakeAProductBatch;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                synchronized (item) {
                    while (item.quantity > 0) {
                        item.wait();
                    }
                    System.out.println("Products count: " + item.quantity + " | ");
                    System.out.println("Producer machine working, current inventory: " + item.quantity);
                }
                Thread.sleep(timeToMakeAProductBatch);
                synchronized (item) {
                    item.quantity += productsPerHour;
                    System.out.println("Producer machine added " + productsPerHour + " " + item.name + " in inventory.");
                    System.out.println("Products count: " + item.quantity + " | " + "Semaphore: ");
                    item.notifyAll();
                }
            }
        }
    }

    static class ConsumerVehicle implements Runnable {
        private final String vehicleName;
        private final Item item;
        private final Semaphore semaphore;
        private final int consumptionPerHour;
        private final int timeToCompleteTransportation;

        ConsumerVehicle(String vehicleName, Item item, Semaphore semaphore, int consumptionPerHour, int timeToCompleteTransportation) {
            this.item = item;
            this.semaphore = semaphore;
            this.vehicleName = vehicleName;
            this.consumptionPerHour = consumptionPerHour;
            this.timeToCompleteTransportation = timeToCompleteTransportation;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                semaphore.acquire(consumptionPerHour);
                synchronized (item) {
                    while (item.quantity <= 0) {
                        item.wait();
                    }
                    System.out.println("Products count: " + item.quantity + " | " + "Semaphore: " + semaphore.availablePermits() + " | " + vehicleName + " carrying " + consumptionPerHour + " " + item.name);
                    item.quantity -= consumptionPerHour;
                }
                Thread.sleep(timeToCompleteTransportation);
                System.out.println("Products count: " + item.quantity + " | " + "Semaphore: " + semaphore.availablePermits() + " | "  + vehicleName + " has done its work.");
                semaphore.release(consumptionPerHour);
                synchronized (item) {
                    if (item.quantity <= 0) {
                        item.notifyAll();
                    }
                }
            }
        }
    }

    @AllArgsConstructor
    static class Item {
        String name;
        int quantity;
    }

    public static void main(String[] args) {
        int limitOfItem = 61;
        Semaphore semaphore = new Semaphore(limitOfItem);
        Item item = new Item("Treadmill", 0);

        ProducerMachine producer = new ProducerMachine(item, semaphore, limitOfItem, 20);
        ConsumerVehicle van = new ConsumerVehicle("Van", item, semaphore, 5, 10);
        ConsumerVehicle truck = new ConsumerVehicle("Truck", item, semaphore, 10, 10);
        ConsumerVehicle man = new ConsumerVehicle("Man", item, semaphore, 1, 8);

        Thread producerThread = new Thread(producer);
        Thread vanThread = new Thread(van);
        Thread truckThread = new Thread(truck);
        Thread manThread = new Thread(man);

        producerThread.start();
        vanThread.start();
        truckThread.start();
        manThread.start();
    }
}

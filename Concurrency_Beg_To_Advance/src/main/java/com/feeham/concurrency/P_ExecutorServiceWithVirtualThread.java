package com.feeham.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class P_ExecutorServiceWithVirtualThread {
    public static void main(String[] args) {
        ExecutorService executorService1 = Executors.newVirtualThreadPerTaskExecutor();
        runManyThreads(executorService1, 100);
        ExecutorService executorService2 = Executors.newThreadPerTaskExecutor(Thread.ofVirtual().name("VT-", 1).factory());
        runManyThreads(executorService2, 100);
        shutDown(executorService1, executorService2);
    }

    public static void runManyThreads(ExecutorService executorService, int threadsCount) {
        while (threadsCount --> 0) {
            executorService.submit(() -> {
                System.out.printf("%s | %s | %s %n", Thread.currentThread().getThreadGroup(), Thread.currentThread().getName(), Thread.currentThread().getPriority());
            });
        }
    }

    public static void shutDown(ExecutorService ... executorServices){
        for (ExecutorService executorService : executorServices) {
            executorService.shutdown();
            executorService.close();
        }
    }
}

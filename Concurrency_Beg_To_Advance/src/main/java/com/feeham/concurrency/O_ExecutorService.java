package com.feeham.concurrency;

import java.util.concurrent.*;

public class O_ExecutorService {
    public static void main(String[] args) throws InterruptedException{
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        runManyThreads("single", singleThreadExecutor, 5);
        ExecutorService fixedThreadExecutor = Executors.newFixedThreadPool(10);
        runManyThreads("fixed", fixedThreadExecutor, 5);
        int initialPoolSize = 3, maxPoolSize = 8, increasedPoolsLifetime = 1, waitingQueueSize = 1000;
        ExecutorService customExecutor = new ThreadPoolExecutor(
                initialPoolSize,
                maxPoolSize,
                increasedPoolsLifetime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(waitingQueueSize)
        );
        runManyThreads("custom", customExecutor, 15);

        shutDown(singleThreadExecutor, fixedThreadExecutor, customExecutor);
    }

    public static void runManyThreads(String type, ExecutorService executorService, int threadsCount) {
        while (threadsCount --> 0) {
            executorService.submit(() -> {
                System.out.println("Type: " + type + "Thread Name: " + Thread.currentThread().getName());
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

package com.feeham.practiceJava21;

import java.util.concurrent.*;

public class I_TerminateExecutor {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future futureTask = executorService.submit(getSubTask("Callable task"));
        String result = (String) futureTask.get();
        System.out.println(result);
        // ExecutorService doesn't close itself automatically, so here is how to close it.
        // TODO: This code is incomplete
        // Approach 1
        executorService.close();
        // Approach 2: Terminates after sitting idol for 1 second.
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }

    private static Callable getSubTask(String message){
        return () -> {
            return Thread.currentThread().getName() + " -> " + message;
        };
    }
}
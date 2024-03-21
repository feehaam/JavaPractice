package com.feeham.practiceJava21;

import java.util.concurrent.*;

public class M_BFF2VirtualPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // The way BFF 2.0 creates its virtual threads. Its noting but just a little customization.
        // They just gives their virtual threads a custom name and a custom number (starting from 1)
        // like this: vt-1, vt-2, vt-3...
        ThreadFactory threadFactory = Thread.ofVirtual().name("vt-", 1).factory();
        ExecutorService executorService = Executors.newThreadPerTaskExecutor(threadFactory);

        for (int i=0; i<1600; i++){
            final String subTaskNo = i + "";
            // A lambda implementation of submit method, reduces code eliminating anonymous Callable implementation.
            Future<String> futureTask = executorService.submit(() -> Thread.currentThread().getName() + " -> Subtask-" + subTaskNo);
            String result = (String) futureTask.get();
            System.out.println(result);
        }
    }
}

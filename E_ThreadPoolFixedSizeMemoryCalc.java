package com.feeham.practiceJava21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Memory consumption calculation of the first (B_ThreadPoolFixedSize) threading approach
// There are 20 threads are running here, but they still take 1.8MB RAM. As number of thread is limited.
// However, because of putting other threads in the queue, its performance is slower than direct
// threading approach, but still this one is better as it doesn't crash the PC.
public class E_ThreadPoolFixedSizeMemoryCalc {

    public static void main(String[] args) {
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        for (int i=0; i<20; i++){
            final String subTaskNo = i + "";
            executorService.execute(getSubTask("Subtask-" + subTaskNo));
        }

        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory usage increased by: " + (afterUsedMem - beforeUsedMem));
    }

    private static Runnable getSubTask(String message){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " -> " + message);
            }
        };
    }
}
package com.feeham.practiceJava21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// ExecutorService can be defined in many ways, previous one was fixedThreadPool, this one
// is SingleThreadPool its same as newFixedThreadPool(1)
public class C_ThreadPoolSingle {

    public static void main(String[] args) {
        // Uses only one thread to process all requests
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i=0; i<10; i++){
            final String subTaskNo = i + "";
            executorService.execute(getSubTask("Subtask-" + subTaskNo));
        }
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
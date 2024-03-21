package com.feeham.practiceJava21;

import java.util.concurrent.*;

// A memory calculation of virtual thread. The previous memory calculation tests (D, E) took
// 1.8MB memory for running 10 threads. But virtual thread taking 2MB for 1800 threads!
// So it is taking almost 200 times less memory. Note that, in this case each thread itself is like
// an object/register, so each one takes some little memory for its own object/definition. That's why
// it's not exactly 200 times but around 180 times faster.
public class L_VirtualPoolMemoryCalc {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        for (int i=0; i<1800; i++){
            final String subTaskNo = i + "";
            Future futureTask = executorService.submit(getSubTask("Subtask-" + subTaskNo));
            String result = (String) futureTask.get();
            System.out.println(result);
        }
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory usage increased by: " + (afterUsedMem - beforeUsedMem));
    }

    private static Callable getSubTask(String message){
        return () -> {
            return Thread.currentThread().getName() + " -> " + message;
        };
    }
}
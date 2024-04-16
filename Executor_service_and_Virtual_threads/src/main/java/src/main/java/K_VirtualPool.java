import java.util.concurrent.*;

// Now we can create virtual threads instead of platform threads. Lighter, faster, simpler.
public class K_VirtualPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // The only change here is the method we call, nothing else
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        // The sub-tasks will be running in virtual threads, and note that these print statements
        // will not print thread name! Because virtual thread doesn't have any name by default.
        for (int i=0; i<10; i++){
            final String subTaskNo = i + "";
            Future futureTask = executorService.submit(getSubTask("Subtask-" + subTaskNo));
            String result = (String) futureTask.get();
            System.out.println(result);
        }
    }

    private static Callable getSubTask(String message){
        return () -> {
            return Thread.currentThread().getName() + " -> " + message;
        };
    }
}
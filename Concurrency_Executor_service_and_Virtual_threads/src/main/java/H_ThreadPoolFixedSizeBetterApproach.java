import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// Previously we used a loop and called all threads in it, however executorService offers a formal
// way to do that using invokeAll(). It does the exact same behaviour but with better performance and look.
public class H_ThreadPoolFixedSizeBetterApproach {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Callable<String>> callables = new ArrayList<>();
        for (int i=0; i<10; i++){
            final String subTaskNo = i + "";
            callables.add(getSubTask("Subtask-" + subTaskNo));
        }

        // Invoking all threads
        List<Future<String>> results = executorService.invokeAll(callables);
        for (Future future: results){
            System.out.println(future.get());
        }
    }

    private static Callable getSubTask(String message){
        return () -> {
            return Thread.currentThread().getName() + " -> " + message;
        };
    }
}
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Instead of directly executing one task, we can set it up for later execution.
public class F_RunnableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Called executorService.submit() instead of executorService.execute()
        Future futureTask = executorService.submit(getSubTask("Callable task"));
        // Prints false as it is prepared only, but no executed
        System.out.println(futureTask.isDone());
        // This triggers the execution
        futureTask.get();
        // Prints true as it has been executed.
        System.out.println(futureTask.isDone());
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
import java.util.concurrent.*;

// The previous approach was working in its own block because of public void run() method as it is 'void'
// So we can use Callable instead of that, if we want to return some data.
public class G_CallableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future futureTask = executorService.submit(getSubTask("Callable task"));
        System.out.println(futureTask.isDone());
        // Receiving data that is returned from the sub task thread.
        String result = (String) futureTask.get();
        System.out.println(result);
    }

    private static Callable getSubTask(String message){
        return () -> {
            return Thread.currentThread().getName() + " -> " + message;
        };
    }
}
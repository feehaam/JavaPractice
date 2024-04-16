import java.util.concurrent.*;

// A thread pool can be customized defining how many threads to create, size of waiting queue etc.
public class J_CustomizedPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Customization parameters
        int initialPoolSize = 4; // 4 threads will always be there, even if it is idol
        int maxPoolSize = 10; // If more than 4 request comes then, executorService will create at most 6 more threads
        int increasedPoolsLifetime = 3000; // The extra 6 threads will be terminated if those are idol for 3 sec
        int waitingQueueSize = 100; // Size of request waiting queue
        ExecutorService executorService = new ThreadPoolExecutor(
                initialPoolSize,
                maxPoolSize,
                increasedPoolsLifetime,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(waitingQueueSize)
        );

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
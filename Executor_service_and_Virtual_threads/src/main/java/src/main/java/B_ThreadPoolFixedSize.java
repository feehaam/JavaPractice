import java.util.concurrent.*;

// Using thread pool, we can make a predefined number of sets (here we used 4 threads), and use those
// for any amount of requests like a queue. ExecutorService is Javas most well known thread pool service
public class B_ThreadPoolFixedSize {

    public static void main(String[] args) {
        // There will be 4 fixed threads, even if request is 1000 or 0
        ExecutorService executorService = Executors.newFixedThreadPool(4);

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
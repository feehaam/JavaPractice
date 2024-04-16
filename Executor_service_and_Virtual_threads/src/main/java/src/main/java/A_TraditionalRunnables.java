// Traditional way of creating and using multiple threads - used platform threads
// Limitation: If number of threads are high, the system may fail as all of these threads requires a lot of memory
// And there is a limit of how many threads the CPU has.
public class A_TraditionalRunnables {

    public static void main(String[] args) {
        for(int i = 0; i < 4; i++) {
            final String subTaskNo = i + "";
            new Thread(getSubTask("Subtask-" + subTaskNo)).start();
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
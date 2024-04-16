// Memory consumption calculation of the first (A_TraditionalRunnables) threading approach
// Here only 8 threads takes around 1.85 million bytes (1.8MB)
public class D_TraditionalRunnablesMemoryCalc {

    public static void main(String[] args) {
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        for(int i = 0; i < 8; i++) {
            final String subTaskNo = i + "";
            new Thread(getSubTask("Subtask-" + subTaskNo)).start();
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
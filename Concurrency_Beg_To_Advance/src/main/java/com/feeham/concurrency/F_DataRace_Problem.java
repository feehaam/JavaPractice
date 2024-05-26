package com.feeham.concurrency;

// Here is another example of race condition, operating systems usually runs code without sequence for better performance
// As a result, EVEN IF WE USED SYNCHRONIZED KEYWORD still even before all 3 operations of x++ (get value of x,
// increment x by 1, set new value to x) is completed, the next line may start executing.
// Basically it was never supposed to happen: y > x. But it is happening because of data race condition.
public class F_DataRace_Problem {

    public static void main(String[] args) {
        Data data = new Data();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                data.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                data.checkForDataRace(i);
            }
        });
        thread1.start();
        thread2.start();
    }

    private static class Data{
        private  int x = 0;
        private  int y = 0;

        public synchronized void increment(){
            x++;
            y++;
        }

        public void checkForDataRace(int iterationNo){
            if(y > x) {
                System.out.println("Intercepted between two increments! @" + iterationNo + "th");
            }
        }
    }
}

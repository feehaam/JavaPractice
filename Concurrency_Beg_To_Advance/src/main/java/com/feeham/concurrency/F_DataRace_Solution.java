package com.feeham.concurrency;

// By using volatile, any line of code written before a volatile object will always run before volatile object read/write
// and any line below will execute after, in simple words: synchronized before and after. However, the lines before it may be async among them
// and lines after it as well!
public class F_DataRace_Solution {

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
        private volatile int x = 0;
        private volatile int y = 0;

        public void increment(){
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

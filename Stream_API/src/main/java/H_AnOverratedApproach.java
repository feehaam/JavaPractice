import java.util.stream.IntStream;

public class H_AnOverratedApproach {
    public static void main(String[] args) {
        int [] ar = new int[] {10, 5, 3, 8};

        // Instead of this dirty code
        for (int i=0; i<ar.length; i+=2){
            System.out.println(ar[i]);
        }
        // We can write this clean code
        IntStream.iterate(0, i -> i + 2).limit(ar.length / 2)
                .forEach(i -> System.out.println(ar[i]));
    }

    /*
     * BUT WAS IT REALLY BETTER? - NO!
     * DID THOSE EXTRA ADDED COMPLEXITY WORTH? - NO!
     * IS IT ANY FASTER? - NO!
     * IS IT MORE MEMORY OPTIMIZED?  - HELL NO!
     * SO WHY IS STREAM API USED? - MENTAL PEACE!
     *
     * IS THERE ANYTHING REALLY GOOD? - ONLY .map() AND .filter()
     *
     * OVERALL THOUGHT? - YEAH, OVERRATED!
     */
}

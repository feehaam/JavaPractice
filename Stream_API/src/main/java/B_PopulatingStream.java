import java.util.Collections;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class B_PopulatingStream {
    public static void main(String[] args) {
        // A 100 sized stream with all values set to 18
        Stream<Integer> ages = Stream.generate(() -> 18).limit(100);
        System.out.println(ages.toList());

        // Generating a sequence: 1, -2, 4, -8, 16, -32, 64, -128, 256...
        LongStream sequenceStream = LongStream.iterate(1, n -> 2 * (-n)).limit(50);
        List<Long> sequenceList = sequenceStream.boxed().toList();
        System.out.println(sequenceList);

        // Incremental sequence: very useful sometimes
        Stream<Integer> oneTo100 = IntStream.range(1, 100).boxed();
        System.out.println(oneTo100.toList());
    }
}

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class A_CreatingStream {
    public static void main(String[] args) {
        // #1
        Stream<Integer> stream1 = Stream.empty();
        // #2
        List<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> stream2 = collection.stream();
        // #3
        Stream<String> stream3 = Stream.of("a", "b", "c");
        // #4
        Stream<String> stream4 = Stream.<String>builder().add("a").add("b").add("c").build();

        System.out.println(stream1.toList().toString() + stream2.toList().toString() + stream3.toList().toString() + stream4.toList().toString());
    }
}

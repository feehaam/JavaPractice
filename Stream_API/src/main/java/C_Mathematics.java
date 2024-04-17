import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class C_Mathematics {
    public static void main(String[] args) {
        // Distinct elements
        print(getMarksStream().distinct().toList());
        // Find the max value
        Optional<Integer> max = getMarksStream().max((n1, n2) -> n1 > n2 ? 1 : -1);
        print(max.get());
        // Find the min value
        Optional<Integer> min = getMarksStream().min((n1, n2) -> n1 > n2 ? 1 : -1);
        print(min.get());
        // Summing a stream
        int sum = getMarksStream().reduce((currentTotal, nextValue) -> currentTotal + nextValue).get();
        print(sum);

        // Using PrimitiveStream
        print(getPrimitiveStream().sum());
        print(getPrimitiveStream().average());
        print(getPrimitiveStream().max());
        print(getPrimitiveStream().min());
        print(getPrimitiveStream().max());
    }

    // We need to use source.stream() every time before getting a stream because in Java,
    // streams are designed to be used only once. If we store this stream in the above method in a local variable,
    // then on re-use it would throw: java.lang.IllegalStateException: stream has already been operated upon or closed
    static Stream<Integer> getMarksStream() {
        return _Source.marks.stream();
    }

    static IntStream getPrimitiveStream() {
        return _Source.marks.stream().mapToInt(v -> v);
    }

    static void print(Object val) {
        System.out.println(val);
    }
}

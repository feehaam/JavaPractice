import java.util.stream.Stream;

public class F_Combining {
    public static void main(String[] args) {
        Stream<Integer> marks = _Source.marks.stream();
        Stream<Double> points = _Source.points.stream();
        // Algo: Convert the integer stream to double stream so that, it can be combined with the double stream.
        // Then convert the combined stream into formatted string ("%.2f")
        Stream<String> formattedScore = Stream.concat
                (
                    points,
                    marks.mapToDouble(v -> v).boxed()
                    // or: marks.map(val -> Double.parseDouble(val.toString())
                )
                .map(val -> String.format("%.2f", val)
        );
        System.out.println(formattedScore.toList());
    }
}

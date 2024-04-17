import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class G_Filtering {
    public static void main(String[] args) {
        // Filter the passed ones (Got at least 50)
        List<Integer> passedMarks = getMarksStream()
                .filter(mark -> mark >= 50)
                .toList();
        print(passedMarks);
        // Filter the failed ones (Got below 50)
        List<Integer> failedMarks = getMarksStream()
                .filter(mark -> mark < 50)
                .toList();
        print(failedMarks);
        // Find the ones who only got even marks and passed
        List<Integer> evenAndPassed = getMarksStream()
                .filter(mark -> mark >= 50 && mark % 2 ==0)
                .toList();
        print(evenAndPassed);
        // Find any of the excellent marks
        Optional<Integer> excellentMark = getMarksStream()
                .filter(mark -> mark >= 90)
                .findAny();
        print(excellentMark.get());
        // Find the first excellent mark
        Optional<Integer> firstExcellentMark = getMarksStream()
                .filter(mark -> mark >= 90)
                .findFirst();
        print(firstExcellentMark.get());
    }

    static Stream<Integer> getMarksStream() {
        return _Source.marks.stream();
    }

    static void print(Object val) {
        System.out.println(val);
    }
}

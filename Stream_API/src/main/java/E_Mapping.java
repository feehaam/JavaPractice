import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E_Mapping {
    // The .map operation is used to transform the elements of the lists.
    // For example, it's used to double the marks and convert the names to upper case.
    // The .flatMap operation is used to flatten the structure of the stream. In this case,
    // it's used to convert a list of names into a list of characters, and to flatten a list of lists into a single list.
    public static void main(String[] args) {
        // USE OF MAP OPERATION
        // Double all the values
        List<Integer> doubledMarks = _Source.marks.stream()
                .map(mark -> mark * 2)
                .toList();
        System.out.println("Doubled marks: " + doubledMarks);

        // Convert all names to upper case
        List<String> upperCaseNames = _Source.namesLatter.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("Upper case names: " + upperCaseNames);

        // Find the length of each name
        List<Integer> namesLength = _Source.namesLatter.stream()
                .map(String::length)
                .toList();
        System.out.println("Length of each name: " + namesLength);

        // USE OF FLATMAP OPERATION
        // From names list -> to characters list - [[]] -> []
        List<Character> chars = _Source.namesLatter.stream()
                .flatMap(name -> name.chars().mapToObj(e->(char)e))
                .toList();
        System.out.println("Characters from names: " + chars);

        // Using flatMap to flatten a list of lists
        List<List<Integer>> listOfLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );
        List<Integer> flatList = listOfLists.stream()
                .flatMap(List::stream)
                .toList();
        System.out.println("Flattened list: " + flatList);

        // COMBINED BOTH
        // FlatMap to split each name into individual characters, then map to convert each character to lowercase
        List<String> lowerCaseChars = _Source.namesLatter.stream()
                .flatMap(name -> Stream.of(name.split("")))
                .map(String::toLowerCase)
                .toList();
        System.out.println("Lowercase characters from names: " + lowerCaseChars);

        // Using flatMap to flatten a list of maps
        List<Map<String, Integer>> listOfMaps = Arrays.asList(
                new HashMap<String, Integer>() {{
                    put("A", 1);
                    put("B", 2);
                }},
                new HashMap<String, Integer>() {{
                    put("C", 3);
                    put("D", 4);
                }},
                new HashMap<String, Integer>() {{
                    put("E", 5);
                    put("F", 6);
                }}
        );
        List<Integer> flatMapList = listOfMaps.stream()
                .flatMap(map -> map.values().stream())
                .toList();
        System.out.println("Flattened list from List of Maps: " + flatMapList);
    }
}

import java.util.ArrayList;
import java.util.List;

public class B_SequencedCollections {
    // Some collections like linked list had these both-end add-get operations previously
    // From now on, a higher interfaces have these. (SequencedCollection, SequencedSet, SequencedMap)
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.addFirst(10);
        list.addLast(20);
        System.out.println(list);
    }
}

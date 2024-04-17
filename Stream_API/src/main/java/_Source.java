import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class _Source {
    public static List<Integer> marks = new ArrayList<>();
    public static List<Double> points = new ArrayList<>();
    public static List<String> namesLatter = new ArrayList<>();

    static {
        int total = 20;
        System.out.println("Loading sources...");
        Random r = new Random();
        for(int i=0; i<total; i++){
            marks.add(r.nextInt(80) + 20);
        }
        for(int i=0; i<total; i++){
            points.add(Math.abs(r.nextDouble()) * 100);
        }
        for(int i=0; i<total; i++){
            namesLatter.add((char) (r.nextInt(26) + 'A') + "" + (char) (r.nextInt(26) + 'A'));
        }
    }
}

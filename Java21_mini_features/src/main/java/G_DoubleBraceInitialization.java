import java.util.HashMap;
import java.util.Map;

// This feature is here in java since version 11.0 however I just found it!
// It is a common feature in other languages, but I didn't know it existed in java too!
public class G_DoubleBraceInitialization {
    public static void main(String[] args) {
        // Create a map and call its methods while initializing
        Map<Integer, String> map = new HashMap<>(){{
            put(1, "Apple");
            put(2, "Orrange");
        }};
        System.out.println(map);

        // Create a Temp class and call its methods while initializing
        Temperature temperature = new Temperature(){{
           setCel(100);
           generateOtherTemps();
        }};
        System.out.println(temperature);
    }
}

class Temperature {
    private double cel;
    private double kel;
    private double fer;

    public void setCel(double cel) {
        this.cel = cel;
    }

    public void setKel(double kel) {
        this.kel = kel;
    }

    public void setFer(double fer) {
        this.fer = fer;
    }

    public void generateOtherTemps() {
        if (cel != 0) {
            kel = cel + 273.15;
            fer = (cel * 9/5) + 32;
        } else if (kel != 0) {
            cel = kel - 273.15;
            fer = (cel * 9/5) + 32;
        } else if (fer != 0) {
            cel = (fer - 32) * 5/9;
            kel = cel + 273.15;
        }
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "cel=" + cel +
                ", kel=" + kel +
                ", fer=" + fer +
                '}';
    }
}
